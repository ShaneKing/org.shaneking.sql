create table `t_prepare_s_k_id_adt_ver_full_entity` (
  `ver` int not null comment 'Version for optimistic locking',
  `id` varchar(51) not null comment 'Uniquely identifies',
  `deleted` varchar(1) comment 'The logic deleted status of record {Y:logic deleted,N:logic exist(default)}',
  `mod_date_time` varchar(20) comment 'The last modification date time of record',
  `mod_user_id` varchar(51) comment 'The last modified person of record',
  `crt_date_time` varchar(20) comment 'The creation time of record',
  `crt_user_id` varchar(51) comment 'The creator of record',
  `del_date_time` varchar(20) comment 'The deleted time of record',
  `del_user_id` varchar(51) comment 'The deleted operator of record',
  primary key (`id`)
);
