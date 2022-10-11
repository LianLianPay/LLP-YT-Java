package com.lianlianpay.ytapi.params.mpayapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡签约申请 请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardBindParams {
    // 平台来源标识
    private String platform;
    private String user_id;
    private String oid_partner;
    private String sign_type;
    private String sign;
    private String notify_url;
    private String no_order;
    private String dt_order;
    /*
    支付方式。 指定使用的支付方式。
    2， 快捷支付 - 借记卡。
    3， 快捷支付 - 信用卡。
    P， 新认证支付。
    27， 运通卡支付（信用卡）。
     */
    private String pay_type;
    private String risk_item;
    private String card_no;
    private String acct_name;
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
    目前仅支持身份证，不传则默认为身份证。
     */
    private String id_type;
    private String id_no;
}
