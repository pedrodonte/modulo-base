CREATE OR REPLACE VIEW segu_vta_jaas AS 
 SELECT u.identificador AS jaas_user,
    u.clave AS jaas_password,
    r.identificador AS jaas_rol
   FROM sg_tb_user_rol ur
     JOIN sg_tb_user u ON ur.usuario_id = u.usuario_id
     JOIN sg_tb_rol r ON ur.rol_id = r.rol_id
  WHERE now() >= ur.valido_desde AND now() <= ur.valido_hasta;