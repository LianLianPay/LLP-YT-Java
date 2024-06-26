package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.traderapi.ClosePayOrderParams;
import com.lianlianpay.ytapi.params.traderapi.ClosePayOrderResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 支付订单关闭 Demo
 */
public class ClosePayOrderDemo {
    public static void main(String[] args) {
        ClosePayOrderParams params = new ClosePayOrderParams();

        String timestamp = LLianPayDateUtils.getTimestamp();

        params.setOid_partner(LLianPayConstant.OidPartner);

        params.setSign_type("RSA");

        params.setNo_order("LLianPayYT" + timestamp);

        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        JSONObject jsonObject = new JSONObject();

        String url = "https://traderapi.lianlianpay.com/closeOrder.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, jsonObject.toJSONString(params));
        ClosePayOrderResult closePayOrderResult = JSON.parseObject(resultJsonStr, ClosePayOrderResult.class);
        System.out.println(closePayOrderResult);
    }
}
