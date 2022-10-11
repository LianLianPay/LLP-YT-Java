package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PrcptcdBankInfo {
    // 大额行号
    private String prcptcd;
    // 开户支行名称全称
    private String brabank_name;
}
