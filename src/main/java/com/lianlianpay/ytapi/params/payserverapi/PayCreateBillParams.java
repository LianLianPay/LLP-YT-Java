package com.lianlianpay.ytapi.params.payserverapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收银台支付创单 请求参数
 */
@Data
@EqualsAndHashCode
public class PayCreateBillParams {
    // 当前版本为1.0
    private String api_version;
    private String sign;
    private String sign_type;
    private String time_stamp;
    // 平台来源标识。 定义用户来源，可以使多个商户号共享一套用户信息， 使用前请联系连连技术支持部以确定您的商户号是否满足相关配置条件
    private String platform;
    private String oid_partner;
    private String user_id;
    /*
    虚拟商品销售：101001。
    实物商品销售：109001。当busi_partner与您的商户号的业务属性不相符时， 该次请求将返回请求无效
     */
    private String busi_partner;
    private String no_order;
    private String dt_order;
    private String name_goods;
    private String info_order;
    private String money_order;
    private String notify_url;
    // 传递该值，当支付成功后，在收银台页面激活"返回商户"按钮，用户点击返回后跳转向该地址(POST)。如不传则返回按钮不显示
    private String url_return;
    // 传递该值，当支付过程中，在收银台页面左下角激活"返回"按钮，用户点击返回后跳转向该地址(POST)。如不传则返回按钮不显示。
    private String back_url;
    private Integer valid_order;
    // 分账信息。 用于有分账需求的交易
    private String shareing_data;
    private String risk_item;
    /*
    支付产品标识。
    0， 快捷收款。
    1， 认证收款。
    2， 网银收款。
    5， 新认证收款。
    12， 手机网银收款 。
     */
    private String flag_pay_product;
    /*
    应用渠道标识。
    0， App-Android。
    1， App-iOS。
    2， Web。
    3， H5。
    注意:
    当flag_pay_product为12（即手机银行APP支付时）, flag_chnl仅支持传入0或1。
     */
    private String flag_chnl;
    private String id_type;
    private String id_no;
    private String acct_name;
    private Integer card_no;
    // 签约协议编号
    private String no_agree;
    private String bank_code;
    private String card_type;
    // 主题配色， 用以预设收银台或认证中心的主题配色
    private String theme;
    /*
    是否显示收银台/认证中心的页头， 默认为show。
    show - 显示。
    hide - 隐藏。
     */
    private String show_head;
}
