DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100),
  `summary` VARCHAR(225),
  `content` LONGTEXT,
  `html_content` LONGTEXT,
  `user_id` INT,
  `catalog_id` INT,
  `create_time` DATETIME,
  `last_edit_time` DATETIME,
  `read_size` INT,
  `comment_size` INT,
  `vote_size` INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`catalog_id`) REFERENCES `catalog` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8