create table `sktest1_schema`.`sktest1_table` (
  `ver` int not null comment '',
  `id` varchar(51) not null comment '',
  `deleted` varchar(1) comment 'The deleted status of record {Y:Invalid,N:Valid(Default)}',
  `mod_date_time` varchar(20) comment 'The last modification time of record',
  `mod_user_id` varchar(51) comment 'The last modified person of record',
  `has_length` varchar(10) comment '',
  `no_get_method` varchar(255) comment '',
  `not_null_col` varchar(255) not null comment '',
  `unique_col` varchar(255) comment '',
  `re_name_col` varchar(255) comment '',
  `long_text` longtext comment '',
  primary key (`id`)
);
