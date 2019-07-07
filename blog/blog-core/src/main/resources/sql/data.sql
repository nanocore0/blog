INSERT INTO `user` (`id`,`username`,`password`,`nickname`,`email`,`avatar`,`create_time`,`last_login_time`,`enabled`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`) VALUES (1,'admin','$2a$10$kzBDmjblXmvICE8SJsrUoON/emMrbiHzYrp6rxF4nhchTV0xtdlKy','香草冰淇淋','pianxian@game.com',NULL,'2019-05-22 00:00:00','2019-05-22 00:00:00',1,1,1,1);
INSERT INTO `user` (`id`,`username`,`password`,`nickname`,`email`,`avatar`,`create_time`,`last_login_time`,`enabled`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`) VALUES (2,'nanocore','$2a$10$60JDi32udQFZFi89ZcubvOafo73evIeFQJhalCYEUqhzKx8i5JN52','一舞翩跹','pianxian@game.com',NULL,'2019-05-22 00:00:00','2019-05-22 00:00:00',1,1,1,1);

INSERT INTO `role` (`id`,`role_name`,`role_desc`) VALUES (1,'admin','管理员');
INSERT INTO `role` (`id`,`role_name`,`role_desc`) VALUES (2,'user','用户');

INSERT INTO `user_role` (`user_id`,`role_id`) VALUES (1,1);
INSERT INTO `user_role` (`user_id`,`role_id`) VALUES (1,2);
INSERT INTO `user_role` (`user_id`,`role_id`) VALUES (2,2);


