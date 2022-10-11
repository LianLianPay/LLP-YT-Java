package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.BankCardBindQueryParams;
import com.lianlianpay.ytapi.params.queryapi.BankCardBindQueryResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 用户签约银行卡列表查询 Demo
 */
public class BankCardBindQueryDemo {
    public static void main(String[] args) {
        BankCardBindQueryParams params = new BankCardBindQueryParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        // 用户编号
        params.setUser_id("LLianPay-YT-Test-12345");
        // 查询偏移量
        params.setOffset(0);
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 用户签约银行卡列表查询URL
        String url = "https://queryapi.lianlianpay.com/bankcardbindlist.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        BankCardBindQueryResult bankCardBindQueryResult = JSON.parseObject(resultJsonStr, BankCardBindQueryResult.class);
        System.out.println(bankCardBindQueryResult);
    }
}
