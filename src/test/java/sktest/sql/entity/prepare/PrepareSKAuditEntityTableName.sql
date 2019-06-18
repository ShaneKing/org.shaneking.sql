create table `t_prepare_s_k_audit_entity_table_name` (
  `version` int not null comment '',
  `id` varchar(40) not null comment '',
  `create_datetime` varchar(20) comment '创建时间',
  `create_user_id` varchar(40) comment '创建人',
  `invalid` varchar(1) comment '是否失效{Y:已失效,N:未失效(默认值)}',
  `invalid_datetime` varchar(20) comment '失效时间',
  `invalid_user_id` varchar(40) comment '失效人',
  `last_modify_datetime` varchar(20) comment '最后修改时间',
  `last_modify_user_id` varchar(40) comment '最后修改人',
  primary key (`id`)
);
