package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约申请 响应参数
 */
@Data
@EqualsAndHashCode
public class BankCardBindResult {
    private String ret_code;
    private String ret_msg;
    private String token;
    private String sign_type;
    private String sign;
    private String oid_partner;
    private String no_order;
    private String dt_order;
    private String user_id;
}
