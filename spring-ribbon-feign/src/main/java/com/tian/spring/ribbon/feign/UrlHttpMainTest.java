package com.tian.spring.ribbon.feign;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianbeiping
 * @Title: UrlHttpMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午2:33
 */
public class UrlHttpMainTest {


    public static void main(String[] args) throws IOException {

        String s = httpGet("http://127.0.0.1:9090/user/all");
        System.out.println(s);

        String s1 = httpPost("http://127.0.0.1:9090/user/add", "{\"id\":\"client\",\"name\":\"tian\"}");

        System.out.println(s1);

    }


    /**
     * json
     *
     * @param getUrl url
     * @param body   json 字符
     * @return
     * @throws IOException
     */
    private static String httpPost(String getUrl, String body) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/json;charset=utf-8");
        return httpDeal(getUrl, "POST", body, null, map);
    }

    /**
     * form
     *
     * @param getUrl url
     * @param body   请求参数应该是 name1=value1&name2=value2 的形式
     * @return
     * @throws IOException
     */
    private static String httpForm(String getUrl, String body) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("Content-Type", "application/x-www-form-urlencoded");
        return httpDeal(getUrl, "POST", body, null, map);
    }

    private static String httpGet(String getUrl) throws IOException {
        return httpDeal(getUrl, "GET", null, null, null);
    }

    /**
     * requestProperty key value
     * Authorization :  Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
     */
    private static String httpDeal(String getUrl, String method, String body, String charset, Map<String, String> requestProperty) throws IOException {
        String result;
        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            // 创建远程url连接对象
            URL url = new URL(getUrl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            httpURLConnection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            httpURLConnection.setRequestMethod(method);
            //设置连接主机服务器的超时时间：15000毫秒
            httpURLConnection.setConnectTimeout(50000);
            //设置读取远程返回的数据时间：60000毫秒
            httpURLConnection.setReadTimeout(20000);
            if (requestProperty != null) {
                for (Map.Entry<String, String> e : requestProperty.entrySet()) {
                    httpURLConnection.setRequestProperty(e.getKey(), e.getValue());
                }
            }

            if ("POST".equalsIgnoreCase(method)) {
                // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
                httpURLConnection.setDoOutput(true);
                // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
                httpURLConnection.setDoInput(true);

                outputStream = httpURLConnection.getOutputStream();

                if (charset == null || "".equals(charset)) {
                    outputStream.write(body.getBytes("UTF-8"));
                } else {
                    outputStream.write(body.getBytes(charset));
                }
            }

            //发送请求
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() == 200) {
                //通过connection连接，获取输入流
                inputStream = httpURLConnection.getInputStream();
                //封装输入流is，并指定字符集
                result = getString(inputStream);
            } else {
                result = "链接失败";
            }

        } catch (IOException e) {
            System.out.println("httpGet connect to exception");
            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
        return result;
    }


    private static String getString(InputStream stream) {

        String result = null;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"))) {
            //存放数据
            StringBuffer sbf = new StringBuffer();
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        } catch (IOException e) {
            System.out.println("InputStream to string  exception");
            return null;
        }
        return result;
    }
}
