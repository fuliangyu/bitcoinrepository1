package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.dao.BlockMapper;
import com.fly.bitcoin1112.dto.PageDTO;
import com.fly.bitcoin1112.po.Block;
import com.fly.bitcoin1112.po.Transaction;
import com.fly.bitcoin1112.service.BlockService;
import com.fly.bitcoin1112.service.TransactionService;
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

@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BlockService blockService;

    @Autowired
    private TransactionService transactionService;

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
    public PageDTO<JSONObject> getWithPage(@RequestParam(required = false, defaultValue = "1") Integer page){
        Page<Block> blocks = blockService.getWithPage(page);
        List<JSONObject> blockJsons = blocks.stream().map(block -> {
            JSONObject jsonblock = new JSONObject();
            jsonblock.put("height",block.getHeight());
            jsonblock.put("blockhash", block.getBlockhash());
            jsonblock.put("time", block.getTime());
            jsonblock.put("miner", block.getMiner());
            jsonblock.put("size", block.getTxSize());
            return jsonblock;
        }).collect(Collectors.toList());
        PageDTO<JSONObject> pagedto = new PageDTO<>();
        pagedto.setList(blockJsons);
        pagedto.setTotal(blocks.getTotal());
        pagedto.setPageSize(blocks.getPageSize());
        pagedto.setCurrentPage(blocks.getPageNum());

        return pagedto;
    }

    @GetMapping("/getInfoByHash")
    public JSONObject getInfoByHash(@RequestParam String blockhash){
        JSONObject blockinfo = new JSONObject();
        Block block = blockService.getByBlockhash(blockhash);
        blockinfo.put("blockhash", block.getBlockhash());
        blockinfo.put("time", block.getTime());
        blockinfo.put("height", block.getHeight());
        blockinfo.put("miner", block.getMiner());
        blockinfo.put("txSize", block.getTxSize());
        blockinfo.put("diffculty", block.getDifficulty());

        List<Transaction> transactions = transactionService.getByBlockId(block.getBlockId());

        blockinfo.put("transactions",null);

        return blockinfo;
    }

    @GetMapping("/getInfoByHeight")
    public JSONObject getInfoByHeight(@RequestParam Integer height){
        return null;
    }
}
