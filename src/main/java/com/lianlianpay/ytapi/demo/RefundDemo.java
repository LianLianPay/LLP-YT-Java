package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.traderapi.RefundParams;
import com.lianlianpay.ytapi.params.traderapi.RefundResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 退款 Demo
 */
public class RefundDemo {
    public static void main(String[] args) {
        RefundParams params = new RefundParams();
        String timestamp = LLianPayDateUtils.getTimestamp();
        params.setSign_type("RSA");
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setNo_refund("LLianPayYT" + timestamp);
        params.setDt_refund(timestamp);
        // 退款请求中money_refund对应的此次退款的金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。
        params.setMoney_refund("100.00");
        // 连连收款单号。 全局唯一。 如： 2011030900001098。原商户订单号no_order + 原商户订单时间 dt_order或连连收款单号oid_paybill必须提供一个作为退款单标识
        params.setOid_paybill("");
        params.setNotify_url("https://test.lianlianpay.com/notify");

        // 生产签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));
        // 退款URL
        String url = "https://traderapi.lianlianpay.com/refund.htm";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        RefundResult refundResult = JSON.parseObject(resultJsonStr, RefundResult.class);
        System.out.println(refundResult);
    }
}
