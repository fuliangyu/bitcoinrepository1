package com.fly.bitcoin1112.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinRest;
import com.fly.bitcoin1112.dao.TransactionMapper;
import com.fly.bitcoin1112.po.Transaction;
import com.fly.bitcoin1112.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private BitcoinRest bitcoinRest;

    @Override
    public void syncTransaction(String txid,Integer blockId,Long time) {
        JSONObject transactionJson = bitcoinRest.getTransaction("txhash");
        Transaction transaction = new Transaction();

        transaction.setBlockId(blockId);
        transaction.setTxhash(transactionJson.getString("txid"));
        transaction.setTime(time);
        transaction.setStatus(transactionJson.getBoolean(String.valueOf(0)));
        transaction.setWeight(transactionJson.getInteger("weight"));
        transaction.setSize(transactionJson.getLong("size"));

        transactionMapper.insert(transaction);
    }
}
