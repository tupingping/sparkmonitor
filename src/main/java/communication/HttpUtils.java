package communication;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import conf.ConfigurationManager;
import constant.Constants;
import logger.LogUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @File : HttpUtils.java
 * @Author: tupingping
 * @Date : 2019/7/2
 * @Desc :
 */
public class HttpUtils {
    private static PoolingHttpClientConnectionManager connectionManager;
    private static RequestConfig requestConfig;

    static {
        try{
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(ConfigurationManager.getInteger(Constants.HTTP_CONNECT_SIZE));
            connectionManager.setDefaultMaxPerRoute(connectionManager.getMaxTotal());
            connectionManager.setValidateAfterInactivity(ConfigurationManager.getInteger(Constants.HTTP_VALIDATE_INACTIVITY));
            RequestConfig.Builder configBuilder = RequestConfig.custom();
            configBuilder.setConnectTimeout(ConfigurationManager.getInteger(Constants.HTTP_TIME_OUT));
            configBuilder.setSocketTimeout(ConfigurationManager.getInteger(Constants.HTTP_TIME_OUT));
            configBuilder.setConnectionRequestTimeout(ConfigurationManager.getInteger(Constants.HTTP_TIME_OUT));
            requestConfig = configBuilder.build();
        }catch (Exception e){
            LogUtil.getInstance().error("http connect init failed.", e);
        }
    }

    /**
     * send get-request without data params
     * @param url
     * @return
     */
    public static JSONArray doGet(String url){
        try{
            return doGet(url, new HashMap<String, Object>());
        }catch (Exception e)
        {
            LogUtil.getInstance().error("doGet failed.", e);
            return JSONObject.parseArray("");
        }
    }

    /**
     * send get-request with data params
     * @param url
     * @param params
     * @return
     */
    public static JSONArray doGet(String url, Map<String, Object> params) throws Exception {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for(String key : params.keySet()){
            if(i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
        }

        apiUrl += param;

        String result = null;
        HttpClient httpClient = null;

        if(apiUrl.startsWith("https"))
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        else
            httpClient = HttpClients.createDefault();

        try{
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                InputStream inputStream = entity.getContent();
                result = IOUtils.toString(inputStream, "utf-8");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return JSONObject.parseArray(result);
    }

    public static SSLConnectionSocketFactory createSSLConnectionSocketFactory(){
        SSLConnectionSocketFactory sslConnectionSocketFactory = null;
        //TODO:
        try{
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            }).build();

            sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    return true;
                }
            });
        }catch (Exception e){
            throw new SecurityException(e.getMessage());
        }

        return sslConnectionSocketFactory;
    }

    public static JSONArray doPost(String apiUrl){
        try{
            return doPost(apiUrl, new HashMap<String, Object>());
        }catch (Exception e){
            LogUtil.getInstance().error("doPost failed.", e);
            return JSONObject.parseArray("");
        }
    }

    /**
     * send post request with k-v data
     * @param apiUrl
     * @param params
     * @return
     */
    public static JSONArray doPost(String apiUrl, Map<String, Object> params){
        CloseableHttpClient httpClient = null;
        if(apiUrl.startsWith("https"))
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        else
            httpClient = HttpClients.createDefault();

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());

            for(Map.Entry<String, Object> entry : params.entrySet()){
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }

            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "utf-8");

        }catch (Exception e){

        }finally {
            if(response != null){
                try{
                    EntityUtils.consume(response.getEntity());
                }catch (IOException e){

                }
            }
        }

        return JSONObject.parseArray(httpStr);
    }

    /**
     * send post request with json data
     * @param apiUrl
     * @param json
     * @return
     */
    public static JSONArray doPost(String apiUrl, Object json){
        CloseableHttpClient httpClient = null;
        if(apiUrl.startsWith("https"))
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnectionSocketFactory())
                    .setConnectionManager(connectionManager).setDefaultRequestConfig(requestConfig).build();
        else
            httpClient = HttpClients.createDefault();

        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "utf-8");
            stringEntity.setContentEncoding("utf-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (IOException e) {

        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {

                }
            }
        }
        return JSONObject.parseArray(httpStr);
    }
}
