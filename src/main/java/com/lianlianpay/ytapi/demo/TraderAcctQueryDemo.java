package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.TraderAcctQueryParams;
import com.lianlianpay.ytapi.params.queryapi.TraderAcctQueryResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 账户余额查询接口 Demo
 */
public class TraderAcctQueryDemo {
    public static void main(String[] args) {
        TraderAcctQueryParams params = new TraderAcctQueryParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        params.setApi_version("1.0");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 账户余额查询接口URL
        String url = "https://traderapi.lianlianpay.com/traderAcctQuery.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        TraderAcctQueryResult traderAcctQueryResult = JSON.parseObject(resultJsonStr, TraderAcctQueryResult.class);
        System.out.println(traderAcctQueryResult);
    }
}
