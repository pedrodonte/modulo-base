create sequence bs_sq_persona;
CREATE TABLE bs_tb_persona
(
  persona_id numeric(9,0) NOT NULL DEFAULT
  nextval('bs_sq_persona'::regclass),
  identificador character varying(15) NOT NULL,
  nombres character varying(100) NOT NULL,
  apellidos character varying(100) NOT NULL,
  sexo numeric(1) NOT NULL,
  fecha_nacimiento date not null,
  reg_fec_insert timestamp without time zone NOT NULL,
  reg_fec_update timestamp without time zone,
  CONSTRAINT persona_id_pk PRIMARY KEY (persona_id)
);


create sequence cs_sq_consulta;
CREATE TABLE cs_tb_consulta
(
  consulta_id numeric(9,0) NOT NULL DEFAULT
  nextval('cs_sq_consulta'::regclass),
  txt_desarrollo text NOT NULL, 
  fecha_consulta timestamp without time zone NOT NULL,
  reg_fec_insert timestamp without time zone NOT NULL,
  reg_fec_update timestamp without time zone,
  persona_id numeric(9,0) NOT NULL,
  CONSTRAINT consulta_id_pk PRIMARY KEY (consulta_id),
  CONSTRAINT persona_id_fk FOREIGN KEY (persona_id)
      REFERENCES bs_tb_persona (persona_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);