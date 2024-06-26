package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信支付宝创单 请求参数
 */
@Data
@EqualsAndHashCode
public class WechatAlipayParams {
    private String oid_partner;
    // 商户编号是商户在连连支付支付平台上开设的商户号码，为18位数字，如：201304121000001004。测试阶段可以先用测试商户号测试。
    private String sign;
    //签名， 详见签名机制。商户请求签名错误排查步骤
    private String sign_type;
    //RSA。
    private String user_id;
    //用户编号。 商户系统内对用户的唯一编码，可以为自定义字符串，加密密文或者邮箱等可以唯一定义用户的标识。
    private String busi_partner;
    //虚拟商品销售：101001。
    //实物商品销售：109001。当busi_partner与您的商户号的业务属性不相符时， 该次请求将返回请求无效。 取值范围为 0.01 ~ 99999999。初始额度：50元
    private String no_order;
    // 商户订单号。 为商户系统内对订单的唯一编号，保证唯一。 连连会根据no_order 创建连连订单号 oid_paybill， 如no_order已有对应连连订单号 oid_paybill，则将请求视为重复订单请求。 重复发起订单请求时， 请求中的参数信息需与原创单时一致。
    private String dt_order;
    // 商户订单时间。格式为yyyyMMddHHmmss
    private String name_goods;
    // 商户商品名称。建议传入真实商品名称(可选）
    private String info_order;
    // 订单扩展字段(可选）
    private String money_order;
    //交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度
    private String notify_url;
    // 接收异步通知的线上地址
    private int valid_order;
    // 订单有效期(可选）
    private String shareing_data;
    // 分账信息(可选）
    private String risk_item;
    // 风险控制参数
    private String pay_type;

    private String ext_param;

}
