package com.fly.bitcoin1112.service;

public interface TransactionService {
     void syncTransaction(String txid,Integer blockId,Long time) throws Throwable;


}
