package com.lianlianpay.ytapi.params.traderapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关闭支付订单响应参数
 */
@Data
@EqualsAndHashCode
public class ClosePayOrderResult {
    private String ret_code;
    // 请求结果代码。长度4，必返回。

    private String ret_msg;
    // 请求结果描述。长度100。

    private String no_order;
    // 请求受理成功时返回原请求中商户订单号。长度32。

    private String sign_type;
    // 请求受理成功时返回RSA。长度3，。

    private String sign;
    // 请求受理成功时返回签名。长度无限制。

    private String oid_partner;
    // 请求受理成功时返回商户编号是商户在连连支付支付平台上开设的商户号码，为18位数字，如：201304121000001004。长度18。
}