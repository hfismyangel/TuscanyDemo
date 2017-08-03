package com.jnshu.util;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hfismyangel@163.com on 2017/7/13.
 */
public class MailTest {

 public static void main(String[] args) throws IOException {
    final String url = "http://api.sendcloud.net/apiv2/mail/send";
    final String apiUser = "hfismyangel_test_oVD2NO";
    final String apiKey = "KcCVOeAF6Uy698RH";

    HttpClient httpclient = new DefaultHttpClient();
    HttpPost httPost = new HttpPost(url);

    List params = new ArrayList();
    // 您需要登录SendCloud创建API_USER，使用API_USER和API_KEY才可以进行邮件的发送。
    params.add(new BasicNameValuePair("apiUser", apiUser));
    params.add(new BasicNameValuePair("apiKey", apiKey));
    params.add(new BasicNameValuePair("from", "hfismyangel@163.com"));
    params.add(new BasicNameValuePair("fromName", ""));
    params.add(new BasicNameValuePair("to", "hfismyange@163.com"));
    params.add(new BasicNameValuePair("subject", "来自SendCloud的第一封邮件！"));
    params.add(new BasicNameValuePair("html", "你太棒了！你已成功的从SendCloud发送了一封测试邮件，接下来快登录前台去完善账户信息吧！"));

    httPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
    // 请求
    HttpResponse response = httpclient.execute(httPost);
    // 处理响应
    if (response.getStatusLine().getStatusCode() == 200) { // 正常返回,httpstatus.sc_ok为200状态码
        // 读取xml文档
        String result = EntityUtils.toString(response.getEntity());
        System.out.println(result);
    } else {
        System.err.println("error");
    }
    httPost.releaseConnection();
}
}

