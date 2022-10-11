package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户余额查询接口 响应参数
 */
@Data
@EqualsAndHashCode
public class TraderAcctQueryResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    // 商户未结算金额。 该金额可用于退款， 结转。 不建议开通商户站充值业务的商户使用账户余额查询接口查询未结算金额， 并将其作为退款依据。
    private String amt_unsettle;
    // 商户可用余额。该金额可用于商户提现或付款。
    private String amt_balance;
}
