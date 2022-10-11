package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 支持银行查询 响应参数
 */
@Data
@EqualsAndHashCode
public class SupportBankQueryResult {
    private String ret_code;
    private String ret_msg;
    // 支持银行列表数组
    private List<SupportBank> support_banklist;
}
