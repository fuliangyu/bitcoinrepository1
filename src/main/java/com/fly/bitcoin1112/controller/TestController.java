package com.fly.bitcoin1112.controller;

import com.alibaba.fastjson.JSONObject;
import com.fly.bitcoin1112.client.BitcoinJsonRpc;
import com.fly.bitcoin1112.client.BitcoinJsonRpcImpl;
import com.fly.bitcoin1112.client.BitcoinRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private BitcoinJsonRpcImpl bitcoinJsonRpc;

    @GetMapping("/hello")
    public String hello() throws Throwable {
//        JSONObject chainInfo = bitcoinRest.getChainInfo();
//        JSONObject blockhashByHeight = bitcoinRest.getBlockhashByHeight(10);
//        JSONObject blockNoTxDetails = bitcoinRest.getBlockNoTxDetails("00000000000021835836d464728e1235e207f299a314498caf770afeb30a4997");
//        JSONObject blockInfo = bitcoinRest.getBlockInfo("00000000000021835836d464728e1235e207f299a314498caf770afeb30a4997");
//        List<JSONObject> blockHeaders = bitcoinRest.getBlockHeaders(5, "00000000000000704df5ce37f68cc0680f8f39ab93fe136f242373e8cdacc26f");
//        JSONObject tx = bitcoinRest.getTransaction("c7b9185e870df17ff9b6942eb44cdb714dcba24d5aa85edf19a98aadf8e263a4");
//        JSONObject mempoolInfo = bitcoinRest.getMempoolInfo();
//        JSONObject mempoolContents = bitcoinRest.getMempoolContents();
//        JSONObject utxo = bitcoinRest.getUTXO("c7b9185e870df17ff9b6942eb44cdb714dcba24d5aa85edf19a98aadf8e263a4", 0);
        JSONObject jsonRpcRawTransaction = bitcoinJsonRpc.getRawTransaction("c7b9185e870df17ff9b6942eb44cdb714dcba24d5aa85edf19a98aadf8e263a4");


        return null;
    }
}
