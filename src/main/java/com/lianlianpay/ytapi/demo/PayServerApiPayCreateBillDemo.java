package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.payserverapi.PayCreateBillParams;
import com.lianlianpay.ytapi.params.payserverapi.PayCreateBillResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 收银台支付创单 Demo
 */
public class PayServerApiPayCreateBillDemo {
    public static void main(String[] args) {
        PayCreateBillParams params = new PayCreateBillParams();
        String timestamp = LLianPayDateUtils.getTimestamp();
        params.setApi_version("1.0");
        params.setSign_type("RSA");
        params.setTime_stamp(timestamp);
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setUser_id("LLianPay-YT-Test-12345");
        /*
        虚拟商品销售：101001。
        实物商品销售：109001。当busi_partner与您的商户号的业务属性不相符时， 该次请求将返回请求无效。
         */
        params.setBusi_partner("101001");
        params.setNo_order("LLianPayYT" + timestamp);
        params.setDt_order(timestamp);
        // 商户商品名称。建议传入真实商品名称
        params.setName_goods("西瓜");
        // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元
        params.setMoney_order("10.00");
        params.setNotify_url("https://test.lianlianpay.com/notify");
        params.setUrl_return("https://open.lianlianpay.com/docs/accp/accpstandard/accp-overview.html");
        params.setRisk_item("{\"frms_ware_category\":\"4007\",\"goods_name\":\"西瓜\",\"user_info_mercht_userno\":\"LLianPay-YT-Test-12345\",\"user_info_dt_register\":\"20220823101239\",\"user_info_bind_phone\":\"13197403201\",\"user_info_full_name\":\"连连测试\",\"user_info_id_no\":\"\",\"user_info_identify_state\":\"0\",\"user_info_identify_type\":\"4\",\"user_info_id_type\":\"0\",\"frms_client_chnl\":\" H5\",\"frms_ip_addr\":\"127.0.0.1\",\"user_auth_flag\":\"1\"}");

        /*
        支付产品标识。
        0， 快捷收款。
        1， 认证收款。
        2， 网银收款。
        5， 新认证收款。
        12， 手机网银收款 。
         */
        params.setFlag_pay_product("0");
        /*
        应用渠道标识。
        0， App-Android。
        1， App-iOS。
        2， Web。
        3， H5。
         */
        params.setFlag_chnl("3");
        // 生产签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        // 收银台支付创单URL
        String url = "https://payserverapi.lianlianpay.com/v1/paycreatebill";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, JSON.toJSONString(params));
        PayCreateBillResult payCreateBillResult = JSON.parseObject(resultJsonStr, PayCreateBillResult.class);
        System.out.println(payCreateBillResult);
    }
}
