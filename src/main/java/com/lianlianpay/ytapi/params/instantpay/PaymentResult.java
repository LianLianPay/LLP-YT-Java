package com.lianlianpay.ytapi.params.instantpay;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 付款申请 响应参数
 */
@Data
@EqualsAndHashCode
public class PaymentResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    private String oid_partner;
    private String no_order;
    // 连连支付单号
    private String oid_paybill;
    //ret_code 为4002, 4003, 4004 时返回确认码。 用于确认付款API
    private String confirm_code;
}
