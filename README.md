# LLP-YT-Java

连连银通产品-Java示例工程

欢迎来到连连银通产品开放平台API接口的Java示例代码仓库， 本仓库包含示例代码及必要的说明。

## 主要内容：

Demo仅做参考，请仔细阅读Demo代码，如有问题及时群内连连技术技术。

### 前置要求：
Java JDK版本为1.8及以上

### 使用说明
1、config/LLianPayContant.java 保存测试商户号、测试商户号对应私钥、连连公钥，可视情况进行替换。<br/>
2、security/LLianPayYtSignature.java 包含签名、验签、本地加密方法。<br/>
3、client/LLianPayClient.java 发起请求方法。<br/>
4、部分Demo需要用debug打断点方式进行调试，请自行阅读注释。<br/>
5、部分Demo是无法直接运行的，需要在代码中添加相应个人信息后方可运行（手机号、证件号、银行卡号等）。<br/>

### Demo说明（持续完善中）
#### 银行卡签约：
* 银行卡签约申请/验证：BankCardBindDemo.java https://open.lianlianpay.com/apis/bankCard-signing.html

#### 收款：
* 收银台支付创单：PayServerApiPayCreateBillDemo.java https://open.lianlianpay.com/apis/unified-payment.html
* 银行卡支付：BankCardPayDemo.java https://open.lianlianpay.com/apis/signingPayment-application.html
* 收款结果查询：OrderQueryDemo.java https://open.lianlianpay.com/apis/receive-money-result-query.html

#### 付款：
* 付款结果查询：QueryPaymentDemo.java https://open.lianlianpay.com/apis/instant-result-query.html

#### 退款：
* 退款结果查询：RefundQueryDemo.java https://open.lianlianpay.com/apis/refund-query.html

#### 通用：
* 卡bin查询：BankCardBinDemo.java https://open.lianlianpay.com/apis/card-bin-query.html
* 支持银行查询：SupportBankQueryDemo.java https://open.lianlianpay.com/apis/support-bank-list-query.html
* 大额行号查询：PrcptcdQueryDemo.java https://open.lianlianpay.com/apis/prcptcd-query.html
* 用户签约银行卡列表查询：BankCardBindQueryDemo.java https://open.lianlianpay.com/apis/bind-card-list-query.html
* 银行卡解约：BankcardUnbindDemo.java https://open.lianlianpay.com/apis/unbind-card.html
* 账户余额查询接口：TraderAcctQueryDemo.java https://open.lianlianpay.com/apis/account-balance-query.html

