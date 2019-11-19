package com.fly.bitcoin1112.service;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.po.Record;

import java.util.List;

public interface RecordService {
    void syncRecord(JSONObject vout,Integer transactionId);


    void syncTxDetailVin(JSONObject vin,Integer transactionId) throws Throwable;

    List<Record> getByRecordId(Integer transactionId);
}
