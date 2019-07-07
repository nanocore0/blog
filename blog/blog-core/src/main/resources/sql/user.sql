DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50),
  `password` VARCHAR(100),
  `nickname` VARCHAR(50),
  `email` VARCHAR(50),
  `avatar` VARCHAR(100),
  `create_time` DATETIME,
  `last_login_time` DATETIME,
  `enabled` INT(2),
  `account_non_expired` INT(2),
  `account_non_locked` INT(2),
  `credentials_non_expired` INT(2),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8