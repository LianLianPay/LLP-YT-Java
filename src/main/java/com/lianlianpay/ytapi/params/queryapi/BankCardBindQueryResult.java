package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户签约银行卡列表查询 响应参数
 */
@Data
@EqualsAndHashCode
public class BankCardBindQueryResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
    // 用户编号
    private String user_id;
    // 本次查询返回的总记录条数
    private Integer count;
    // 协议号结果集。 按照签约时间倒序排列， 该参数不参与签名
    List<BankCardBindAgreement> agreement_list;
}
