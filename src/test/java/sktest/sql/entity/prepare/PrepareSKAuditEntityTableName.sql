create table `t_prepare_s_k_audit_entity_table_name` (
  `version` int not null,
  `id` varchar(40) not null,
  `create_datetime` varchar(20),
  `create_user_id` varchar(40),
  `invalid` varchar(1),
  `invalid_datetime` varchar(20),
  `invalid_user_id` varchar(40),
  `last_modify_datetime` varchar(20),
  `last_modify_user_id` varchar(40),
  primary key (`id`)
);
