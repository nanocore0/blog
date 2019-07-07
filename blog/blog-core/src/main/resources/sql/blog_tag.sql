DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `blog_id` INT(11),
  `tag_id` INT(11),
  FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`),
  FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8