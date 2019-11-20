package com.fly.bitcoin1112.dao;

import com.fly.bitcoin1112.po.Transaction;
import com.github.pagehelper.Page;
import feign.Param;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(Integer transactionId);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer transactionId);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    Page<Transaction> search(Transaction transaction);

    List<Transaction> selectByBlockId(@Param("blockId") Integer blockId);

    Page<Transaction> selectByBlockIdWithPage(@Param("blockId") Integer blockId);
}