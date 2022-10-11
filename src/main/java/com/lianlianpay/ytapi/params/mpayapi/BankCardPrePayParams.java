package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约支付申请 请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardPrePayParams {
    private String platform;
    private String user_id;
    private String oid_partner;
    private String sign_type;
    private String sign;
    /*
    虚拟商品销售：101001。
    实物商品销售：109001
     */
    private String busi_partner;
    private String no_order;
    private String dt_order;
    // 商户商品名称。 建议传入真实商品名称
    private String name_goods;
    private String info_order;
    // 交易金额。请求no_order对应的订单总金额，单位为元，精确到小数点后两位，小数点计入字符长度。 取值范围为 0.01 ~ 99999999。初始额度：50元
    private String money_order;
    // 接收异步通知的线上地址
    private String notify_url;
    // 订单有效期。订单创建后，开始计时， 以分钟为单位，不传默认为10080 (7天)
    private Integer valid_order;
    private String risk_item;
    // 分账信息
    private String shareing_data;
    /*
    支付方式。 指定使用的支付方式。
    2， 快捷支付 - 借记卡。
    3， 快捷支付 - 信用卡。
    P， 新认证支付。
    27， 运通卡支付（信用卡）。
     */
    private String pay_type;
    // 用户银行卡卡号。 首次支付需要传入；历次支付时，传入no_agree
    private String card_no;
    // 用户姓名，为用户在银行预留的姓名信息
    private String acct_name;
    // 用户手机号码。为用户在银行预留的手机号码
    private String bind_mob;
    /*
    证件类型。
    0， 身份证或企业经营证件。
    1， 户口簿。
    2， 护照。
    3， 军官证。
    4， 士兵证。
    5， 港澳居民来往内地通行证。
    6，台湾同胞来往内地通行证。
    7， 临时身份证
    8，外国人居住证。
    9，警官证。
    10，组织机构代码
    X， 其他证件。
    默认为0， 且目前仅支持0
     */
    private String id_type;
    // 证件号码。 对应id_type的相关证件号码
    private String id_no;
    // 签约协议号
    private String no_agree;
}
