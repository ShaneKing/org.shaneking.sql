["select id,invalid,last_modify_datetime,last_modify_user_id,version,create_datetime,create_user_id,invalid_datetime,invalid_user_id from t_prepare_s_k_id_adt_ver_full_entity where last_modify_datetime like ? and create_user_id in (?,?,?) and invalid_datetime between ? and ? limit 30 offset 0",["%1949-10-01%","1","a",",","1949-10-01","1996-07"]]
