package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户余额查询接口 请求参数
 */
@Data
@EqualsAndHashCode
public class TraderAcctQueryParams {
    private String oid_partner;
    private String api_version;
    private String sign;
    private String sign_type;
}
