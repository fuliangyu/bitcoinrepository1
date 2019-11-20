package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.constants.PageConfig;
import com.fly.bitcoin1112.dao.TransactionMapper;
import com.fly.bitcoin1112.dto.PageDTO;
import com.fly.bitcoin1112.po.Block;
import com.fly.bitcoin1112.po.Record;
import com.fly.bitcoin1112.po.Transaction;
import com.fly.bitcoin1112.service.BlockService;
import com.fly.bitcoin1112.service.RecordService;
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
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private RecordService recordService;

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

    @GetMapping("/getByBlockhashWithPage")
    public PageDTO<JSONObject> getByBlockhashWithPage(@RequestParam String blockhash,
                                                      @RequestParam(required = false,defaultValue = "1") Integer page){
        Block block = blockService.getByBlockhash(blockhash);
        Integer blockId = block.getBlockId();
        Page<Transaction> transactionWithPage = transactionService.getByBlockIdWithPage(blockId, page);

        List<JSONObject> txJsons = transactionWithPage.stream().map(tx -> {
            JSONObject txJson = new JSONObject();
            txJson.put("txid", tx.getTxid());
            txJson.put("txhash", tx.getTxhash());
            txJson.put("time", tx.getTime());
            txJson.put("fees", tx.getFees());
            txJson.put("totalOutput", tx.getTotalOutput());

            List<Record> records = recordService.getByRecordId(tx.getTransactionId());
            List<JSONObject> record1Jsons = records.stream().map(txrecord -> {
                JSONObject record1Json = new JSONObject();
                record1Json.put("address", txrecord.getAddress());
                record1Json.put("type", txrecord.getType());
                record1Json.put("amount", Math.abs(txrecord.getAmount()));
                return record1Json;
            }).collect(Collectors.toList());
            txJson.put("records", record1Jsons);
            return txJson;
        }).collect(Collectors.toList());

        PageDTO<JSONObject> pageDTO = new PageDTO<>();
        pageDTO.setTotal(transactionWithPage.getTotal());
        pageDTO.setPageSize(PageConfig.PAGE_SIZE);
        pageDTO.setCurrentPage(transactionWithPage.getPageNum());
        pageDTO.setList(txJsons);

        return pageDTO;
    }
}
