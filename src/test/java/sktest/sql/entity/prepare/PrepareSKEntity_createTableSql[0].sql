create table `sktest1_schema`.`sktest1_table` (
  `version` int not null comment '',
  `id` varchar(40) not null comment '',
  `invalid` varchar(1) comment 'The invalid status of record {Y:Invalid,N:Valid(Default)}',
  `last_modify_datetime` varchar(20) comment 'The last modification time of record',
  `last_modify_user_id` varchar(40) comment 'The last modified person of record',
  `has_length` varchar(10) comment '',
  `no_get_method` varchar(255) comment '',
  `not_null_col` varchar(255) not null comment '',
  `unique_col` varchar(255) comment '',
  `re_name_col` varchar(255) comment '',
  `long_text` longtext comment '',
  primary key (`id`)
);
