DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11),
  `blog_id` INT(11),
  `status` INT(1) DEFAULT 1,
  `create_time` TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8