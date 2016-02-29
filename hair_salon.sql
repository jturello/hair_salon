--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.0

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

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
-- Name: clients; Type: TABLE; Schema: public; Owner: jturello
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO jturello;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO jturello;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: jturello
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stylists OWNER TO jturello;

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: jturello
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO jturello;

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: jturello
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY clients (id, name, stylist_id) FROM stdin;
28	Fred Client	1
29	Jane Client	2
30	Shane Client	3
31	Wilma Client	1
32	Bambam Client	1
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('clients_id_seq', 32, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: jturello
--

COPY stylists (id, name) FROM stdin;
1	John Stylist
2	Jane Stylist
3	Becky Stylist
4	Tim Stylist
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: jturello
--

SELECT pg_catalog.setval('stylists_id_seq', 11, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: stylist_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: jturello
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT stylist_id_fkey FOREIGN KEY (stylist_id) REFERENCES stylists(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: jturello
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM jturello;
GRANT ALL ON SCHEMA public TO jturello;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

