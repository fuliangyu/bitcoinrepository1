package com.fly.bitcoin1112.service;

import com.fly.bitcoin1112.po.Transaction;
import com.github.pagehelper.Page;

import java.util.List;

public interface TransactionService {
     void syncTransaction(String txid,Integer blockId,Long time) throws Throwable;

     List<Transaction> getByBlockId(Integer blockId);

     Page<Transaction> getByBlockIdWithPage(Integer blockId,Integer page);
}
