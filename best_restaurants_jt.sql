--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner:
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner:
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: cuisine; Type: TABLE; Schema: public; Owner: jturello; Tablespace:
--

CREATE TABLE cuisine (
    cuisine_id integer NOT NULL,
    type character varying
);


ALTER TABLE cuisine OWNER TO "jturello";

--
-- Name: cuisine_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE cuisine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cuisine_id_seq OWNER TO "jturello";

--
-- Name: cuisine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE cuisine_id_seq OWNED BY cuisine.cuisine_id;


--
-- Name: restaurants; Type: TABLE; Schema: public; Owner: jturello; Tablespace:
--

CREATE TABLE restaurants (
    id integer NOT NULL,
    name character varying,
    cuisine_id integer,
    description character varying
);


ALTER TABLE restaurants OWNER TO "jturello";

--
-- Name: restaurants_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE restaurants_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE restaurants_id_seq OWNER TO "jturello";

--
-- Name: restaurants_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE restaurants_id_seq OWNED BY restaurants.id;


--
-- Name: cuisine_id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY cuisine ALTER COLUMN cuisine_id SET DEFAULT nextval('cuisine_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY restaurants ALTER COLUMN id SET DEFAULT nextval('restaurants_id_seq'::regclass);


--
-- Data for Name: cuisine; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY cuisine (cuisine_id, type) FROM stdin;
1	American
2	Southern
3	Vietnamese
4	Thai
5	Chinese
6	Italian
\.


--
-- Name: cuisine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('cuisine_id_seq', 6, true);


--
-- Data for Name: restaurants; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY restaurants (id, name, cuisine_id, description) FROM stdin;
4	Lu Lac	3	\N
3	Thai Bloom	4	\N
5	Hot Pot City	5	\N
6	Seratto	6	\N
1	Screen Door	1	\N
2	Lardo	3	\N
\.


--
-- Name: restaurants_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('restaurants_id_seq', 6, true);


--
-- Name: cuisine_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello; Tablespace:
--

ALTER TABLE ONLY cuisine
    ADD CONSTRAINT cuisine_pkey PRIMARY KEY (cuisine_id);


--
-- Name: restaurants_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello; Tablespace:
--

ALTER TABLE ONLY restaurants
    ADD CONSTRAINT restaurants_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: epicodus
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM jturello;
GRANT ALL ON SCHEMA public TO jturello;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
