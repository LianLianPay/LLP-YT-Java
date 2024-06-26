package com.lianlianpay.ytapi.params.payserverapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡绑卡请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardOneBindParams {
    private String api_version;
    // 当前版本为1.0。长度6，必传。

    private String sign;
    // 签名，详见签名机制。商户请求签名错误排查步骤。长度无限制，必传。

    private String sign_type;
    // RSA。长度3，必传。

    private String time_stamp;
    // 时间戳，格式为yyyyMMddHHmmss，HH以24小时为准，如20170309143712。当time_stamp与连连服务器的时间(北京时间)之间的误差超过30分钟时，该次请求将返回交易已过期。长度14，必传。

    private String platform;
    // 平台来源标识。定义用户来源，可以使多个商户号共享一套用户信息，使用前请联系连连技术支持部以确定您的商户号是否满足相关配置条件。长度18，可选。

    private String oid_partner;
    // 商户编号，是商户在连连支付支付平台上开设的商户号码，为18位数字，如：201304121000001004。测试阶段可以先用测试商户号测试。长度18，必传。

    private String user_id;
    // 用户编号。商户系统内对用户的唯一编码，可以为自定义字符串，加密密文或者邮箱等可以唯一定义用户的标识。长度40，必传。

    private String no_order;
    // 商户订单号。为商户系统内对订单的唯一编号，保证唯一。连连会根据no_order创建连连订单号oid_paybill，如no_order已有对应连连订单号oid_paybill，则将请求视为重复订单请求。重复发起订单请求时， 请求中的参数信息需与原创单时一致。长度32，必传。

    private String dt_order;
    // 商户订单时间。格式为yyyyMMddHHmmss，HH以24小时为准，如20180130161010。长度14，必传。

    private String risk_item;
    // 风险控制参数。连连风控部门要求商户统一传入风险控制参数字段，字段值为json字符串的形式。传入示例及要求见风险控制说明。长度不限，必传。

    private String notify_url;
    // 接收异步通知的线上地址。连连支付支付平台在用户支付成功后通知商户服务端的地址。如http://test.lianlianpay.com.cn/help/notify.php。异步通知处理规则及详情见异步通知。长度64，必传。

    private String id_type;
    // 证件类型。0：身份证或企业经营证件；1：户口簿；2：护照；3：军官证；4：士兵证；5：港澳居民来往内地通行证；6：台湾同胞来往内地通行证；7：临时身份证；8：外国人居住证；9：警官证；10：组织机构代码；X：其他证件。目前仅支持身份证，不传则默认为身份证。长度不限，必传。

    private String id_no;
    // 证件号码。长度64，必传。

    private String acct_name;
    // 用户姓名，为用户在银行预留的姓名信息。长度不限，必传。

    private String bind_mob;
    // 用户在银行预留的手机号码。长度11，必传。

    private String url_return;
    // 传递该值，当签约绑卡成功后，在绑卡成功页面激活"返回"按钮，用户点击返回后跳转向该地址(Get请求)。如不传则返回按钮不显示。长度128，可选。

    private String bind_card_type;
    // 绑卡方式：TOKEN_SIGN：一键绑卡；H5_SIGN：H5绑卡，暂只支持招行。不传默认为一键绑卡。长度不限，可选。

    private String pay_type;
    // 支付方式（H5绑卡模式下必传）：2：快捷支付（借记卡）；3：快捷支付（信用卡）；D：认证支付（借记卡）；P：新认证支付（借记卡）；M：游易付（借记卡）；N：游易付（信用卡）。长度1，可选。

    private String card_no;
    // 用户银行卡卡号，H5绑卡模式必传。长度15~19，可选。

    private String card_type;
    // 卡类型，一键绑卡模式下可指定卡类型：2-借记卡；3-信用卡。长度1，可选。

    private String bank_code;
    // 银行编码，一键绑卡模式下可指定银行。长度8，可选。
}