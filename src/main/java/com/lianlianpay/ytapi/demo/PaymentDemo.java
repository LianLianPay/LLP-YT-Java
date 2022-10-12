package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.instantpay.PaymentParams;
import com.lianlianpay.ytapi.params.instantpay.PaymentResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 付款申请 Demo
 */
public class PaymentDemo {
    public static void main(String[] args) {
        PaymentParams params = new PaymentParams();
        String timestamp = LLianPayDateUtils.getTimestamp();
        params.setApi_version("1.1");
        params.setSign_type("RSA");
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setNo_order("LLianPayYT" + timestamp);
        params.setDt_order(timestamp);
        // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元
        params.setMoney_order("100.00");
        // 收款方银行账号
        params.setCard_no("");
        // 收款方姓名
        params.setAcct_name("");
        // 收款银行名称
        params.setBank_name("");
        // 订单描述
        params.setInfo_order("测试付款");
        /*
        对公对私标志。
        0 - 对私。
        1 - 对公。
         */
        params.setFlag_card("0");
        // 收款备注。 传递至银行， 一般作为订单摘要展示
        params.setMemo("测试付款");
        params.setNotify_url("https://test.lianlianpay.com/notify");
        // 生产签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 构建实际发起请求参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid_partner", LLianPayConstant.OidPartner);
        // 使用连连公钥对请求参数进行加密
        jsonObject.put("pay_load", LLianPayYtSignature.getInstance().encryptGeneratePayload(JSON.toJSONString(params)));

        // 付款申请URL
        String url = "https://instantpay.lianlianpay.com/paymentapi/payment.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, jsonObject.toJSONString());
        PaymentResult paymentResult = JSON.parseObject(resultJsonStr, PaymentResult.class);
        System.out.println(paymentResult);
    }
}
