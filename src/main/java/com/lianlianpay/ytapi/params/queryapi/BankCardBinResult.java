package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 卡bin查询 响应参数
 */
@Data
@EqualsAndHashCode
public class BankCardBinResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    private String bank_name;

    private String bank_code;
    /*
    银行卡类型。
    2 - 储蓄卡。
    3 - 信用卡。
     */
    private String card_type;
}
