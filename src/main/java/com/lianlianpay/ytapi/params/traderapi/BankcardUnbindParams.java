package com.lianlianpay.ytapi.params.traderapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡解约 请求参数
 */
@Data
@EqualsAndHashCode
public class BankcardUnbindParams {
    private String oid_partner;
    // 用户编号
    private String user_id;
    private String sign;
    private String sign_type;
    // 平台来源标识
    private String platform;
    /*
    协议号所属支付方式。
    2， 快捷支付。
    D， 认证支付。
    P， 新认证支付
    不传默认为2。
     */
    private String pay_type;
    // 签约协议编号。可调用签约查询API获取
    private String no_agree;
}
