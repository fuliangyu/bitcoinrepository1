package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinRest;
import com.fly.bitcoin1112.dao.BlockMapper;
import com.fly.bitcoin1112.po.Block;
import com.fly.bitcoin1112.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("syncdata")
public class SyncDataController {

    @Autowired
    private BlockService blockService;

    @PostMapping("/fullImport")
    public void fullImport(@RequestParam(required = false,defaultValue = "000000000933ea01ad0ee984209779baaec3ced90fa3f408719526f8d77f4943")String blockhash) throws Throwable {
        blockService.syncAllBlocks(blockhash);
    }
}
