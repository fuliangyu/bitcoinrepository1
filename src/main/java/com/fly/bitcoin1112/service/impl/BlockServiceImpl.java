package com.fly.bitcoin1112.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinRest;
import com.fly.bitcoin1112.dao.BlockMapper;
import com.fly.bitcoin1112.dao.TransactionMapper;
import com.fly.bitcoin1112.po.Block;
import com.fly.bitcoin1112.po.Transaction;
import com.fly.bitcoin1112.service.BlockService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public String syncBlock(String blockhash) throws Throwable {
        JSONObject blockJson = bitcoinRest.getBlockNoTxDetails(blockhash);
        Block block = new Block();
        block.setBlockhash(blockJson.getString("hash"));
        block.setHeight(blockJson.getInteger("height"));
        block.setTime(blockJson.getLong("time"));
        block.setMiner(blockJson.getString("miner"));
        block.setDifficulty(blockJson.getDouble("difficulty"));
        block.setTxSize(blockJson.getInteger("nTx"));

        blockMapper.insert(block);
        Integer blockId = block.getBlockId();
        Long time = block.getTime();
        // insert Transaction
        ArrayList<String> txnumbers = (ArrayList<String>) blockJson.get(("tx"));
        for (String txid : txnumbers){
           transactionService.syncTransaction(txid,blockId,time);
        }

        String nextblockhash = blockJson.getString("nextblockhash");
        return  nextblockhash;
    }

    @Override
    public void syncAllBlocks(String fromBlockhash) throws Throwable {
        logger.info("Block synchronization start");
        String tempBlockhash = fromBlockhash;
        while(tempBlockhash !=null){
            tempBlockhash =syncBlock(tempBlockhash);
        }
        logger.info("End of block synchronization");
    }

    @Override
    public List<Block> getRecent() {
        List<Block> blocks = blockMapper.selectRecent();
        return blocks;
    }

    @Override
    public Page<Block> getWithPage(Integer page) {
        PageHelper.startPage(page, 20);
        Page<Block> blocks = blockMapper.selectWithPage();
        return blocks;
    }

    @Override
    public Block getByBlockhash(String blockhash) {
        Block block = blockMapper.selectByBlockhash(blockhash);
        return block;
    }
}
