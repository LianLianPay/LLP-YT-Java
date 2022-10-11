package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.PrcptcdQueryParams;
import com.lianlianpay.ytapi.params.queryapi.PrcptcdQueryResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 大额行号查询 Demo
 */
public class PrcptcdQueryDemo {
    public static void main(String[] args) {
        PrcptcdQueryParams params = new PrcptcdQueryParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        // 银行编码
        params.setBank_code("01020000");
        // 开户行所在省市编码， 标准地市编码
        params.setCity_code("540000");
        // 开户支行名称， 支持模糊查询
        params.setBrabank_name("ceshi");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 大额行号查询URL
        String url = "https://queryapi.lianlianpay.com/prcptcdquery.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        PrcptcdQueryResult prcptcdQueryResult = JSON.parseObject(resultJsonStr, PrcptcdQueryResult.class);
        System.out.println(prcptcdQueryResult);
    }
}
