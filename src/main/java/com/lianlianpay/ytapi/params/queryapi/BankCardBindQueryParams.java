package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户签约银行卡列表查询 请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardBindQueryParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    // 用户编号
    private String user_id;
    // 平台来源标识
    private String platform;
    /*
    签约协议号所属收款产品，不传则默认为快捷收款。
    2 - 快捷收款。
    D - 认证收款。
    P - 新认证收款。
     */
    private String pay_type;
    // 签约协议编号
    private String no_agree;
    // 用户银行卡卡号
    private String card_no;
    // 查询偏移量， 传0即可
    private Integer offset;
}
