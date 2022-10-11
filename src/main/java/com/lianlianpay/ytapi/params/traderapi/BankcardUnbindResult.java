package com.lianlianpay.ytapi.params.traderapi;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银行卡解约 响应参数
 */
@Data
@EqualsAndHashCode
public class BankcardUnbindResult {
    private String ret_code;
    private String ret_msg;
    private String sign;
    private String sign_type;
}
