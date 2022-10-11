package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class SupportBank {
    // 银行编号
    private String bank_code;
    // 所属银行名称
    private String bank_name;
    /*
    银行卡类型。
    2 - 储蓄卡。
    3 - 信用卡。
     */
    private String card_type;
    // 单笔限额。 单位为元， 恒为整数。 最大限额为90000000.
    private String single_amt;
    // 单日限额。 单位为元， 恒为整数。 最大限额为90000000.
    private String day_amt;
    // 单月限额。单位为元，恒为整数。最大限额为90000000。
    private String month_amt;
    /*
    银行状态。
    0 - 正常。
    2 - 维护中。
     */
    private String bank_status;
}
