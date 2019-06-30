create table `t_prepare_s_k_id_adt_ver_full_entity` (
  `version` int not null comment 'Version for optimistic locking',
  `id` varchar(40) not null comment 'Uniquely identifies',
  `invalid` varchar(1) comment 'The invalid status of record {Y:Invalid,N:Valid(Default)}',
  `last_modify_datetime` varchar(20) comment 'The last modification time of record',
  `last_modify_user_id` varchar(40) comment 'The last modified person of record',
  `create_datetime` varchar(20) comment 'The creation time of record',
  `create_user_id` varchar(40) comment 'The creator of record',
  `invalid_datetime` varchar(20) comment 'The invalid time of record',
  `invalid_user_id` varchar(40) comment 'The invalid operator of record',
  primary key (`id`)
);
