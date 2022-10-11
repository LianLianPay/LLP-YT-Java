package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.RefundQueryParams;
import com.lianlianpay.ytapi.params.queryapi.RefundQueryResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 退款订单查询 Demo
 */
public class RefundQueryDemo {
    public static void main(String[] args) {
        RefundQueryParams params = new RefundQueryParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        params.setOid_refundno("");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 退款订单查询URL
        String url = "https://queryapi.lianlianpay.com/refundquery.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        RefundQueryResult refundQueryResult = JSON.parseObject(resultJsonStr, RefundQueryResult.class);
        System.out.println(refundQueryResult);
    }
}
