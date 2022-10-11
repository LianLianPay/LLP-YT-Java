package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.BankCardBinParams;
import com.lianlianpay.ytapi.params.queryapi.BankCardBinResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 卡bin查询 Demo
 */
public class BankCardBinDemo {
    public static void main(String[] args) {
        BankCardBinParams params = new BankCardBinParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        params.setCard_no("");
        // 生成签名
        String sign = LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params));
        params.setSign(sign);

        // 卡bin查询URL
        String url = "https://queryapi.lianlianpay.com/bankcardbin.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        BankCardBinResult bankCardBinResult = JSON.parseObject(resultJsonStr, BankCardBinResult.class);
        System.out.println(bankCardBinResult);
    }
}
