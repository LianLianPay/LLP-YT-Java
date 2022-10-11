package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约验证 请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardBindVerfyParams {
    private String oid_partner;
    private String token;
    private String sign_type;
    private String sign;
    private String no_order;
    private String user_id;
    private String verify_code;
}
