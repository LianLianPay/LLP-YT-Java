package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 大额行号查询 请求参数
 */
@Data
@EqualsAndHashCode
public class PrcptcdQueryParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    private String bank_code;
    private String brabank_name;
    private String city_code;
}
