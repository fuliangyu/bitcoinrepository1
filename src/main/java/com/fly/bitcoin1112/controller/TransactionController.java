package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.dao.TransactionMapper;
import com.fly.bitcoin1112.po.Transaction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionMapper transactionMapper;

    @GetMapping("/selectByPrimaryKey")
    public Transaction SelectByPrimaryKey(Integer transactionId){
        Transaction transaction = this.transactionMapper.selectByPrimaryKey(transactionId);
        return transaction;
    }

    @GetMapping("/search")
    public PageInfo<Transaction> search(@RequestParam(required = false,defaultValue = "1")Integer pageNum, Transaction transaction){
        PageHelper.startPage(pageNum,3);
        Page<Transaction> transactionPage = transactionMapper.search(transaction);
        PageInfo<Transaction> transactionPageInfo = transactionPage.toPageInfo();
        return transactionPageInfo;
    }

    @GetMapping("/deleteByPrimaryKey")
    public int DeleteByPrimaryKey(Integer transactionId){
        final int a = this.transactionMapper.deleteByPrimaryKey(transactionId);
        return a;
    }

    @GetMapping("/getRecentUnconfirmed")
    public List<JSONObject> getRecentUnconfirmed(@RequestParam(required = false, defaultValue = "20") Integer size){
        return null;
    }

    @GetMapping("/getByTxhash")
    public JSONObject getByTxhash(@RequestParam String txhash){
        return null;
    }
}
