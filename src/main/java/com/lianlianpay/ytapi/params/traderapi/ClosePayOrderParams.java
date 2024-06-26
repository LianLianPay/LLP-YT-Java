package com.lianlianpay.ytapi.params.traderapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 关闭支付订单请求参数
 */
@Data
@EqualsAndHashCode
public class ClosePayOrderParams {
    private String oid_partner;
    // 商户编号是商户在连连支付支付平台上开设的商户号码，为18位数字，如：201304121000001004。测试阶段可以先用测试商户号测试。长度18，必传。

    private String no_order;
    // 商户订单号。为商户系统内对订单的唯一编号，保证唯一。连连会根据no_order创建连连订单号oid_paybill，如no_order已有对应连连订单号oid_paybill，则将请求视为重复订单请求。重复发起订单请求时，请求中的参数信息需与原创单时一致。长度32，必传。

    private String sign;
    // 签名，详见签名机制。商户请求签名错误排查步骤。长度无限制，必传。

    private String sign_type;
    // RSA。长度3，必传。
}