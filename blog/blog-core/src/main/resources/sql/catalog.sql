DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),
  `user_id` INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8