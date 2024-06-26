package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.params.payserverapi.BankCardOneBindParams;
import com.lianlianpay.ytapi.params.payserverapi.BankCardOneBindResult;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;
/**
 * 一键绑卡签约Demo
 */
public class BankCardOneBindDemo {
    public static void main(String[] args) {
        BankCardOneBindParams params = new BankCardOneBindParams();

        String timestamp = LLianPayDateUtils.getTimestamp();

        params.setApi_version("1.0");

        params.setUser_id("LLianPay-YT-Test-12345");

        params.setTime_stamp(timestamp);

        params.setOid_partner(LLianPayConstant.OidPartner);

        params.setSign_type("RSA");

        params.setNotify_url("https://test.lianlianpay/notify");

        params.setNo_order("LLianPayYT" + timestamp);

        params.setDt_order(timestamp);

        params.setPay_type("2");

        params.setCard_no(""); // 用户银行卡卡号6236681540024760964

        params.setAcct_name(""); // 用户姓名，为用户在银行预留的姓名信息

        params.setBind_mob(""); // 用户在银行预留的手机号码

        params.setId_type("0"); // 证件类型证件类型。
        /*    0， 身份证或企业经营证件。
        1， 户口簿。
        2， 护照。
        3， 军官证。
        4， 士兵证。
        5， 港澳居民来往内地通行证。
        6，台湾同胞来往内地通行证。
        7， 临时身份证
        8，外国人居住证。
        9，警官证。
        10，组织机构代码
        X， 其他证件。
        目前仅支持身份证，不传则默认为身份证。
        */

        params.setId_no("420625200308072516"); // 证件号码

        params.setRisk_item("{\"frms_ware_category\":\"4007\",\"goods_name\":\"西瓜\",\"user_info_mercht_userno\":\"LLianPay-YT-Test-12345\",\"user_info_dt_register\":\"20220823101239\",\"user_info_bind_phone\":\"13197403201\",\"user_info_full_name\":\"连连测试\",\"user_info_id_no\":\"\",\"user_info_identify_state\":\"0\",\"user_info_identify_type\":\"4\",\"user_info_id_type\":\"0\",\"frms_client_chnl\":\" H5\",\"frms_ip_addr\":\"127.0.0.1\",\"user_auth_flag\":\"1\"}");

        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));// 使用商户私钥对请求参数进行加签

        JSONObject jsonObject = new JSONObject();

        String url = "https://payserverapi.lianlianpay.com/v1/tokensigncreatebill";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, jsonObject.toJSONString(params));
        BankCardOneBindResult bankCardOneBindResult = JSON.parseObject(resultJsonStr, BankCardOneBindResult.class);
        System.out.println(bankCardOneBindResult);
    }
}