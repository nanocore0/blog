DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `permission_name` VARCHAR(50),
  `permission_tag` VARCHAR(50),
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8