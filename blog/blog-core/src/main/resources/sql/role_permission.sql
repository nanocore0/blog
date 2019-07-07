DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` INT(11),
  `permission_id` INT(11),
  FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8