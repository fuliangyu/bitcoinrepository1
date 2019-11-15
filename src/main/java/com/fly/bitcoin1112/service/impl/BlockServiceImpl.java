package com.fly.bitcoin1112.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinRest;
import com.fly.bitcoin1112.dao.BlockMapper;
import com.fly.bitcoin1112.dao.TransactionMapper;
import com.fly.bitcoin1112.po.Block;
import com.fly.bitcoin1112.po.Transaction;
import com.fly.bitcoin1112.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BlockServiceImpl implements BlockService{
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public String syncBlock(String blockhash) {
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
    public void syncAllBlocks(String fromBlockhash) {
        String tempBlockhash = fromBlockhash;
        while(tempBlockhash !=null){
            tempBlockhash =syncBlock(tempBlockhash);
        }
    }
}
