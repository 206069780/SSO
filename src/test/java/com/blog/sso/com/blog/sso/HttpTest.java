package com.blog.sso.com.blog.sso;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HttpTest {

    @Test
    public void doGet() throws IOException {
        //http://127.0.0.1:9091/getuid
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http GET请求
        HttpGet httpGet = new HttpGet("http://127.0.0.1:9091/getuid");
        System.out.println(EntityUtils.toString(httpclient.execute(httpGet).getEntity()));

    }
}
