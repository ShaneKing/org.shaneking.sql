create table `t_prepare_s_k_id_adt_ver_full_entity` (
  `ver` int not null comment 'Version for optimistic locking',
  `id` varchar(40) not null comment 'Uniquely identifies',
  `invalid` varchar(1) comment 'The invalid status of record {Y:invalid,N:valid(default)}',
  `mod_date_time` varchar(20) comment 'The last modification date time of record',
  `mod_user_id` varchar(40) comment 'The last modified person of record',
  `crt_date_time` varchar(20) comment 'The creation time of record',
  `crt_user_id` varchar(40) comment 'The creator of record',
  `ivd_date_time` varchar(20) comment 'The invalid time of record',
  `ivd_user_id` varchar(40) comment 'The invalid operator of record',
  primary key (`id`)
);
