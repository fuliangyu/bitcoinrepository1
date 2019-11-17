package com.fly.bitcoin1112.service;

import com.alibaba.fastjson.JSONObject;

public interface RecordService {
    void syncRecord(JSONObject vout,Integer transactionId);


    void syncTxDetailVin(JSONObject vin,Integer transactionId);
}
