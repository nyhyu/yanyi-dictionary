package com.yanyi.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URLDecoder;

public class HttpRequestUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    /**
     * post请求
     * @param url         url地址
     * @param jsonParam     参数
     * @return
     */
    public static String httpPost(String url,JSONObject jsonParam){
        //post请求返回结果
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);
        String res = "";
        try {
            if (null != jsonParam) {
                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                method.setEntity(entity);
            }
            HttpResponse result = httpClient.execute(method);
            url = URLDecoder.decode(url, "UTF-8");
            /**请求发送成功，并得到响应**/
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                try {
                    /**读取服务器返回过来的json字符串数据**/
                    res = EntityUtils.toString(result.getEntity());
                } catch (Exception e) {
                    logger.error("post请求提交失败:" + url, e);
                }
            }
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        }
        return res;
    }


    /**
     * 发送get请求
     * @param url    路径
     * @return
     */
    public static String httpGet(String url){
        //get请求返回结果
        String res = null;
        try {
            HttpClient client = HttpClients.createDefault();
            //发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                res = EntityUtils.toString(response.getEntity());
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return res;
    }
}