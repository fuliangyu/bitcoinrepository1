SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for block1
-- ----------------------------
DROP TABLE IF EXISTS `block1`;
CREATE TABLE `block1` (
  `block_id` int(11) NOT NULL,
  `blockhash` char(64) NOT NULL,
  `height` int(11) NOT NULL,
  `time` bigint(20) NOT NULL,
  `miner` varchar(50),
  `tx_size` int(20),
  `difficulty` double,
  PRIMARY KEY (`block_id`),
  index `idx_block`(`blockhash`),
  index index `idx_time` (`time`),
  index index `idx_height` (`height`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 auto_increment=1;
