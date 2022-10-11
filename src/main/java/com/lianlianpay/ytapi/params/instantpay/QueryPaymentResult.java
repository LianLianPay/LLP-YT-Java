package com.lianlianpay.ytapi.params.instantpay;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 付款结果查询 响应参数
 */
@Data
@EqualsAndHashCode
public class QueryPaymentResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    private String oid_partner;
    private String no_order;
    // 连连支付单号
    private String oid_paybill;
    // 商户订单时间
    private String dt_order;
    // 原请求中交易金额
    private String money_order;
    /*
    付款结果， 结果以此字段为准， 详情可参考付款类订单状态说明。
    APPLY， 付款申请。
    CHECK， 复核申请。
    SUCCESS， 成功。
    PROCESSING，付款处理中。
    CANCEL，付款退款， 付款成功后，有可能发生退款。
    FAILURE， 失败 。
    CLOSED， 付款关闭。
     */
    private String result_pay;
    // 清算日期
    private String settle_date;
    private String info_order;
    // 付款失败的原因
    private String memo;
}
