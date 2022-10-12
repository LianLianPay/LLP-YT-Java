package com.lianlianpay.ytapi.params.instantpay;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 付款申请 请求参数
 */
@Data
@EqualsAndHashCode
public class PaymentParams {
    private String oid_partner;
    // 平台来源标识
    private String platform;
    private String api_version;
    private String sign;
    private String sign_type;
    // 原请求中商户订单号。 为商户系统内对订单的唯一编号
    private String no_order;
    private String dt_order;
    // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元
    private String money_order;
    // 收款方银行账号
    private String card_no;
    // 收款方姓名
    private String acct_name;
    // 收款银行名称
    private String bank_name;
    // 订单描述。说明付款用途，5W以上必传
    private String info_order;
    /*
    对公对私标志。
    0 - 对私。
    1 - 对公。
     */
    private String flag_card;
    // 收款备注。 传递至银行， 一般作为订单摘要展示
    private String memo;
    // 大额行号。 可调用大额行号查询接口进行查询
    private String prcptcd;
    // 银行编码
    private String bank_code;
    // 开户行所在省市编码
    private String city_code;
    // 开户支行名称
    private String brabank_name;
    private String notify_url;
    private String risk_item;
}
