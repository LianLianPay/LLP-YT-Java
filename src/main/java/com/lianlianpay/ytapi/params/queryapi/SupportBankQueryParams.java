package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支持银行查询 请求参数
 */
@Data
@EqualsAndHashCode
public class SupportBankQueryParams {
    private String oid_partner;
    private String api_version;
    private String sign;
    private String sign_type;
    // 银行编码。 可调用卡bin查询API进行查询
    private String bank_code;
    /*
    银行卡类型， 默认且目前仅支持2。
    2 - 借记卡。
    3 - 信用卡。
     */
    private String card_type;
    /*
    产品类型。
    1 - 普通认证收款或分期收款。
    3 - 新认证收款。
     */
    private String product_type;
    /*
    收款产品所配置的来源标识。
    10 - 认证收款APP。
    13 - 认证收款PC端网页。
    15 - 认证收款API。
    16 - 认证收款移动端网页。
    44 - 分期收款API。
    45 - 分期收款APP。
    46 - 分期收款移动端网页。
    55 - 分期收款PC端网页。
     */
    private String pay_chnl;
}
