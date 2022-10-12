package com.lianlianpay.ytapi.params.traderapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 退款 请求参数
 */
@Data
@EqualsAndHashCode
public class RefundParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    // 退款商户编号。 需要进行退款的商户编号， 如该字段未填写时， 默认取oid_partner的值
    private String trader_refund;
    // 商户退款流水号
    private String no_refund;

    // 商户退款时间。格式为 yyyyMMddHHmmss， HH以24小时为准，如 20180130161010
    private String dt_refund;
    // 退款请求中money_refund对应的此次退款的金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999
    private String money_refund;
    // 原收款请求中传入的商户订单号no_order
    private String no_order;
    private String dt_order;
    // 连连收款单号。 全局唯一
    private String oid_paybill;
    private String notify_url;
}
