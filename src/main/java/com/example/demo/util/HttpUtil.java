package com.example.demo.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import sun.misc.IOUtils;

/**
 * Created by Administrator on 2018/4/10.
 */
public class HttpUtil {
    private final static Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        logger.info("===========================传入参数开始===========================");
        logger.info(param);
        logger.info("===========================传入参数结束===========================");
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString); // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection(); // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); // 建立实际的连接
            connection.connect(); // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields(); // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }  // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            result = "204";
            logger.info("===========================返回结果开始===========================");
            logger.info(result);
            logger.info("===========================返回结果结束===========================");
        } catch (Exception e) {
            if (e.toString().contains("204")) {
                result = "204";
            } else if (e.toString().contains("400")) {
                result = "400";
            } else {
                result = "500";
            }
            /*System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();*/
        }  // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param url 不带参数的url
     * @return
     */
    public static String sendGet(String url) {
        logger.info("===========================传入参数开始===========================");
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            URL realUrl = new URL(urlNameString); // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection(); // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); // 建立实际的连接
            connection.connect(); // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields(); // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }  // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            result = "204";

        } catch (Exception e) {
            if (e.toString().contains("204")) {
                result = "204";
            } else if (e.toString().contains("400")) {
                result = "400";
            } else {
                result = "500";
            }
            /*System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();*/

        }  // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String sendPost(String url, String data) {

        String result = "";
        try {
            // Configure and open a connection to the site you will send the
            URL realUrl = new URL(url);
            URLConnection urlConnection = realUrl.openConnection();
            // 设置doOutput属性为true表示将使用此urlConnection写入数据
            urlConnection.setDoOutput(true);
            // 定义待写入数据的内容类型，我们设置为application/x-www-form-urlencoded类型
            urlConnection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            // 得到请求的输出流对象
            OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream(), "utf-8");
            // 把数据写入请求的Body
            out.write(data);
            out.flush();
            out.close();

            // 从服务器读取响应
            InputStream inputStream = urlConnection.getInputStream();
            // String encoding = urlConnection.getContentEncoding();
            return "204";
        } catch (IOException e) {
            if (e.toString().contains("204")) {
                result = "204";
            } else if (e.toString().contains("400")) {
                result = "400";
            } else {
                result = "500";
            }
        }
        return result;
    }
}

