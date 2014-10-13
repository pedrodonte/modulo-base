--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.5
-- Dumped by pg_dump version 9.3.5
-- Started on 2014-10-13 18:25:21 CLST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 183 (class 3079 OID 12616)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2876 (class 0 OID 0)
-- Dependencies: 183
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

--
-- TOC entry 176 (class 1259 OID 16421)
-- Name: bs_sq_persona; Type: SEQUENCE; Schema: public; Owner: modulo_base
--

CREATE SEQUENCE bs_sq_persona
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bs_sq_persona OWNER TO modulo_base;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 178 (class 1259 OID 16508)
-- Name: bs_tb_persona; Type: TABLE; Schema: public; Owner: modulo_base; Tablespace: 
--

CREATE TABLE bs_tb_persona (
    persona_id numeric(9,0) DEFAULT nextval('bs_sq_persona'::regclass) NOT NULL,
    identificador character varying(15) NOT NULL,
    nombres character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    sexo numeric(1,0) NOT NULL,
    fecha_nacimiento date NOT NULL,
    reg_fec_insert timestamp without time zone NOT NULL,
    reg_fec_update timestamp without time zone
);


ALTER TABLE public.bs_tb_persona OWNER TO modulo_base;

--
-- TOC entry 181 (class 1259 OID 16549)
-- Name: bs_tb_sexo; Type: TABLE; Schema: public; Owner: modulo_base; Tablespace: 
--

CREATE TABLE bs_tb_sexo (
    sexo_id numeric(1,0) NOT NULL,
    descripcion character varying(50)
);


ALTER TABLE public.bs_tb_sexo OWNER TO modulo_base;

--
-- TOC entry 177 (class 1259 OID 16429)
-- Name: cs_sq_consulta; Type: SEQUENCE; Schema: public; Owner: modulo_base
--

CREATE SEQUENCE cs_sq_consulta
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cs_sq_consulta OWNER TO modulo_base;

--
-- TOC entry 179 (class 1259 OID 16514)
-- Name: cs_tb_consulta; Type: TABLE; Schema: public; Owner: modulo_base; Tablespace: 
--

CREATE TABLE cs_tb_consulta (
    consulta_id numeric(9,0) DEFAULT nextval('cs_sq_consulta'::regclass) NOT NULL,
    txt_desarrollo text NOT NULL,
    fecha_consulta timestamp without time zone NOT NULL,
    persona_paciente_id numeric(9,0) NOT NULL,
    reg_fec_insert timestamp without time zone NOT NULL,
    reg_fec_update timestamp without time zone
);


ALTER TABLE public.cs_tb_consulta OWNER TO modulo_base;

--
-- TOC entry 170 (class 1259 OID 16386)
-- Name: sg_sq_rol; Type: SEQUENCE; Schema: public; Owner: modulo_base
--

CREATE SEQUENCE sg_sq_rol
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sg_sq_rol OWNER TO modulo_base;

--
-- TOC entry 172 (class 1259 OID 16390)
-- Name: sg_sq_user; Type: SEQUENCE; Schema: public; Owner: modulo_base
--

CREATE SEQUENCE sg_sq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sg_sq_user OWNER TO modulo_base;

--
-- TOC entry 171 (class 1259 OID 16388)
-- Name: sg_sq_user_rol; Type: SEQUENCE; Schema: public; Owner: modulo_base
--

CREATE SEQUENCE sg_sq_user_rol
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sg_sq_user_rol OWNER TO modulo_base;

--
-- TOC entry 175 (class 1259 OID 16399)
-- Name: sg_tb_rol; Type: TABLE; Schema: public; Owner: modulo_base; Tablespace: 
--

CREATE TABLE sg_tb_rol (
    rol_id numeric(9,0) DEFAULT nextval('sg_sq_rol'::regclass) NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion character varying(140) NOT NULL,
    identificador character varying(50) NOT NULL,
    reg_fec_update timestamp without time zone,
    reg_fec_insert timestamp without time zone NOT NULL
);


ALTER TABLE public.sg_tb_rol OWNER TO modulo_base;

--
-- TOC entry 174 (class 1259 OID 16395)
-- Name: sg_tb_user; Type: TABLE; Schema: public; Owner: modulo_base; Tablespace: 
--

CREATE TABLE sg_tb_user (
    usuario_id numeric(9,0) DEFAULT nextval('sg_sq_user'::regclass) NOT NULL,
    identificador character varying(50) NOT NULL,
    clave character varying(64) NOT NULL,
    reg_fec_insert timestamp without time zone NOT NULL,
    reg_fec_update timestamp without time zone,
    email character varying(140),
    flag_cambiar_clave numeric(1,0) DEFAULT 0
);


ALTER TABLE public.sg_tb_user OWNER TO modulo_base;

--
-- TOC entry 2877 (class 0 OID 0)
-- Dependencies: 174
-- Name: TABLE sg_tb_user; Type: COMMENT; Schema: public; Owner: modulo_base
--

COMMENT ON TABLE sg_tb_user IS 'registro de los usuarios del sistema.';


--
-- TOC entry 2878 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN sg_tb_user.email; Type: COMMENT; Schema: public; Owner: modulo_base
--

COMMENT ON COLUMN sg_tb_user.email IS 'correo electronico del usuario.';


--
-- TOC entry 2879 (class 0 OID 0)
-- Dependencies: 174
-- Name: COLUMN sg_tb_user.flag_cambiar_clave; Type: COMMENT; Schema: public; Owner: modulo_base
--

COMMENT ON COLUMN sg_tb_user.flag_cambiar_clave IS '0 es no hacer nada, 1 cambiar clave en proximo login';


--
-- TOC entry 173 (class 1259 OID 16392)
-- Name: sg_tb_user_rol; Type: TABLE; Schema: public; Owner: modulo_base; Tablespace: 
--

CREATE TABLE sg_tb_user_rol (
    usuario_rol_id numeric(9,0) DEFAULT nextval('sg_sq_user_rol'::regclass) NOT NULL,
    usuario_id numeric(9,0) NOT NULL,
    rol_id numeric(9,0) NOT NULL,
    valido_desde timestamp without time zone NOT NULL,
    valido_hasta timestamp without time zone,
    reg_fec_insert timestamp without time zone NOT NULL,
    reg_fec_update timestamp without time zone
);


ALTER TABLE public.sg_tb_user_rol OWNER TO modulo_base;

--
-- TOC entry 180 (class 1259 OID 16545)
-- Name: segu_vta_jaas; Type: VIEW; Schema: public; Owner: modulo_base
--

CREATE VIEW segu_vta_jaas AS
 SELECT u.identificador AS jaas_user,
    u.clave AS jaas_password,
    r.identificador AS jaas_rol
   FROM ((sg_tb_user_rol ur
     JOIN sg_tb_user u ON ((ur.usuario_id = u.usuario_id)))
     JOIN sg_tb_rol r ON ((ur.rol_id = r.rol_id)))
  WHERE ((now() >= ur.valido_desde) AND (now() <= ur.valido_hasta));


ALTER TABLE public.segu_vta_jaas OWNER TO modulo_base;

--
-- TOC entry 182 (class 1259 OID 16579)
-- Name: vw_rc_resumen_consultas; Type: VIEW; Schema: public; Owner: modulo_base
--

CREATE VIEW vw_rc_resumen_consultas AS
 SELECT cs_tb_consulta.consulta_id AS vista_id,
    date_trunc('day'::text, cs_tb_consulta.fecha_consulta) AS fecha_consulta,
    cs_tb_consulta.fecha_consulta AS fecha_hora,
    (date_part('minute'::text, (cs_tb_consulta.reg_fec_insert - cs_tb_consulta.fecha_consulta)))::numeric AS duracion,
    (((date_part('minute'::text, (cs_tb_consulta.reg_fec_insert - cs_tb_consulta.fecha_consulta)) || ' minutos, '::text) || date_part('second'::text, (cs_tb_consulta.reg_fec_insert - cs_tb_consulta.fecha_consulta))) || ' segundos'::text) AS duracion_exacta,
    bs_tb_persona.identificador AS paciente,
    bs_tb_sexo.descripcion AS sexo
   FROM bs_tb_persona,
    cs_tb_consulta,
    bs_tb_sexo
  WHERE ((cs_tb_consulta.persona_paciente_id = bs_tb_persona.persona_id) AND (bs_tb_sexo.sexo_id = bs_tb_persona.sexo))
  ORDER BY cs_tb_consulta.consulta_id DESC;


ALTER TABLE public.vw_rc_resumen_consultas OWNER TO modulo_base;

--
-- TOC entry 2880 (class 0 OID 0)
-- Dependencies: 176
-- Name: bs_sq_persona; Type: SEQUENCE SET; Schema: public; Owner: modulo_base
--

SELECT pg_catalog.setval('bs_sq_persona', 20, true);


--
-- TOC entry 2866 (class 0 OID 16508)
-- Dependencies: 178
-- Data for Name: bs_tb_persona; Type: TABLE DATA; Schema: public; Owner: modulo_base
--

INSERT INTO bs_tb_persona VALUES (14, '16350301-8', 'NATALY RAQUEL', 'LEMUS ?VILA', 1, '1986-06-11', '2014-10-06 16:22:04.859', NULL);
INSERT INTO bs_tb_persona VALUES (15, '16184826-3', 'PEDRO ARIEL', 'CARRASCO CUR?N', 2, '1985-09-02', '2014-10-06 17:20:12.744', NULL);
INSERT INTO bs_tb_persona VALUES (16, '23543', 'sdfsdafsd', 'fdsafsda', 2, '2014-10-09', '2014-10-09 11:59:13.885', '2014-10-09 11:59:31.746');
INSERT INTO bs_tb_persona VALUES (17, '17391922-0', 'MIGUEL ALEJANDRO', 'ENRÍQUEZ TRICALLOTA', 2, '1986-11-19', '2014-10-13 10:53:40.11', NULL);
INSERT INTO bs_tb_persona VALUES (18, '8388146-1', 'PEDRO CALIXTO', 'CARRASCO QUIROZ', 2, '1957-10-14', '2014-10-13 12:01:33.805', NULL);
INSERT INTO bs_tb_persona VALUES (19, '8768000-2', 'ROSA REBECA', 'CUR?N MILL?N', 1, '1961-04-10', '2014-10-13 12:01:47.208', NULL);
INSERT INTO bs_tb_persona VALUES (20, '24219144-7', 'VICENTE RODRIGO ', 'CARRASCO LEMUS', 2, '2013-03-17', '2014-10-13 12:02:14.566', NULL);


--
-- TOC entry 2868 (class 0 OID 16549)
-- Dependencies: 181
-- Data for Name: bs_tb_sexo; Type: TABLE DATA; Schema: public; Owner: modulo_base
--

INSERT INTO bs_tb_sexo VALUES (1, 'Femenino');
INSERT INTO bs_tb_sexo VALUES (2, 'Masculino');
INSERT INTO bs_tb_sexo VALUES (3, 'Otro');


--
-- TOC entry 2881 (class 0 OID 0)
-- Dependencies: 177
-- Name: cs_sq_consulta; Type: SEQUENCE SET; Schema: public; Owner: modulo_base
--

SELECT pg_catalog.setval('cs_sq_consulta', 77, true);


--
-- TOC entry 2867 (class 0 OID 16514)
-- Dependencies: 179
-- Data for Name: cs_tb_consulta; Type: TABLE DATA; Schema: public; Owner: modulo_base
--

INSERT INTO cs_tb_consulta VALUES (43, 'Hi<br>I have a div element full of checkboxes that gets hidden on page load via<br>$(document).ready(function() {$(''#testDiv'').hide(); }.<br>This works fine, but what I really want to do is to only hide the testDiv if<br>none of the checkboxes within it have a ''checked="checked"'' attribute. All<br>I''ve managed to do so far is to hide/not hide the checkboxes that are<br>checked - not quite the result I was hoping for :)<br>Is this the sort of selection that JQuery can handle easily, or do I have to<br>write a function?<br>Many thanks<br>~GreyCells (now subscribed)<br>-- <br>View this message in context: <a href="http://www.nabble.com/Hide-div-only-if-no-child-checkbox-is-selected-tf2817380.html#a7863621" target="_blank">http://www.nabble.com/Hide-div-only-if-no-child-checkbox-is-selected-tf2817380.html#a7863621</a><br>Sent from the JQuery mailing list archive at Nabble.com.<br>_______________________________________________<br>jQuery mailing list<br><a href="mailto:discuss@jquery.com" target="_blank">discuss@jquery.com</a><br><a href="http://jquery.com/discuss/" target="_blank">http://jquery.com/discuss/</a>', '2014-10-06 16:23:08.393', 14, '2014-10-06 16:23:13.176', '2014-10-06 16:25:10.299');
INSERT INTO cs_tb_consulta VALUES (44, 'hfgfdhdfgh', '2014-10-06 16:54:02.405', 14, '2014-10-06 16:54:04.501', NULL);
INSERT INTO cs_tb_consulta VALUES (45, 'sdfsadf', '2014-10-06 16:55:31.13', 14, '2014-10-06 16:55:32.639', NULL);
INSERT INTO cs_tb_consulta VALUES (46, 'dsfdsf', '2014-10-06 17:02:46.567', 14, '2014-10-06 17:02:48.225', NULL);
INSERT INTO cs_tb_consulta VALUES (47, 'sdafsdaf', '2014-10-06 17:20:13.842', 15, '2014-10-06 17:20:15.373', NULL);
INSERT INTO cs_tb_consulta VALUES (48, 'Integer elementum massa at nulla placerat varius. Suspe           ndisse in libero risus.', '2014-10-06 17:20:15', 14, '2014-10-06 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (49, 'Integer elementum massa at nul      nec est eget dolor laoreet iaculis a sit amet diam.', '2014-10-07 17:20:15', 14, '2014-10-07 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (50, 'Maecenas eu placerat ante. Fusce ut d d fdf d  df df df df df df sd sfdd facilisis erat.', '2014-10-08 17:20:15', 15, '2014-10-08 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (51, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-09 17:20:15', 15, '2014-10-09 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (52, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-10 17:20:15', 17, '2014-10-10 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (53, 'Maecenas eu placerat ante. Fusce ut neque justo, et aliquet enim. In hac habitasse plis erat.', '2014-10-13 17:20:15', 14, '2014-10-13 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (54, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing', '2014-10-14 17:20:15', 15, '2014-10-14 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (55, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-15 17:20:15', 14, '2014-10-15 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (56, 'Maecenas eu placerat ante. Fusce ut neque justo, et aliquet enime erat, vitae facilisis erat. ', '2014-10-16 17:20:15', 17, '2014-10-16 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (57, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-17 17:20:15', 15, '2014-10-17 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (58, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-18 17:20:15', 17, '2014-10-18 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (59, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-19 17:20:15', 14, '2014-10-19 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (60, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-20 17:20:15', 14, '2014-10-20 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (61, 'Maecenas eu placerat ante. Fusce ut nequmst. Nullam commodo neque erat, vitae facilisis erat. ', '2014-10-21 17:20:15', 15, '2014-10-21 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (62, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-22 17:20:15', 17, '2014-10-22 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (63, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-06 17:20:15', 18, '2014-10-06 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (64, 'Maecenas eu placerat ante. Fusce ut nequmst. Nullam commodo neque erat, vitae facilisis erat.', '2014-10-07 17:20:15', 19, '2014-10-07 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (65, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-08 17:20:15', 20, '2014-10-08 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (66, 'Maecenas eu placerat ante. Fusce ut neque justo, et aliquet enim. In hace ersis fdfderat. ', '2014-10-09 17:20:15', 18, '2014-10-09 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (67, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-10 17:20:15', 19, '2014-10-10 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (68, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-13 17:20:15', 20, '2014-10-13 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (69, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-14 17:20:15', 18, '2014-10-14 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (70, 'Maecenas eu placerat ante. Fusce ut neque justo, et ali. Nullam commoacilisis efdfdfrat.', '2014-10-15 17:20:15', 19, '2014-10-15 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (71, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-16 17:20:15', 20, '2014-10-16 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (72, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-17 17:20:15', 17, '2014-10-17 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (73, 'Maecenas eu placerat ante. Fusce ut neque justo, et aliquet enim. In hac habita Nms erat. ', '2014-10-18 17:20:15', 14, '2014-10-18 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (74, 'Proin suscipit luctus orci placerat fringilla. Donec hendrerit laoreet risus eget adipiscing.', '2014-10-19 17:20:15', 14, '2014-10-19 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (75, 'Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.', '2014-10-20 17:20:15', 14, '2014-10-20 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (76, 'Maecenas eu placerat ante. Fusce ut neque justo, et aliquet enim. In at, vitae facilisis erat.', '2014-10-21 17:20:15', 15, '2014-10-21 17:25:00', NULL);
INSERT INTO cs_tb_consulta VALUES (77, 'Maecenas eu placerat ante. Fusce ut neque justo, et aliquet enim.ue erfsdf s sdfsdferat.', '2014-10-22 17:20:15', 17, '2014-10-22 17:25:00', NULL);


--
-- TOC entry 2882 (class 0 OID 0)
-- Dependencies: 170
-- Name: sg_sq_rol; Type: SEQUENCE SET; Schema: public; Owner: modulo_base
--

SELECT pg_catalog.setval('sg_sq_rol', 4, true);


--
-- TOC entry 2883 (class 0 OID 0)
-- Dependencies: 172
-- Name: sg_sq_user; Type: SEQUENCE SET; Schema: public; Owner: modulo_base
--

SELECT pg_catalog.setval('sg_sq_user', 10, true);


--
-- TOC entry 2884 (class 0 OID 0)
-- Dependencies: 171
-- Name: sg_sq_user_rol; Type: SEQUENCE SET; Schema: public; Owner: modulo_base
--

SELECT pg_catalog.setval('sg_sq_user_rol', 13, true);


--
-- TOC entry 2863 (class 0 OID 16399)
-- Dependencies: 175
-- Data for Name: sg_tb_rol; Type: TABLE DATA; Schema: public; Owner: modulo_base
--

INSERT INTO sg_tb_rol VALUES (1, 'invitado', 'invitado', 'INVITADO', NULL, '2014-09-14 00:17:01.48');
INSERT INTO sg_tb_rol VALUES (2, 'administrador', 'administrador', 'ADMINISTRADOR', NULL, '2014-09-14 00:17:25.843');
INSERT INTO sg_tb_rol VALUES (3, 'consulta', 'consulta', 'CONSULTA', NULL, '2014-09-14 00:17:44.604');
INSERT INTO sg_tb_rol VALUES (4, 'PRESTADOR', 'PRESTADOR', 'PRESTADOR', NULL, '2014-10-13 09:22:32.191579');


--
-- TOC entry 2862 (class 0 OID 16395)
-- Dependencies: 174
-- Data for Name: sg_tb_user; Type: TABLE DATA; Schema: public; Owner: modulo_base
--

INSERT INTO sg_tb_user VALUES (4, 'natyprint', 'CCBC5C', '2014-09-14 14:02:40.797', NULL, 'natyprint@gmail.com', 0);
INSERT INTO sg_tb_user VALUES (3, 'pfns', '5DB8FC', '2014-09-14 13:52:42.302', '2014-09-14 14:03:14.493', 'pacarrasco@fonasa.cl', 1);
INSERT INTO sg_tb_user VALUES (5, 'richard', '008F9A', '2014-09-15 12:34:09.26', NULL, 'richard@fonasa', 0);
INSERT INTO sg_tb_user VALUES (6, 'uno', 'D97F0D', '2014-09-15 12:35:08.721', NULL, 'uno@fonasa', 0);
INSERT INTO sg_tb_user VALUES (7, 'uno', '6E570C', '2014-09-15 12:35:58.28', NULL, 'uno@fonasa', 0);
INSERT INTO sg_tb_user VALUES (8, '123', 'C79A57', '2014-09-15 12:52:32.18', NULL, '123', 1);
INSERT INTO sg_tb_user VALUES (9, 'uno', 'EE55D9', '2014-09-15 12:53:50.709', NULL, 'uno@fonasa', 0);
INSERT INTO sg_tb_user VALUES (10, 'miguel', 'B8CFB9', '2014-10-01 12:50:52.808', '2014-10-01 12:52:12.921', 'menriquez@fonasa.cl', 1);
INSERT INTO sg_tb_user VALUES (2, 'vicente', '46A05B619197BC9C16EC59662C81AD7181B26EFAF5E2BF0BD14B3D88B759E818', '2014-09-14 01:05:55.424', '2014-09-14 14:03:38.397', 'pedrodonte@gmail.com', 0);
INSERT INTO sg_tb_user VALUES (1, 'pedrodonte', '46A05B619197BC9C16EC59662C81AD7181B26EFAF5E2BF0BD14B3D88B759E818', '2014-09-14 00:50:41.846', '2014-10-13 10:56:42.965', 'pedrodonte@outlook.com', 0);


--
-- TOC entry 2861 (class 0 OID 16392)
-- Dependencies: 173
-- Data for Name: sg_tb_user_rol; Type: TABLE DATA; Schema: public; Owner: modulo_base
--

INSERT INTO sg_tb_user_rol VALUES (2, 2, 1, '2014-09-14 01:05:55.457', '2014-09-14 12:19:03.912', '2014-09-14 01:05:55.461', '2014-09-14 12:19:03.918');
INSERT INTO sg_tb_user_rol VALUES (3, 2, 3, '2014-09-14 01:05:55.543', '2014-09-14 12:19:04.073', '2014-09-14 01:05:55.546', '2014-09-14 12:19:04.077');
INSERT INTO sg_tb_user_rol VALUES (4, 2, 3, '2014-09-14 12:33:50.869', '9999-01-31 12:33:50.869', '2014-09-14 12:33:50.874', NULL);
INSERT INTO sg_tb_user_rol VALUES (5, 2, 1, '2014-09-14 12:33:50.928', '9999-01-31 12:33:50.928', '2014-09-14 12:33:50.939', NULL);
INSERT INTO sg_tb_user_rol VALUES (6, 3, 2, '2014-09-14 13:52:42.394', '9999-01-31 13:52:42.394', '2014-09-14 13:52:42.412', NULL);
INSERT INTO sg_tb_user_rol VALUES (7, 3, 3, '2014-09-14 13:52:42.472', '9999-01-31 13:52:42.472', '2014-09-14 13:52:42.484', NULL);
INSERT INTO sg_tb_user_rol VALUES (8, 4, 1, '2014-09-14 14:02:40.893', '9999-01-31 14:02:40.893', '2014-09-14 14:02:40.898', NULL);
INSERT INTO sg_tb_user_rol VALUES (9, 5, 2, '2014-09-15 12:34:09.451', '9999-01-31 12:34:09.451', '2014-09-15 12:34:09.462', NULL);
INSERT INTO sg_tb_user_rol VALUES (10, 6, 1, '2014-09-15 12:35:08.749', '9999-01-31 12:35:08.749', '2014-09-15 12:35:08.756', NULL);
INSERT INTO sg_tb_user_rol VALUES (11, 8, 2, '2014-09-15 12:52:32.221', '9999-01-31 12:52:32.221', '2014-09-15 12:52:32.223', NULL);
INSERT INTO sg_tb_user_rol VALUES (12, 10, 3, '2014-10-01 12:50:52.852', '9999-01-31 12:50:52.852', '2014-10-01 12:50:52.854', NULL);
INSERT INTO sg_tb_user_rol VALUES (13, 1, 4, '2014-10-13 10:26:52.963', '9999-01-31 12:50:52.852', '2014-10-13 10:26:52.964', '2014-10-13 10:27:03.52');
INSERT INTO sg_tb_user_rol VALUES (1, 1, 2, '2014-09-14 01:05:06.965', '9999-01-31 12:50:52.852', '2014-09-14 01:05:06.968', '2014-10-13 10:27:03.493');


--
-- TOC entry 2745 (class 2606 OID 16553)
-- Name: bs_tb_sexo_pkey; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY bs_tb_sexo
    ADD CONSTRAINT bs_tb_sexo_pkey PRIMARY KEY (sexo_id);


--
-- TOC entry 2743 (class 2606 OID 16522)
-- Name: consulta_id_pk; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY cs_tb_consulta
    ADD CONSTRAINT consulta_id_pk PRIMARY KEY (consulta_id);


--
-- TOC entry 2739 (class 2606 OID 16513)
-- Name: persona_id_pk; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY bs_tb_persona
    ADD CONSTRAINT persona_id_pk PRIMARY KEY (persona_id);


--
-- TOC entry 2741 (class 2606 OID 16544)
-- Name: persona_unique_identificador; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY bs_tb_persona
    ADD CONSTRAINT persona_unique_identificador UNIQUE (identificador);


--
-- TOC entry 2737 (class 2606 OID 16404)
-- Name: sg_tb_rol_pk; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY sg_tb_rol
    ADD CONSTRAINT sg_tb_rol_pk PRIMARY KEY (rol_id);


--
-- TOC entry 2735 (class 2606 OID 16406)
-- Name: sg_tb_user_pk; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY sg_tb_user
    ADD CONSTRAINT sg_tb_user_pk PRIMARY KEY (usuario_id);


--
-- TOC entry 2733 (class 2606 OID 16408)
-- Name: sg_tb_user_rol_pk; Type: CONSTRAINT; Schema: public; Owner: modulo_base; Tablespace: 
--

ALTER TABLE ONLY sg_tb_user_rol
    ADD CONSTRAINT sg_tb_user_rol_pk PRIMARY KEY (usuario_rol_id);


--
-- TOC entry 2748 (class 2606 OID 16523)
-- Name: persona_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: modulo_base
--

ALTER TABLE ONLY cs_tb_consulta
    ADD CONSTRAINT persona_id_fk FOREIGN KEY (persona_paciente_id) REFERENCES bs_tb_persona(persona_id);


--
-- TOC entry 2746 (class 2606 OID 16409)
-- Name: rol_usuario_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: modulo_base
--

ALTER TABLE ONLY sg_tb_user_rol
    ADD CONSTRAINT rol_usuario_rol_fk FOREIGN KEY (rol_id) REFERENCES sg_tb_rol(rol_id);


--
-- TOC entry 2747 (class 2606 OID 16414)
-- Name: usuario_usuario_rol_fk; Type: FK CONSTRAINT; Schema: public; Owner: modulo_base
--

ALTER TABLE ONLY sg_tb_user_rol
    ADD CONSTRAINT usuario_usuario_rol_fk FOREIGN KEY (usuario_id) REFERENCES sg_tb_user(usuario_id);


--
-- TOC entry 2875 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-10-13 18:25:21 CLST

--
-- PostgreSQL database dump complete
--

