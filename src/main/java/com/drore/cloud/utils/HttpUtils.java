package com.drore.cloud.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpUtils {
    public static String httpGet(String get_url) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            URL url = new URL(get_url);
            URI uri = new URI(url.getProtocol(), (url.getHost() + (url.getPort() == -1 ? "" : ":" + url.getPort())), url.getPath(), url.getQuery(), null);
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response;
            response = httpclient.execute(httpGet);
            org.apache.http.HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String postJson(String url, JSONObject parms) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(new StringEntity(JSON.toJSONString(parms), "utf-8"));
            httpPost.setHeader("Content-Type", "application/json");
            CloseableHttpResponse response;
            response = httpclient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            return body;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String postForm(String url, Map<String, String> parms) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Set<String> keySet = parms.keySet();
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String key : keySet) {
            list.add(new BasicNameValuePair(key, parms.get(key)));
        }
        try {
            HttpPost e = new HttpPost(url);
            e.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
            e.setHeader("Content-Type", "application/x-www-form-urlencoded");
            CloseableHttpResponse response = httpclient.execute(e);
            HttpEntity entity = response.getEntity();
            String body = EntityUtils.toString(entity);
            return body;
        } catch (IOException var8) {
            var8.printStackTrace();
            return "{\"errCode\":\"8500\"}";
        }
    }

    public static JSONObject postForm2Obj(String url, Map<String, String> parms) {
        try {
            String result = postForm(url, parms);
            return JSON.parseObject(result);
        } catch (Exception var8) {
            var8.printStackTrace();
            return JSON.parseObject("{\"errCode\":\"8500\"}");
        }
    }
}
