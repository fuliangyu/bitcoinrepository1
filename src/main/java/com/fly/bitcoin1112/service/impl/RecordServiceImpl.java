package com.fly.bitcoin1112.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinRest;
import com.fly.bitcoin1112.dao.RecordMapper;
import com.fly.bitcoin1112.po.Record;
import com.fly.bitcoin1112.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public void syncRecord(JSONObject vout,Integer transactionId) {
        Record record = new Record();
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if(addresses != null){
            String address = (String) addresses.get(0);
            record.setAddress(vout.getString(address));
            record.setAmount(vout.getDouble("value"));
            record.setType(vout.getBoolean("type"));
            record.setTransactionId(transactionId);

            recordMapper.insert(record);
        }
    }
    @Override
    public void syncTxDetailVin(JSONObject vin,Integer transactionId){
        Record record = new Record();
        record.setTransactionId(transactionId);
        record.setType(Boolean.getBoolean("type"));
        String txid1 = vin.getString("txid");
        Integer out = vin.getInteger("vout");
        if (txid1 != null && out != null) {
            JSONObject utxoJson = bitcoinRest.getUTXO(txid1, out);
            List<JSONObject> utxos = utxoJson.getJSONArray("utxos").toJavaList(JSONObject.class);
            JSONObject utxo = utxos.get(0);
            Double amount = utxo.getDouble("value");
            record.setAmount(-amount);
            JSONObject scriptPubKey = utxo.getJSONObject("scriptPubKey");
            JSONArray addresses = scriptPubKey.getJSONArray("addresses");
            if(addresses != null){
                String address = (String) addresses.get(0);
                record.setAddress(address);
                recordMapper.insert(record);
            }
        }
    }

}

