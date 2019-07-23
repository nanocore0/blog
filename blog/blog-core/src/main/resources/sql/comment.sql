DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(225),
  `user_id` INT,
  `blog_id` INT,
  `create_time` TIMESTAMP,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8