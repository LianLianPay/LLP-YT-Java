package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.traderapi.BankcardUnbindParams;
import com.lianlianpay.ytapi.params.traderapi.BankcardUnbindResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 银行卡解约 Demo
 */
public class BankcardUnbindDemo {
    public static void main(String[] args) {
        BankcardUnbindParams params = new BankcardUnbindParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        // 用户编号
        params.setUser_id("LLianPay-YT-Test-12345");
        // 签约协议编号
        params.setNo_agree("");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 银行卡解约URL
        String url = "https://traderapi.lianlianpay.com/bankcardunbind.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        BankcardUnbindResult bankcardUnbindResult = JSON.parseObject(resultJsonStr, BankcardUnbindResult.class);
        System.out.println(bankcardUnbindResult);
    }
}
