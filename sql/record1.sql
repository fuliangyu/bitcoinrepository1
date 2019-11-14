SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for record1
-- ----------------------------
DROP TABLE IF EXISTS `record1`;
CREATE TABLE `record1`
(
  `record_id` bigint(20) not null auto_increment,
  `address`   varchar(70),
  `type`      tinyint(1),
  `amount`    double,
  `transaction_id`    int(11) not null,
  PRIMARY KEY (`record_id`),
  index `idx_txid` (`txid`),
  index `idx_address` (`address`),
  index `idx_transaction_id` (`transaction_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  auto_increment = 1;
