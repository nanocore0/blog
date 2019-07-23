DROP TABLE IF EXISTS `vote`;
CREATE TABLE `vote` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT,
  `blog_id` INT,
  `status` TINYINT DEFAULT 1,
  `create_time` TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8