create table `testschema`.`t_prepare_s_k_audit_entity_columns` (
  `version` int not null,
  `id` varchar(40) not null,
  `create_datetime` varchar(20),
  `create_user_id` varchar(40),
  `invalid` varchar(1),
  `invalid_datetime` varchar(20),
  `invalid_user_id` varchar(40),
  `last_modify_datetime` varchar(20),
  `last_modify_user_id` varchar(40),
  `has_length` varchar(10),
  `re_name` varchar(4000),
  primary key (`id`)
);
