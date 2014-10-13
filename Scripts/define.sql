-- Table: bs_tb_persona

-- DROP TABLE bs_tb_persona;

CREATE TABLE bs_tb_persona
(
  persona_id numeric(9,0) NOT NULL DEFAULT nextval('bs_sq_persona'::regclass),
  identificador character varying(15) NOT NULL,
  nombres character varying(100) NOT NULL,
  apellidos character varying(100) NOT NULL,
  sexo numeric(1,0) NOT NULL,
  fecha_nacimiento date NOT NULL,
  reg_fec_insert timestamp without time zone NOT NULL,
  reg_fec_update timestamp without time zone,
  CONSTRAINT persona_id_pk PRIMARY KEY (persona_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE bs_tb_persona
  OWNER TO modulo_base;

-- Index: bs_tb_persona_identificador_idx

-- DROP INDEX bs_tb_persona_identificador_idx;

CREATE UNIQUE INDEX bs_tb_persona_identificador_idx
  ON bs_tb_persona
  USING btree
  (identificador COLLATE pg_catalog."default");

-- Table: cs_tb_consulta

-- DROP TABLE cs_tb_consulta;

CREATE TABLE cs_tb_consulta
(
  consulta_id numeric(9,0) NOT NULL DEFAULT nextval('cs_sq_consulta'::regclass),
  txt_desarrollo text NOT NULL,
  fecha_consulta timestamp without time zone NOT NULL,
  reg_fec_insert timestamp without time zone NOT NULL,
  reg_fec_update timestamp without time zone,
  persona_id numeric(9,0) NOT NULL,
  CONSTRAINT consulta_id_pk PRIMARY KEY (consulta_id),
  CONSTRAINT persona_id_fk FOREIGN KEY (persona_id)
      REFERENCES bs_tb_persona (persona_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cs_tb_consulta
  OWNER TO modulo_base;