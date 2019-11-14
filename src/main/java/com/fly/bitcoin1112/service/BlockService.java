package com.fly.bitcoin1112.service;

import org.springframework.scheduling.annotation.Async;

public interface BlockService {

    String syncBlock(String blockhash);

    @Async
    void syncAllBlocks(String fromBlockhash);
}
