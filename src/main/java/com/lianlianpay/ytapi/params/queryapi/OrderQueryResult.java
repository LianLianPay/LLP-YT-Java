package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收款结果查询 响应参数
 */
@Data
@EqualsAndHashCode
public class OrderQueryResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    private String oid_partner;
    // 连连支付单号
    private String oid_paybill;
    private String dt_order;
    // 原请求中交易金额
    private String money_order;
    /*
    支付结果以此为准， 商户依此字段进行后续是否发货的操作，详情可参考收款类订单状态说明。
    SUCCESS， 成功。
    WAITING，等待支付。
    PROCESSING，银行支付处理中。
    REFUND，退款。
    FAILURE， 失败 。
     */
    private String result_pay;
    // 清算日期。 格式：YYYYMMDD
    private String settle_date;
    private String info_order;
    /*
    支付方式。 该订单对应的支付方式。
    0，余额支付。
    1， 网银支付 - 借记卡。
    2， 快捷支付 - 借记卡。
    3， 快捷支付 - 信用卡。
    8， 网银支付 - 信用卡。
    9， 网银支付 - B2B企业网银。
    D， 认证支付。
    P， 新认证支付。
     */
    private String pay_type;
    // 银行编号
    private String bank_code;
    // 银行名称。不参与签名
    private String bank_name;
    // 查询新认证支付或认证支付的订单时返回，且不参与签名
    private String card_no;
    // 支付备注， 支付失败的原因。 如果多次支付失败， 返回最后一次失败原因
    private String memo;
    // 付款方名称。rsa公钥加密返回，解密方法demo联系技术支持获取
    private String credential_name;
    // 付款卡号。rsa公钥加密返回，解密方法demo联系技术支持获取
    private String card_number;
    // 请求渠道流水号
    private String reqchnl_serialno;
    // 渠道流水号
    private String finchnl_serialno;
    // 渠道用户号
    private String channel_user_id;
    // 渠道扩展参数
    private String ext_data;
}
