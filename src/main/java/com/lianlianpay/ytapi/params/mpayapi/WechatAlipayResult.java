package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信支付宝创单 响应参数
 */
@Data
@EqualsAndHashCode
public class WechatAlipayResult {
    private String ret_code;
    //请求结果代码(必返回）
    private String ret_msg;
    //请求结果描述（必返回）
    private String sign_type;
    //RSA
    private String sign;
    //签名
    private String user_id;

    private String oid_partner;
    //商户号
    private String token;
    //支付授权令牌
    private String money_order;
    //商户订单金额
    private String oid_paybill;

    private String no_order;
    // 连连支付单号
    private String dt_order;
    //商户订单时间
    private String info_order;

    private String payload;

}
