package com.fly.bitcoin1112.client;

import com.alibaba.fastjson.JSONObject;

public interface BitcoinJsonRpc {
    JSONObject getRawTransaction(String txid) throws Throwable;


}

