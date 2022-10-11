package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 卡bin查询 请求参数
 */
@Data
@EqualsAndHashCode
public class BankCardBinParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    private String card_no;
}
