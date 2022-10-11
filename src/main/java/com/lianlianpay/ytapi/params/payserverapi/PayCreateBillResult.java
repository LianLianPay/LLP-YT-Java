package com.lianlianpay.ytapi.params.payserverapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收银台支付创单 响应参数
 */
@Data
@EqualsAndHashCode
public class PayCreateBillResult {
    private String ret_code;
    private String ret_msg;
    private String sign_type;
    private String sign;
    private String user_id;
    private String oid_partner;
    // 授权令牌。有效期为30分钟。
    private String token;
    private String no_order;
    private String dt_order;
    private String money_order;
    private String oid_paybill;
    // 连连网关地址。跳转方式：商户前端form表单POST提交
    private String gateway_url;
}
