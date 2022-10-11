package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.SupportBankQueryParams;
import com.lianlianpay.ytapi.params.queryapi.SupportBankQueryResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;

/**
 * 支持银行查询 Demo
 */
public class SupportBankQueryDemo {
    public static void main(String[] args) {
        SupportBankQueryParams params = new SupportBankQueryParams();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setApi_version("1.0");
        params.setSign_type("RSA");
        /*
        产品类型。
        1 - 普通认证收款或分期收款。
        3 - 新认证收款。
         */
        params.setProduct_type("3");
        /*
        收款产品所配置的来源标识。
        10 - 认证收款APP。
        13 - 认证收款PC端网页。
        15 - 认证收款API。
        16 - 认证收款移动端网页。
        44 - 分期收款API。
        45 - 分期收款APP。
        46 - 分期收款移动端网页。
        55 - 分期收款PC端网页。
         */
        params.setPay_chnl("15");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 支持银行查询URL
        String url = "https://queryapi.lianlianpay.com/supportbankquery.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        SupportBankQueryResult supportBankQueryResult = JSON.parseObject(resultJsonStr, SupportBankQueryResult.class);
        System.out.println(supportBankQueryResult);
    }
}
