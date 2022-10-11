package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约支付验证 请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardPayParams {
    private String oid_partner;
    // 授权令牌，有效期为30分钟
    private String token;
    private String sign_type;
    private String sign;
    private String no_order;
    // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元
    private String money_order;
    // 短信验证码。验证银行预留手机号
    private String verify_code;
}
