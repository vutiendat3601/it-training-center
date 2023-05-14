CREATE TABLE `user` (
  `user_uuid` varchar(36) NOT NULL,
  `first_name` varchar(12) NOT NULL,
  `last_name` varchar(36) NOT NULL,
  `email` varchar(320) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `email_verified` bit(1) NOT NULL DEFAULT b'0',
  `email_verification_code` varchar(128) DEFAULT NULL,
  `role_id` char(12) DEFAULT NULL,
  PRIMARY KEY (`user_uuid`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_role` (
  `role_id` char(12) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE `user`
ADD FOREIGN KEY (`role_id`) REFERENCES `user_role` (`role_id`);