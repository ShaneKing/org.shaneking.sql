create table `t_prepare_s_k_id_adt_ver_full_entity` (
  `ver` int not null comment 'Version for optimistic locking',
  `id` varchar(40) not null comment 'Uniquely identifies',
  `freezed` varchar(1) comment 'The freeze status of record {Y:freezed,N:actived}',
  `mod_date_time` varchar(20) comment 'The last modification date time of record',
  `mod_user_id` varchar(40) comment 'The last modified person of record',
  `crt_date_time` varchar(20) comment 'The creation time of record',
  `crt_user_id` varchar(40) comment 'The creator of record',
  `frz_date_time` varchar(20) comment 'The freezed time of record',
  `frz_user_id` varchar(40) comment 'The freezed operator of record',
  primary key (`id`)
);
