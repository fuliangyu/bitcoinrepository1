SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for transaction
-- ----------------------------
DROP TABLE IF EXISTS `transaction1`;
CREATE TABLE `transaction1` (
  `transaction_id` int(11) NOT NULL,
  `txid` char(64) NOT NULL,
  `txhash` char(64) NOT NULL,
  `time` bigint(3) NOT NULL,
  `weight` int(11),
  `status` tinyint(1),
  `size` bigint(20),
  `total_input` double,
  `total_output` double,
  `fees` double,
  `block_id` int(11) NOT NULL,
  PRIMARY KEY (`transaction_id`),
  index `idx_txid`(`txid`),
  index `idx_txhash`(`txhash`),
  index `idx_time`(`time`),
  index `idx_block_id`(`block_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
