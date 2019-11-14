package com.fly.bitcoin1112.dao;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.po.Block;
import com.github.pagehelper.Page;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(Integer blockId);

    int insert(Block record);

    int insertSelective(Block record);

    Block selectByPrimaryKey(Integer blockId);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    Page<Block> search(Block block);

}