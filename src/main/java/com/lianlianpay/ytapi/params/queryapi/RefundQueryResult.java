package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退款订单查询 响应参数
 */
@Data
@EqualsAndHashCode
public class RefundQueryResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    private String oid_partner;
    // 原请求中商户退款流水号
    private String no_refund;
    // 原请求中退款金额
    private String money_refund;
    // 连连退款流水号， 全局唯一
    private String oid_refundno;
    /*
    退款单的退款状态， 详情参考退款类订单状态说明。
    0 - 退款申请。
    2 - 退款成功。
    3 - 退款失败。
     */
    private String sta_refund;
    // 清算日期。 格式：YYYYMMDD。
    private String settle_date;
}
