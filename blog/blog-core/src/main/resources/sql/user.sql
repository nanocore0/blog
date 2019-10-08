DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50),
  `password` VARCHAR(100),
  `nickname` VARCHAR(50),
  `email` VARCHAR(50),
  `create_time` DATETIME,
  `last_login_time` DATETIME,
  `enabled` TINYINT,
  `account_non_expired` TINYINT,
  `account_non_locked` TINYINT,
  `credentials_non_expired` TINYINT,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8