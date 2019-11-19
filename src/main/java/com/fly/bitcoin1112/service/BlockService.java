package com.fly.bitcoin1112.service;

import com.fly.bitcoin1112.po.Block;
import com.github.pagehelper.Page;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface BlockService {

    String syncBlock(String blockhash) throws Throwable;

    @Async
    void syncAllBlocks(String fromBlockhash) throws Throwable;

    List<Block> getRecent();

    Page<Block> getWithPage(Integer page);

    Block getByBlockhash(String blockhash);
}
