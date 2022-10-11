package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.mpayapi.BankCardPayParams;
import com.lianlianpay.ytapi.params.mpayapi.BankCardPayResult;
import com.lianlianpay.ytapi.params.mpayapi.BankCardPrePayParams;
import com.lianlianpay.ytapi.params.mpayapi.BankCardPrePayResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

/**
 * 银行卡签约支付申请 Demo
 */
public class BankCardPayDemo {
    public static void main(String[] args) {
        BankCardPrePayParams params = new BankCardPrePayParams();
        String timestamp = LLianPayDateUtils.getTimestamp();
        params.setUser_id("LLianPay-YT-Test-12345");
        params.setOid_partner(LLianPayConstant.OidPartner);
        params.setSign_type("RSA");
        /*
        虚拟商品销售：101001。
        实物商品销售：109001
         */
        params.setBusi_partner("101001");
        params.setNo_order("LLianPay-YT-" + timestamp);
        params.setDt_order(timestamp);
        // 商户商品名称。 建议传入真实商品名称
        params.setName_goods("西瓜");
        // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元
        params.setMoney_order("0.01");
        params.setNotify_url("https://test.lianlianpay/notify");
        // 订单有效期。订单创建后，开始计时， 以分钟为单位，不传默认为10080 (7天)
        params.setValid_order(120);
        params.setRisk_item("{\"frms_ware_category\":\"4007\",\"goods_name\":\"西瓜\",\"user_info_mercht_userno\":\"LLianPay-YT-Test-12345\",\"user_info_dt_register\":\"20220823101239\",\"user_info_bind_phone\":\"13197403201\",\"user_info_full_name\":\"连连测试\",\"user_info_id_no\":\"\",\"user_info_identify_state\":\"0\",\"user_info_identify_type\":\"4\",\"user_info_id_type\":\"0\",\"frms_client_chnl\":\" H5\",\"frms_ip_addr\":\"127.0.0.1\",\"user_auth_flag\":\"1\"}");
        /*
        支付方式。 指定使用的支付方式。
        2， 快捷支付 - 借记卡。
        3， 快捷支付 - 信用卡。
        P， 新认证支付。
        27， 运通卡支付（信用卡）。
         */
        params.setPay_type("2");
        // 签约协议号。用户以签约支付API或者签约API成功进行签约后，连连下发的永久令牌
        params.setNo_agree("");
        // 生成签名
        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid_partner", LLianPayConstant.OidPartner);
        // 使用连连公钥对请求参数进行加密
        jsonObject.put("pay_load", LLianPayYtSignature.getInstance().encryptGeneratePayload(JSON.toJSONString(params)));

        // 银行卡签约支付申请URL
        String url = "https://mpayapi.lianlianpay.com/v1/bankcardprepay";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, jsonObject.toJSONString());
        BankCardPrePayResult bankCardPrePayResult = JSON.parseObject(resultJsonStr, BankCardPrePayResult.class);
        System.out.println(bankCardPrePayResult);

        // 需要输入短信验证码，调用交易二次短信验证接口
        // 用Debug模式，断点打到这里，Debug的时候把verifyCode设置成手机收到的真实验证码
        String verifyCode = "";
        if ("8888".equals(bankCardPrePayResult.getRet_code())) {
            BankCardPayParams payParams = new BankCardPayParams();
            payParams.setOid_partner(bankCardPrePayResult.getOid_partner());
            // 授权令牌，有效期为30分钟
            payParams.setToken(bankCardPrePayResult.getToken());
            payParams.setSign_type(bankCardPrePayResult.getSign_type());
            // 商户订单号
            payParams.setNo_order(bankCardPrePayResult.getNo_order());
            // 交易金额
            payParams.setMoney_order(bankCardPrePayResult.getMoney_order());
            // 短信验证码。验证银行预留手机号
            payParams.setVerify_code(verifyCode);
            // 生成签名
            payParams.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(payParams)));

            // 银行卡签约支付验证URL
            String payUrl = "https://mpayapi.lianlianpay.com/v1/bankcardpay";
            String resultJsonStr2 = new LLianPayClient().sendRequest(payUrl, JSON.toJSONString(payParams));
            BankCardPayResult bankCardPayResult = JSON.parseObject(resultJsonStr2, BankCardPayResult.class);
            System.out.println(bankCardPayResult);
        }
    }
}
