package com.lianlianpay.ytapi.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 连连银通签名
 */
public class LLianPayYtSignature {
    private static Logger log = LoggerFactory.getLogger(LLianPayYtSignature.class);
    private static LLianPayYtSignature instance;

    public static LLianPayYtSignature getInstance() {
        if (null == instance)
            return new LLianPayYtSignature();
        return instance;
    }

    /**
     * 签名处理，默认使用LLianPayConstant中配置的私钥MerchantPrivateKey
     *
     * @param jsonStr ：签名源内容
     * @return
     */
    public String sign(String jsonStr) {
        return sign(LLianPayConstant.MerchantPrivateKey, jsonStr);
    }

    /**
     * 签名处理
     *
     * @param privateKey ：私钥
     * @param jsonStr    ：签名源内容
     * @return
     */
    public String sign(String privateKey, String jsonStr) {
        if (jsonStr == null || "".equals(jsonStr)) {
            log.error("签名处理中，签名异常，签名源内容为空");
            throw new RuntimeException("签名处理中，签名异常，签名源内容为空");
        }
        log.info(String.format("签名处理中，签名源内容：%s", jsonStr));
        // 生成待签名串
        String signedStr = generateSignedStr(JSON.parseObject(jsonStr));
        log.info(String.format("签名处理中，待签名串：%s", signedStr));
        String sign = RSASign.getInstance().sign(privateKey, signedStr);
        log.info(String.format("签名完成，签名值为：%s", sign));
        return sign;
    }

    /**
     * 签名验证，默认使用LLianPayConstant中配置的公钥LLianPayPublicKey
     *
     * @param signStr   ：源串
     * @param signedStr ：签名结果串
     * @return
     */
    public boolean checkSign(String signStr, String signedStr) {
        return checkSign(LLianPayConstant.LLianPayPublicKey, generateSignedStr(JSONObject.parseObject(signStr)), signedStr);
    }

    /**
     * 签名验证
     *
     * @param publicKey ：公钥
     * @param signStr   ：源串
     * @param signedStr ：签名结果串
     * @return
     */
    public boolean checkSign(String publicKey, String signStr, String signedStr) {
        try {
            log.info(String.format("签名验证中，源串：%s，原签名结果串：%s", signStr, signedStr));
            return RSASign.getInstance().checksign(publicKey, signStr, signedStr);
        } catch (Exception e) {
            log.error("签名验证异常,{}", e.getMessage());
        }
        return false;
    }

    public String encryptGeneratePayload(String json) {
        return encryptGeneratePayload(json, LLianPayConstant.LLianPayPublicKey);
    }

    public String encryptGeneratePayload(String json, String publicKey) {
        String version = "lianpay1_0_1";
        String hmackKey = genLetterDigitRandom(32);
        String aesKey = genLetterDigitRandom(32);
        String nonce = genLetterDigitRandom(8);
        log.info(String.format("pay_load生成中，原字符串为：%s", json));
        String result = LLianPayEncrypt.encrypt(json, publicKey, hmackKey, version, aesKey, nonce);
        log.info(String.format("pay_load生成完成，加密后为：%s", result));
        return result;
    }

    /**
     * 测试环境使用连连公钥加密密码
     *
     * @param sourceStr
     * @return encryptStr
     */
    public String localEncrypt(String sourceStr) {
        String encryptStr = null;
        try {
            encryptStr = RSASign.getInstance().encrypt(sourceStr, LLianPayConstant.LLianPayPublicKey);
            log.info(String.format("本地RSA加密，源串：%s，加密后值：%s", sourceStr, encryptStr));
        } catch (Exception e) {
            log.error("本地RSA加密异常,{}", e.getMessage());
        }
        return encryptStr;
    }

    /**
     * 生成待签名串
     *
     * @param jsonObject
     * @return
     */
    private String generateSignedStr(JSONObject jsonObject) {
        StringBuffer content = new StringBuffer();
        // 按照key做首字母升序排列
        List<String> keys = new ArrayList(jsonObject.keySet());
        Collections.sort(keys, String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = jsonObject.getString(key);
            // sign,ip_client,空串不参与签名
            if ("sign".equals(key) || value == null || "".equals(value)) {
                continue;
            }
            content.append((i == 0 ? "" : "&") + key + "=" + value);
        }
        return content.charAt(0) == '&' ? content.deleteCharAt(0).toString() : content.toString();
    }

    private String genLetterDigitRandom(int size) {
        StringBuffer allLetterDigit = new StringBuffer("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        Random random = new Random();
        StringBuffer randomSb = new StringBuffer();
        for (int i = 0; i < size; ++i) {
            randomSb.append(allLetterDigit.charAt(random.nextInt(allLetterDigit.length())));
        }
        return randomSb.toString();
    }
}
