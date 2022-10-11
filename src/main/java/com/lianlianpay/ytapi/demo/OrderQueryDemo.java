package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.queryapi.OrderQueryParams;
import com.lianlianpay.ytapi.params.queryapi.OrderQueryResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 收款结果查询 Demo
 */
public class OrderQueryDemo {
    public static void main(String[] args) {
        OrderQueryParams params = new OrderQueryParams();
        String timestamp = LLianPayDateUtils.getTimestamp();
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        // 原请求中商户订单号。 为商户系统内对订单的唯一编号
        params.setNo_order("");
        params.setDt_order(timestamp);
        params.setQuery_version("1.0");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 收款结果查询URL
        String url = "https://queryapi.lianlianpay.com/orderquery.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        OrderQueryResult orderQueryResult = JSON.parseObject(resultJsonStr, OrderQueryResult.class);
        System.out.println(orderQueryResult);
    }
}
