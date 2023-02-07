package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class BankCardBindAgreement {
    // 签约协议号
    private String no_agree;
    // 用户银行卡卡号
    private String card_no;
    // 银行编号
    private String bank_code;
    // 所属银行名称
    private String bank_name;
    //银行预留手机号码
    private String bind_mobile;
    /*
    银行卡类型。
    2 - 储蓄卡。
    3 - 信用卡。
     */
    private String card_type;
}
