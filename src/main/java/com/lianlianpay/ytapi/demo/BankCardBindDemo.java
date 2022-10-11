package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.mpayapi.BankCardBindParams;
import com.lianlianpay.ytapi.params.mpayapi.BankCardBindResult;
import com.lianlianpay.ytapi.params.mpayapi.BankCardBindVerfyParams;
import com.lianlianpay.ytapi.params.mpayapi.BankCardBindVerfyResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 银行卡签约申请、验证 Demo
 */
public class BankCardBindDemo {
    public static void main(String[] args) {
        // 银行卡签约申请
        BankCardBindParams params = new BankCardBindParams();
        String timestamp = LLianPayDateUtils.getTimestamp();
        params.setUser_id("LLianPay-YT-Test-12345");
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        params.setNotify_url("https://test.lianlianpay/notify");
        params.setNo_order("LLianPay-YT-" + timestamp);
        params.setDt_order(timestamp);
        /*
        支付方式。 指定使用的支付方式。
        2， 快捷支付 - 借记卡。
        3， 快捷支付 - 信用卡。
        P， 新认证支付。
        27， 运通卡支付（信用卡）。
         */
        params.setPay_type("2");
        // 用户银行卡卡号
        params.setCard_no("");
        // 用户姓名，为用户在银行预留的姓名信息
        params.setAcct_name("");
        // 用户在银行预留的手机号码
        params.setBind_mob("");
        /*
        证件类型。
        0， 身份证或企业经营证件。
        1， 户口簿。
        2， 护照。
        3， 军官证。
        4， 士兵证。
        5， 港澳居民来往内地通行证。
        6，台湾同胞来往内地通行证。
        7， 临时身份证
        8，外国人居住证。
        9，警官证。
        10，组织机构代码
        X， 其他证件。
        目前仅支持身份证，不传则默认为身份证。
         */
        params.setId_type("0");
        // 证件号码
        params.setId_no("");
        // 使用商户私钥对请求参数进行加签
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 构建实际发起请求参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid_partner", LLianPayConstant.OidPartner);
        // 使用连连公钥对请求参数进行加密
        jsonObject.put("pay_load", LLianPayYtSignature.getInstance().encryptGeneratePayload(JSON.toJSONString(params)));

        // 银行卡签约申请URL
        String url = "https://mpayapi.lianlianpay.com/v1/bankcardbind";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, jsonObject.toJSONString());
        BankCardBindResult bankCardBindResult = JSON.parseObject(resultJsonStr, BankCardBindResult.class);
        System.out.println(bankCardBindResult);

        // 银行卡签约验证

        // 需要输入短信验证码，调用交易二次短信验证接口
        // 用Debug模式，断点打到这里，Debug的时候把verifyCode设置成手机收到的真实验证码
        String verifyCode = "";
        if ("8888".equals(bankCardBindResult.getRet_code())) {
            // 银行卡签约验证
            BankCardBindVerfyParams verfyParams = new BankCardBindVerfyParams();
            verfyParams.setOid_partner(bankCardBindResult.getOid_partner());
            verfyParams.setToken(bankCardBindResult.getToken());
            verfyParams.setSign_type("RSA");
            verfyParams.setNo_order(bankCardBindResult.getNo_order());
            verfyParams.setUser_id(bankCardBindResult.getUser_id());
            verfyParams.setVerify_code(verifyCode);
            verfyParams.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(verfyParams)));

            // 银行卡签约验证URL
            String verfyUrl = "https://mpayapi.lianlianpay.com/v1/bankcardbindverfy";
            String resultJsonStr2 = lLianPayClient.sendRequest(verfyUrl, JSON.toJSONString(verfyParams));
            BankCardBindVerfyResult bankCardBindVerfyResult = JSON.parseObject(resultJsonStr2, BankCardBindVerfyResult.class);
            System.out.println(bankCardBindVerfyResult);
        }
    }
}
