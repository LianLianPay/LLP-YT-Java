package com.lianlianpay.ytapi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA方式签名
 */
public class RSASign {

    private static Logger log = LoggerFactory.getLogger(RSASign.class);
    private static RSASign instance;

    private RSASign() {
    }

    public static RSASign getInstance() {
        if (null == instance)
            return new RSASign();
        return instance;
    }

    /**
     * 签名处理
     *
     * @param privateKey：私钥
     * @param signStr：签名源内容
     * @return
     */
    public String sign(String privateKey, String signStr) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getBytesBASE64(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);
            // 用私钥对信息生成数字签名
            java.security.Signature signet = java.security.Signature.getInstance("MD5withRSA");
            signet.initSign(myprikey);
            signet.update(signStr.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signet.sign(); // 对信息的数字签名
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(signed));
        } catch (Exception e) {
            log.error("签名失败,{}", e.getMessage());
        }
        return null;
    }

    /**
     * 签名验证
     *
     * @param publicKey：公钥
     * @param oidStr：源串
     * @param signedStr：签名结果串
     * @return
     */
    public boolean checksign(String publicKey, String oidStr, String signedStr) {
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.getBytesBASE64(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);
            byte[] signed = Base64.getBytesBASE64(signedStr);  // 这是SignatureData输出的数字签名
            java.security.Signature signetcheck = java.security.Signature.getInstance("MD5withRSA");
            signetcheck.initVerify(pubKey);
            signetcheck.update(oidStr.getBytes(StandardCharsets.UTF_8));
            return signetcheck.verify(signed);
        } catch (Exception e) {
            log.error("签名验证异常,{}", e.getMessage());
        }
        return false;
    }

    /**
     * 公钥加密的方法
     *
     * @param source
     * @param publicKey
     * @return
     * @throws Exception
     */
    public String encrypt(String source, String publicKey) throws Exception {

        BASE64Decoder b64d = new BASE64Decoder();
        byte[] keyByte = b64d.decodeBuffer(publicKey);
        X509EncodedKeySpec x509ek = new X509EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, keyFactory.generatePublic(x509ek));
        byte[] sbt = source.getBytes(StandardCharsets.UTF_8); // //本地测试可以使用这个
        byte[] epByte = cipher.doFinal(sbt);
        BASE64Encoder encoder = new BASE64Encoder();
        String epStr = encoder.encode(epByte);
        return epStr;
    }
}
