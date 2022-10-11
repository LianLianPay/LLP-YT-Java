package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 大额行号查询 响应参数
 */
@Data
@EqualsAndHashCode
public class PrcptcdQueryResult {
    private String ret_code;
    private String ret_msg;
    // 银行编号
    private String bank_code;
    // 支行信息
    private List<PrcptcdBankInfo> card_list;
    private String sign;
    private String sign_type;
}
