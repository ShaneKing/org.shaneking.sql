create table `sktest1_schema`.`sktest1_table` (
  `ver` int not null comment 'Version for optimistic locking',
  `id` varchar(40) not null comment 'Uniquely identifies',
  `invalid` varchar(1) comment 'The invalid status of record {Y:invalid,N:valid(default)}',
  `mod_date_time` varchar(20) comment 'The last modification date time of record',
  `mod_user_id` varchar(40) comment 'The last modified person of record',
  `has_length` varchar(10) comment '',
  `no_get_method` varchar(255) comment '',
  `not_null_col` varchar(255) not null comment '',
  `unique_col` varchar(255) comment '',
  `re_name_col` varchar(255) comment '',
  `long_text` longtext comment '',
  primary key (`id`)
);
