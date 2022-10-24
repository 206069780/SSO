package com.blog.sso.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.UnknownHostException;

@Slf4j
@Component
public class HttpUtil {
    public Object doGet(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet getClient = new HttpGet(url);
        HttpResponse response = client.execute(getClient);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }
        return null;
    }
}
