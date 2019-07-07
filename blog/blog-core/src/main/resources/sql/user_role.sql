DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` INT(11),
  `role_id` INT(11),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8