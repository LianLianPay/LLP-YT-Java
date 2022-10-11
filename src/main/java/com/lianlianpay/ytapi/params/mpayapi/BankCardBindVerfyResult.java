package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约验证 响应参数
 */
@Data
@EqualsAndHashCode
public class BankCardBindVerfyResult {
    private String ret_code;
    private String ret_msg;
    private String sign_type;
    private String sign;
    private String oid_partner;
    private String no_order;
    private String user_id;
    private String card_no;
    private String no_agree;
}
