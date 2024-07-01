
-- test.users definition
CREATE TABLE IF NOT EXISTS `users` (
  `id` binary(16) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(255) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `last_login_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modification_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `user_token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;


-- test.phones definition
CREATE TABLE IF NOT EXISTS `phones` (
  `number` varchar(255) NOT NULL,
  `city_code` varchar(255) NOT NULL,
  `country_code` varchar(255) NOT NULL,
  `user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`number`),
  KEY `FKmg6d77tgqfen7n1g763nvsqe3` (`user_id`),
  CONSTRAINT `FKmg6d77tgqfen7n1g763nvsqe3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=ascii;