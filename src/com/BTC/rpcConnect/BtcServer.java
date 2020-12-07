package com.BTC.rpcConnect;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类用于调用比特币节点的不同功能和方法，并返回相应的结果
 */
public class BtcServer {
    private static final String RPCURL = "http://127.0.0.1:8332";
    private static final String USER ="user";
    private static final String PASSWORD = "pwd";
    public String getBestBlockHash(){
        String json = rpcUtils.prepareJSON("getbestblockhash");
        Map<String,String> map = new HashMap<>();
        map.put("Encoding","UTF-8");
        map.put("Content-type","application/json");
        map.put("Authorization","Basic " + rpcUtils.base64Str(USER +":"+PASSWORD));
        rpcResult result = rpcUtils.DoPost(RPCURL,map,json);
        if (result.getCode()== HttpStatus.SC_OK){
            String data = result.getData();
        }
        return null;
    }
}
