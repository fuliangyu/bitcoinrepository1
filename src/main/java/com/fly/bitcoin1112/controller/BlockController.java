package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.dao.BlockMapper;
import com.fly.bitcoin1112.po.Block;
import com.fly.bitcoin1112.service.BlockService;
import com.fly.bitcoin1112.service.impl.BlockServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BlockService blockService;

    @GetMapping("/selectByPrimaryKey")
    public Block SelectByPrimaryKey(Integer blockId){
        Block block = this.blockMapper.selectByPrimaryKey(blockId);
        return block;
    }

    @GetMapping("/search")
    public PageInfo<Block> search(@RequestParam(required = false,defaultValue = "1")Integer pageNum, Block block){
        PageHelper.startPage(pageNum,3);
        Page<Block> blockPage = blockMapper.search(block);
        PageInfo<Block> blockPageInfo = blockPage.toPageInfo();
        return blockPageInfo;
    }

    @GetMapping("/deleteByPrimaryKey")
    public int DeleteByPrimaryKey(Integer blockId){
        final int a = this.blockMapper.deleteByPrimaryKey(blockId);
        return a;
    }
    @GetMapping("/getRecent")
    public List<JSONObject> getRecent(){
        List<Block> blocks = blockService.getRecent();
        List<JSONObject> blockJsons = blocks.stream().map(block -> {
            JSONObject blockJson = new JSONObject();
            blockJson.put("blockhash", block.getBlockhash());
            blockJson.put("height", block.getHeight());
            blockJson.put("time", block.getTime());
            blockJson.put("miner", block.getMiner());
            blockJson.put("txSize", block.getTxSize());
            return blockJson;
        }).collect(Collectors.toList());;
        return blockJsons;
    }

    @GetMapping("/getWithPage")
    public List<JSONObject> getWithPage(@RequestParam(required = false, defaultValue = "1") Integer page){
        return null;
    }

    @GetMapping("/getInfoByHash")
    public JSONObject getInfoByHash(@RequestParam String blockhash){
        return null;
    }

    @GetMapping("/getInfoByHeight")
    public JSONObject getInfoByHeight(@RequestParam Integer height){
        return null;
    }
}
