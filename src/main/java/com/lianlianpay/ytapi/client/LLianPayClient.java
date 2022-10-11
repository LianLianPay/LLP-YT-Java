package com.lianlianpay.ytapi.client;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;

public class LLianPayClient {
    private static Logger log = LoggerFactory.getLogger(LLianPayClient.class);

    public String sendRequest(String url, String body) {
        if (url == null || "".equals(url)) {
            throw new RuntimeException("请求URL非法");
        }
        log.info(String.format("请求URL：%s", url));
        log.info(String.format("请求参数：%s", body));

        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=utf-8");
        try {
            StringEntity stringEntity = new StringEntity(body, "UTF-8");
            stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, "UTF-8"));
            // 设置请求主体
            post.setEntity(stringEntity);
            // 发起交易
            HttpResponse resp = LLianPayHttpClient.getLLianPayHttpClient().execute(post);
            int ret = resp.getStatusLine().getStatusCode();
            if (ret == HttpStatus.SC_OK) {
                // 响应分析
                HttpEntity entity = resp.getEntity();

                BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
                StringBuffer responseString = new StringBuffer();
                String result = br.readLine();
                while (result != null) {
                    responseString.append(result);
                    result = br.readLine();
                }
                log.info(String.format("响应结果：%s", responseString));
                return responseString.toString();
            }
            throw new RuntimeException("请求结果异常，响应状态码为：" + ret);
        } catch (ConnectTimeoutException cte) {
            log.error(cte.getMessage());
            throw new RuntimeException(cte);
        } catch (SocketTimeoutException cte) {
            log.error(cte.getMessage());
            throw new RuntimeException(cte);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
