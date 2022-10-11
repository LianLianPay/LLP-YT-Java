package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退款订单查询 请求参数
 */
@Data
@EqualsAndHashCode
public class RefundQueryParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    // 退款商户编号
    private String trader_refund;
    // 商户退款流水号
    private String no_refund;
    // 商户退款时间
    private String dt_refund;
    // 连连退款流水号
    private String oid_refundno;
}
