package com.fly.bitcoin1112.dao;

import com.fly.bitcoin1112.po.Record;
import com.github.pagehelper.Page;

public interface RecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(Long recordId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKey(Record record);

    Page<Record> search(Record record);
}