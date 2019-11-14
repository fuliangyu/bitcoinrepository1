package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.dao.RecordMapper;
import com.fly.bitcoin1112.po.Record;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class RecordController {

    @Autowired
    private RecordMapper recordMapper;

    @GetMapping("/selectByPrimaryKey")
    public Record SelectByPrimaryKey(Long recordid){
        Record record = this.recordMapper.selectByPrimaryKey(recordid);
        return record;
    }

    @GetMapping("/search")
    public PageInfo<Record> search(@RequestParam(required = false,defaultValue = "1")Integer pageNum,Record record){
        PageHelper.startPage(pageNum,3);
        Page<Record> recordPage = recordMapper.search(record);
        PageInfo<Record> recordPageInfo = recordPage.toPageInfo();
        return recordPageInfo;
    }

    @GetMapping("/deleteByPrimaryKey")
    public int DeleteByPrimaryKey(Long recordid){
        final int a = this.recordMapper.deleteByPrimaryKey(recordid);
        return a;
    }

    @GetMapping("/getInfoByAddress")
    public JSONObject getInfoByAddress(@RequestParam String address){
        return null;
    }
}
