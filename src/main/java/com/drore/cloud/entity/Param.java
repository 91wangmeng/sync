package com.drore.cloud.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2016/9/13.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
@Component
public class Param implements Serializable {
    private static final long serialVersionUID = 6267644879110338104L;
    private Data data;
    private String token;
    @Value("${url}")
    private String url;
    @Value("${appid}")
    private String appid;
    @Value("${appsecret}")
    private String appsecret;
    @Value("${get_token}")
    private String get_token;

    public Param() {
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        initToken();
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void initToken() {
        String token_url = MessageFormat.format(url + get_token, appid, appsecret);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(token_url);
        try {
            HttpResponse response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                JSONObject data = JSON.parseObject(EntityUtils.toString(entity));

                if (data.getIntValue("expires_in") == 7200) {
                    String token = data.getString("token");
                    this.setToken(token);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
