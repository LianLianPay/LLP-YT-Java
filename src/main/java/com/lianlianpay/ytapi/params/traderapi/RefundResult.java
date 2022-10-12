package com.lianlianpay.ytapi.params.traderapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退款 响应参数
 */
@Data
@EqualsAndHashCode
public class RefundResult {
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
    2 - 退款成功。
    3 - 退款失败。
     */
    private String sta_refund;
    // 清算日期。 格式：YYYYMMDD
    private String settle_date;
}
