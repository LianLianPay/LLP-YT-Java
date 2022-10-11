package com.lianlianpay.ytapi.params.instantpay;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 付款结果查询 请求参数
 */
@Data
@EqualsAndHashCode
public class QueryPaymentParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    // 原请求中商户订单号。 为商户系统内对订单的唯一编号
    private String no_order;
    // 平台来源标识
    private String platform;
    // 连连付款单号
    private String oid_paybill;
    private String api_version;
}
