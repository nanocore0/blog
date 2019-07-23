DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `blog_id` INT,
  `tag_id` INT,
  FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8