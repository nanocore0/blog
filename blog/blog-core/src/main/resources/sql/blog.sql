DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(225),
  `summary` VARCHAR(225),
  `content` LONGTEXT,
  `html_content` LONGTEXT,
  `user_id` INT(11),
  `catalog_id` INT(11),
  `author` VARCHAR(50),
  `create_time` DATETIME,
  `last_edit_time` DATETIME,
  `read_size` INT(8),
  `comment_size` INT(8),
  `vote_size` INT(8),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8