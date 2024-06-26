package com.lianlianpay.ytapi.params.payserverapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡一键绑卡响应参数
 */
@Data
@EqualsAndHashCode
public class BankCardOneBindResult {
    private String ret_code;
    // 请求结果代码。长度4，必返回。

    private String ret_msg;
    // 请求结果描述。长度100。

    private String sign_type;
    // 请求受理成功时返回RSA。长度3。

    private String sign;
    // 请求受理成功时返回签名。长度无限制。

    private String no_order;
    // 请求受理成功时返回原请求中商户订单号。长度32。

    private String gateway_url;
    // 请求受理成功时返回连连网关地址。用户跳转（get请求）到该地址，完成银行卡签约绑卡，链接有效期30分钟。长度不限。
}