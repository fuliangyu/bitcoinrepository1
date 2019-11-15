package com.fly.bitcoin1112.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.po.Record;
import com.fly.bitcoin1112.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Override
    public void syncRecord(JSONObject vout,Integer transactionId) {
        Record record = new Record();
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        String address = (String) addresses.get(0);
        record.setAddress(vout.getString(address));
        record.setAmount(vout.getDouble("value"));
        record.setType(vout.getBoolean(String.valueOf(0)));
        record.setTransactionId(transactionId);
    }
}
