package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约支付申请 响应参数
 */
@Data
@EqualsAndHashCode
public class BankCardPrePayResult {
    private String ret_code;
    private String ret_msg;
    // 授权令牌。有效期为30分钟。
    private String token;
    private String sign_type;
    private String sign;
    private String oid_partner;
    // 原请求中商户订单号
    private String no_order;
    private String dt_order;
    private String money_order;
    private String oid_paybill;
    private String info_order;
    // 签约协议号。支付成功后返回本次签约协议号。
    private String no_agree;
    // 清算日期。 格式：YYYYMMDD
    private String settle_date;
}
