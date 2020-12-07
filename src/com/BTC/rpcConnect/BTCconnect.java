package com.BTC.rpcConnect;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.BTC.rpcConnect.rpcUtils.base64Str;

public class BTCconnect {
    private static final String RPCUSER = "user";
    private static final String RPCPASSWORD = "pwd";
    private static final String RPCURL ="http://127.0.0.1:8332";
    public static void main(String[] args) {
        //准备JSON-RPC链接的数据
        JSONObject rpcJson= new JSONObject();
        rpcJson.put("id",System.currentTimeMillis());
        rpcJson.put("jsonrpc","2.0");
        rpcJson.put("method","getbestblockhash");//最后一个区块
        //rpcJson.put("params","");

        String json = rpcJson.toJSONString();
        System.out.println(rpcJson.toJSONString());

        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(RPCURL);
        try {
            String authStr = RPCUSER + ":"+RPCPASSWORD;
            String authBase64 = base64Str(authStr);
            post.addHeader("Authorization","Basic " + authBase64);
            post.addHeader("Encoding","UTF-8");
            post.addHeader("Content-type","application/json");
            StringEntity stringEntity = new StringEntity(json);
            post.setEntity(stringEntity);
            HttpResponse response=client.execute(post);

            int code =response.getStatusLine().getStatusCode();
            if (code==200){
                System.out.println("链接成功");
                String result= EntityUtils.toString(response.getEntity());
                System.out.println(result);
            }else{
                System.out.println("链接失败"+code);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
