package com.lianlianpay.ytapi.params.queryapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收款结果查询 请求参数
 */
@Data
@EqualsAndHashCode
public class OrderQueryParams {
    private String oid_partner;
    private String sign;
    private String sign_type;
    // 原请求中商户订单号。 为商户系统内对订单的唯一编号
    private String no_order;
    // 商户订单时间。格式为yyyyMMddHHmmss
    private String dt_order;
    // 连连付款单号
    private String oid_paybill;
    /*
    查询版本号， 默认1.0。
    传1.1时， 查询返回结果中新增memo、bank_name字段。
    传4.0时，查询返回结果中新增reqchnl_serialno、finchnl_serialno、channel_user_id、ext_data字段。
     */
    private String query_version;
}
