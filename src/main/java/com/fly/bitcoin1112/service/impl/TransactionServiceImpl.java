package com.fly.bitcoin1112.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinRest;
import com.fly.bitcoin1112.dao.RecordMapper;
import com.fly.bitcoin1112.dao.TransactionMapper;
import com.fly.bitcoin1112.po.Record;
import com.fly.bitcoin1112.po.Transaction;
import com.fly.bitcoin1112.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private RecordServiceImpl recordService;

    @Autowired
    private BitcoinRest bitcoinRest;

    @Override
    public void syncTransaction(String txid,Integer blockId,Long time) throws Throwable {
        JSONObject transactionJson = bitcoinRest.getTransaction("txhash");
        Transaction transaction = new Transaction();

        transaction.setBlockId(blockId);
        transaction.setTxhash(transactionJson.getString("txid"));
        transaction.setTime(time);
        transaction.setStatus(transactionJson.getBoolean("type"));
        transaction.setWeight(transactionJson.getInteger("weight"));
        transaction.setSize(transactionJson.getLong("size"));

        transactionMapper.insert(transaction);

        Integer transactionId = transaction.getTransactionId();

        //insert transaction record
        List<JSONObject> vout1 = transactionJson.getJSONArray("vout").toJavaList(JSONObject.class);
        for (JSONObject vout:vout1) {
            recordService.syncRecord(vout,transactionId);
        }

        List<JSONObject> vin1 = transactionJson.getJSONArray("vin").toJavaList(JSONObject.class);
        for(JSONObject vin : vin1){
            recordService.syncTxDetailVin(vin,transactionId);
        }
    }

    @Override
    public List<Transaction> getByBlockId(Integer blockId) {
        List<Transaction> transactions = transactionMapper.selectByBlockId(blockId);
        return transactions;
    }

}
