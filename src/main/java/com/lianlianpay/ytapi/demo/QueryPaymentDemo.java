package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.instantpay.QueryPaymentParams;
import com.lianlianpay.ytapi.params.instantpay.QueryPaymentResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 付款结果查询 Demo
 */
public class QueryPaymentDemo {
    public static void main(String[] args) {
        QueryPaymentParams params = new QueryPaymentParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        params.setOid_paybill("");
        params.setApi_version("1.0");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 退款订单查询URL
        String url = "https://instantpay.lianlianpay.com/paymentapi/queryPayment.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        QueryPaymentResult queryPaymentResult = JSON.parseObject(resultJsonStr, QueryPaymentResult.class);
        System.out.println(queryPaymentResult);
    }
}
