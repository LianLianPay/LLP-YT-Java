package com.lianlianpay.ytapi.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lianlianpay.ytapi.client.LLianPayClient;
import com.lianlianpay.ytapi.config.LLianPayConstant;
import com.lianlianpay.ytapi.params.mpayapi.WechatAlipayParams;
import com.lianlianpay.ytapi.params.mpayapi.WechatAlipayResult;
import com.lianlianpay.ytapi.security.LLianPayYtSignature;
import com.lianlianpay.ytapi.utils.LLianPayDateUtils;

import java.util.Objects;

/**
 * 微信支付宝创单Demo
 */
public class WechatAlipayCreateDemo {
    public static void main(String[] args) {
        WechatAlipayParams params = new WechatAlipayParams();

        String timestamp = LLianPayDateUtils.getTimestamp();

        params.setOid_partner(LLianPayConstant.OidPartner);
        // 商户编号是商户在连连支付支付平台上开设的商户号码，为18位数字，如：201304121000001004。测试阶段可以先用测试商户号测试。

        params.setNo_order("LLianPayYT" + timestamp);
        // 商户订单号。为商户系统内对订单的唯一编号，保证唯一。连连会根据no_order 创建连连订单号 oid_paybill，如no_order已有对应连连订单号 oid_paybill，则将请求视为重复订单请求。重复发起订单请求时，请求中的参数信息需与原创单时一致。

        params.setSign_type("RSA");
        // RSA。长度3，必传。

        params.setUser_id("LLianPay-YT-Test-12345");
        // 用户编号。商户系统内对用户的唯一编码，可以为自定义字符串，加密密文或者邮箱等可以唯一定义用户的标识。

        params.setBusi_partner("101001");
        // 虚拟商品销售：101001。实物商品销售：109001。当busi_partner与您的商户号的业务属性不相符时，该次请求将返回请求无效。

        params.setDt_order(timestamp);
        // 商户订单时间。格式为yyyyMMddHHmmss，HH以24小时为准，如 20180130161010。

        params.setMoney_order("10.00");
        // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。取值范围为 0.01 ~ 99999999。初始额度：50元。

        params.setNotify_url("https://test.lianlianpay.com/notify");
        // 接收异步通知的线上地址。连连支付支付平台在用户支付成功后通知商户服务端的地址。如 http://test.lianlianpay.com.cn/help/notify.php 。

        params.setValid_order(10080);
        // 订单有效期。订单创建后，开始计时，以分钟为单位，不传默认为10080 (7天)，建议赋值范围为 120 ~ 10080。超过订单有效期未被支付的订单会被置为关闭状态，不可再次以该单号发起支付申请。

        params.setRisk_item("{\"frms_ware_category\":\"4007\",\"goods_name\":\"西瓜\",\"user_info_mercht_userno\":\"LLianPay-YT-Test-12345\",\"user_info_dt_register\":\"20220823101239\",\"user_info_bind_phone\":\"13197403201\",\"user_info_full_name\":\"连连测试\",\"user_info_id_no\":\"\",\"user_info_identify_state\":\"0\",\"user_info_identify_type\":\"4\",\"user_info_id_type\":\"0\",\"frms_client_chnl\":\" 16\",\"frms_ip_addr\":\"127.0.0.1\",\"user_auth_flag\":\"1\"}");
        // 风险控制参数。连连风控部门要求商户统一传入风险控制参数字段，字段值为json 字符串的形式。传入示例及要求见风险控制说明。

        params.setPay_type("L");
        // 付款方式。L - 支付宝扫码。V - 支付宝应用支付（生活号、小程序）。I - 微信扫码。W - 微信公众号支付。20 - 微信小程序。23 - 银联云闪付。U - 聚合码支付。

        params.setInfo_order("测试付款");
        // 订单扩展字段(可选）。透传参数传于此字段，支付成功后连连会将info_order附在异步通知中返回。

        params.setName_goods("商品名称");
        // 商户商品名称。建议传入真实商品名称(可选）。

        params.setShareing_data("");
        // 分账信息(可选）。用于有分账需求的交易。

        params.setExt_param("");
        // 支付宝应用支付，微信公众号，微信小程序，微信APP支付时需传额外扩展参数，采用json串传入。

        params.setSign(LLianPayYtSignature.getInstance().sign(JSON.toJSONString(params)));
        // 签名，详见签名机制。商户请求签名错误排查步骤。

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("oid_partner", LLianPayConstant.OidPartner);
        jsonObject.put("pay_load", LLianPayYtSignature.getInstance().encryptGeneratePayload(JSON.toJSONString(params)));

        String url = "https://mpayapi.lianlianpay.com/v1/bankcardprepay";
        LLianPayClient lLianPayClient = new LLianPayClient();
        String resultJsonStr = lLianPayClient.sendRequest(url, jsonObject.toJSONString());
        WechatAlipayResult wechatalipayResult = JSON.parseObject(resultJsonStr, WechatAlipayResult.class);
        System.out.println(wechatalipayResult);

        if (Objects.equals(wechatalipayResult.getRet_msg(), "交易成功")) {
            System.out.println("复制gateway_url的内容，打开以下链接或者使用任意二维码生成工具即可生成二维码进行扫码支付");
            System.out.println("https://tool.oschina.net/qr");
        }
    }
}