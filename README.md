```sql
create user 'pinto'@'%' identified by '6389';
GRANT ALL PRIVILEGES ON *.* TO 'pinto'@'%';

CREATE TABLE `f_res_tag` (
  `res_key` bigint(20) NOT NULL,
  `tag_key` int(11) NOT NULL,
  PRIMARY KEY (`res_key`,`tag_key`),
  KEY `pinto_tag_match_pinto_res_res_key_fk` (`res_key`),
  KEY `pinto_tag_match_pinto_tag_tag_key_fk` (`tag_key`),
  CONSTRAINT `pinto_tag_match_pinto_res_res_key_fk` FOREIGN KEY (`res_key`) REFERENCES `pinto_res` (`res_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pinto_tag_match_pinto_tag_tag_key_fk` FOREIGN KEY (`tag_key`) REFERENCES `pinto_tag` (`tag_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `f_res_usr` (
  `usr_key` int(11) NOT NULL,
  `res_key` bigint(20) NOT NULL,
  `gem` int(11) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`usr_key`,`res_key`),
  UNIQUE KEY `usr_key` (`usr_key`,`res_key`),
  KEY `f_res_usr_pinto_usr_usr_key_fk` (`usr_key`),
  KEY `f_res_usr_pinto_res_res_key_fk` (`res_key`),
  CONSTRAINT `f_res_usr_pinto_res_res_key_fk` FOREIGN KEY (`res_key`) REFERENCES `pinto_res` (`res_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_res_usr_pinto_usr_usr_key_fk` FOREIGN KEY (`usr_key`) REFERENCES `pinto_usr` (`usr_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='리소스 소유 유저';

CREATE TABLE `f_var_usr` (
  `work_id` bigint(20) NOT NULL,
  `var_id` bigint(20) NOT NULL,
  `usr_id` int(11) NOT NULL,
  `var_value` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`work_id`,`var_id`,`usr_id`),
  KEY `pinto_var_f_var_usr_work_id_var_id_fk` (`work_id`,`var_id`),
  KEY `pinto_usr_f_var_usr_usr_id_fk` (`usr_id`),
  CONSTRAINT `pinto_var_f_var_usr_work_id_var_id_fk` FOREIGN KEY (`work_id`, `var_id`) REFERENCES `pinto_var` (`work_id`, `var_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `f_work_genre` (
  `work_id` bigint(20) NOT NULL,
  `genre_id` bigint(20) NOT NULL,
  PRIMARY KEY (`work_id`,`genre_id`),
  KEY `f_w_g2` (`genre_id`),
  CONSTRAINT `f_w_g1` FOREIGN KEY (`work_id`) REFERENCES `pinto_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `f_w_g2` FOREIGN KEY (`genre_id`) REFERENCES `pinto_genre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_brabo` (
  `brabo_usr` int(11) NOT NULL,
  `brabo_res` bigint(20) NOT NULL,
  `brabo_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`brabo_usr`,`brabo_res`),
  KEY `fK_usr` (`brabo_usr`),
  KEY `fK_res` (`brabo_res`),
  CONSTRAINT `fk_res` FOREIGN KEY (`brabo_res`) REFERENCES `pinto_res` (`res_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usr` FOREIGN KEY (`brabo_usr`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_cast` (
  `work_id` bigint(20) NOT NULL,
  `cast_id` int(11) NOT NULL,
  `var_id` int(11) NOT NULL,
  `res_no` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `zoom` decimal(5,2) DEFAULT NULL,
  `biasX` decimal(9,8) DEFAULT NULL,
  `biasY` decimal(9,8) DEFAULT NULL,
  PRIMARY KEY (`work_id`,`cast_id`),
  KEY `pinto_work_pinto_cast_work_id_fk` (`work_id`),
  CONSTRAINT `pinto_work_pinto_cast_work_id_fk` FOREIGN KEY (`work_id`) REFERENCES `pinto_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_epi` (
  `work_id` bigint(20) NOT NULL,
  `epi_id` bigint(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `face` varchar(100) NOT NULL,
  `release_yn` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`work_id`,`epi_id`),
  CONSTRAINT `epi_work_fk` FOREIGN KEY (`work_id`) REFERENCES `pinto_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_gem` (
  `gem_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `gem_buyer` int(11) DEFAULT NULL,
  `gem_seller` int(11) DEFAULT NULL,
  `gem_value` int(11) DEFAULT NULL,
  `gem_date` timestamp NULL DEFAULT current_timestamp(),
  `gem_roll_back` tinyint(4) NOT NULL DEFAULT 0,
  PRIMARY KEY (`gem_key`),
  KEY `pinto_gem_gem_key_index` (`gem_key`),
  KEY `pinto_gem_pinto_usr_usr_key_fk` (`gem_buyer`),
  KEY `pinto_gem_pinto_usr_usr_key_fk_2` (`gem_seller`),
  CONSTRAINT `pinto_gem_pinto_usr_usr_key_fk` FOREIGN KEY (`gem_buyer`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pinto_gem_pinto_usr_usr_key_fk_2` FOREIGN KEY (`gem_seller`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_genre` (
  `id` bigint(20) NOT NULL,
  `genre_value` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  UNIQUE KEY `genre_value` (`genre_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_log` (
  `log_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `log_work` bigint(20) DEFAULT NULL,
  `log_date` timestamp NULL DEFAULT current_timestamp(),
  `log_usr` int(11) DEFAULT NULL,
  PRIMARY KEY (`log_key`),
  KEY `pinto_log_pinto_usr_usr_key_fk` (`log_usr`),
  CONSTRAINT `pinto_log_pinto_usr_usr_key_fk` FOREIGN KEY (`log_usr`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_login` (
  `login_id` varchar(30) NOT NULL,
  `usr_key` int(11) DEFAULT NULL,
  PRIMARY KEY (`login_id`),
  KEY `pinto_login_fk` (`usr_key`),
  CONSTRAINT `pinto_login_fk` FOREIGN KEY (`usr_key`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_opt` (
  `work_id` bigint(20) NOT NULL,
  `epi_id` bigint(20) NOT NULL,
  `scene_id` bigint(20) NOT NULL,
  `page_id` bigint(20) NOT NULL,
  `opt_id` bigint(20) NOT NULL,
  `protocol` tinyint(2) NOT NULL DEFAULT 1,
  `opt` longtext NOT NULL DEFAULT 'test',
  PRIMARY KEY (`work_id`,`epi_id`,`scene_id`,`page_id`,`opt_id`),
  CONSTRAINT `opt_page_fk` FOREIGN KEY (`work_id`, `epi_id`, `scene_id`, `page_id`) REFERENCES `pinto_page` (`work_id`, `epi_id`, `scene_id`, `page_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_page` (
  `work_id` bigint(20) NOT NULL,
  `epi_id` bigint(20) NOT NULL,
  `scene_id` bigint(20) NOT NULL,
  `page_id` bigint(20) NOT NULL,
  PRIMARY KEY (`work_id`,`epi_id`,`scene_id`,`page_id`),
  CONSTRAINT `page_scene_fk` FOREIGN KEY (`work_id`, `epi_id`, `scene_id`) REFERENCES `pinto_scene` (`work_id`, `epi_id`, `scene_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_res` (
  `res_key` bigint(20) NOT NULL AUTO_INCREMENT,
  `res_cat` tinyint(4) DEFAULT NULL,
  `res_usr` int(11) DEFAULT NULL,
  `res_title` varchar(100) DEFAULT NULL,
  `res_date` timestamp NULL DEFAULT current_timestamp(),
  `res_price` int(11) DEFAULT NULL,
  `res_price_dis` tinyint(4) DEFAULT NULL,
  `res_note` varchar(240) DEFAULT NULL,
  `res_view` int(11) DEFAULT 0,
  `OPEN_YN` tinyint(1) NOT NULL DEFAULT 1,
  `res_group` bigint(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`res_key`),
  KEY `pinto_res_pinto_usr_usr_key_fk` (`res_usr`),
  CONSTRAINT `pinto_res_pinto_usr_usr_key_fk` FOREIGN KEY (`res_usr`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_scene` (
  `work_id` bigint(20) NOT NULL,
  `epi_id` bigint(20) NOT NULL,
  `scene_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `lastedit` int(10) NOT NULL,
  `bf_scene` bigint(20) DEFAULT NULL,
  `bf_page` bigint(20) DEFAULT NULL,
  `note` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`work_id`,`epi_id`,`scene_id`),
  CONSTRAINT `scene_epi_fk1` FOREIGN KEY (`work_id`, `epi_id`) REFERENCES `pinto_epi` (`work_id`, `epi_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_tag` (
  `tag_key` int(11) NOT NULL AUTO_INCREMENT,
  `tag_value` varchar(20) DEFAULT NULL,
  `tag_time` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`tag_key`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_usr` (
  `usr_key` int(11) NOT NULL AUTO_INCREMENT,
  `usr_name` varchar(40) DEFAULT ' ',
  `usr_highness` tinyint(4) DEFAULT 0,
  `usr_gem` int(11) DEFAULT 0,
  PRIMARY KEY (`usr_key`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_var` (
  `work_id` bigint(20) NOT NULL,
  `var_id` bigint(20) NOT NULL,
  `var_value` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`work_id`,`var_id`),
  KEY `pinto_work_pinto_var_work_id_fk` (`work_id`),
  CONSTRAINT `pinto_work_pinto_var_work_id_fk` FOREIGN KEY (`work_id`) REFERENCES `pinto_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_view_info` (
  `work_id` bigint(20) NOT NULL,
  `usr_id` int(11) NOT NULL,
  `origin_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `view_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`work_id`,`usr_id`,`origin_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `pinto_work` (
  `id` bigint(20) NOT NULL,
  `title` varchar(100) NOT NULL,
  `lastedit` int(20) NOT NULL,
  `face` varchar(100) NOT NULL,
  `usr_key` int(11) NOT NULL,
  `lang` varchar(20) NOT NULL,
  `NOTE` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `work_usr_fk` (`usr_key`),
  CONSTRAINT `work_usr_fk` FOREIGN KEY (`usr_key`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `work_brabo` (
  `brabo_usr` int(11) NOT NULL,
  `brabo_work` bigint(20) NOT NULL,
  `brabo_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`brabo_usr`,`brabo_work`),
  KEY `fK_usr` (`brabo_usr`),
  KEY `fK_work` (`brabo_work`),
  CONSTRAINT `work_brabo_fk_usr_key` FOREIGN KEY (`brabo_usr`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `work_brabo_fk_work_id` FOREIGN KEY (`brabo_work`) REFERENCES `pinto_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `work_usr` (
  `work_id` bigint(20) NOT NULL,
  `usr_key` int(11) NOT NULL,
  `epi_id` bigint(20) NOT NULL,
  `scene_id` bigint(20) NOT NULL,
  `page_id` bigint(20) NOT NULL,
  `read_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`work_id`,`usr_key`),
  KEY `work_usr_fk2` (`usr_key`),
  CONSTRAINT `work_usr_fk1` FOREIGN KEY (`work_id`) REFERENCES `pinto_work` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `work_usr_fk2` FOREIGN KEY (`usr_key`) REFERENCES `pinto_usr` (`usr_key`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `usr_message` (
  `id` bigint(20) NOT NULL,
  `to_usr_id` int(11) NOT NULL,
  `form_usr_id` int(11) NOT NULL,
  `message_value` varchar(2000) NOT NULL,
  `send_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE EPI_CONFIRM (
    WORK_ID BIGINT(20) NOT NULL,
    EPI_ID BIGINT(20) NOT NULL,
    CONFIRM_YN TINYINT(1) NOT NULL DEFAULT 0,
    CREATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UPDATE_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(WORK_ID,EPI_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

alter table pinto_work add column end_yn tinyint(1) not null default 0;
```
