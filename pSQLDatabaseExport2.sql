--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE admin;
ALTER ROLE admin WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:lG8SAKdRGbTe6XBl4SgiUQ==$MdyROuZjL9atYponVqFMzPTmyj4mSm7IA3D+BK3SZQo=:UEfSYFvoezFVIMkcNZw4Nwwi3Ymzar7K5FPtrDL64xY=';






--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- PostgreSQL database dump complete
--

--
-- Database "admin" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: admin; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE admin WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE admin OWNER TO admin;

\connect admin

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: chstation; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.chstation (
    id integer NOT NULL,
    station_id integer NOT NULL,
    customer_id integer NOT NULL,
    kwh integer NOT NULL,
    datetime timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.chstation OWNER TO admin;

--
-- Name: chstation_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.chstation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chstation_id_seq OWNER TO admin;

--
-- Name: chstation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.chstation_id_seq OWNED BY public.chstation.id;


--
-- Name: chstation id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.chstation ALTER COLUMN id SET DEFAULT nextval('public.chstation_id_seq'::regclass);


--
-- Data for Name: chstation; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.chstation (id, station_id, customer_id, kwh, datetime) FROM stdin;
\.


--
-- Name: chstation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.chstation_id_seq', 1, false);


--
-- Name: chstation chstation_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.chstation
    ADD CONSTRAINT chstation_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

--
-- Database "chstation" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: chstation; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE chstation WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'en_US.utf8';


ALTER DATABASE chstation OWNER TO admin;

\connect chstation

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: customerdata; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.customerdata (
    id integer NOT NULL,
    station_id integer NOT NULL,
    customer_id integer NOT NULL,
    kwh integer NOT NULL,
    datetime timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);


ALTER TABLE public.customerdata OWNER TO admin;

--
-- Name: chstation_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.chstation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.chstation_id_seq OWNER TO admin;

--
-- Name: chstation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.chstation_id_seq OWNED BY public.customerdata.id;


--
-- Name: customerlist; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.customerlist (
    id integer NOT NULL,
    customer_id character varying(255) NOT NULL,
    firstname character varying(255) NOT NULL,
    lastname character varying(255) NOT NULL
);


ALTER TABLE public.customerlist OWNER TO admin;

--
-- Name: customerlist_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.customerlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customerlist_id_seq OWNER TO admin;

--
-- Name: customerlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.customerlist_id_seq OWNED BY public.customerlist.id;


--
-- Name: stationdata; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.stationdata (
    id integer NOT NULL,
    station_id integer NOT NULL,
    available boolean NOT NULL,
    latitude double precision NOT NULL,
    longitude double precision NOT NULL
);


ALTER TABLE public.stationdata OWNER TO admin;

--
-- Name: stationdata_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.stationdata_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stationdata_id_seq OWNER TO admin;

--
-- Name: stationdata_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.stationdata_id_seq OWNED BY public.stationdata.id;


--
-- Name: customerdata id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.customerdata ALTER COLUMN id SET DEFAULT nextval('public.chstation_id_seq'::regclass);


--
-- Name: customerlist id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.customerlist ALTER COLUMN id SET DEFAULT nextval('public.customerlist_id_seq'::regclass);


--
-- Name: stationdata id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.stationdata ALTER COLUMN id SET DEFAULT nextval('public.stationdata_id_seq'::regclass);


--
-- Data for Name: customerdata; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.customerdata (id, station_id, customer_id, kwh, datetime) FROM stdin;
1	1	5555	3300	2022-06-23 17:37:54.875619
3	2	455	2000	2022-06-23 17:39:59.435393
4	3	8000	47780	2022-06-23 17:40:25.519249
\.


--
-- Data for Name: customerlist; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.customerlist (id, customer_id, firstname, lastname) FROM stdin;
1	5555	Michael	Scott
2	455	Robert	California
3	8000	Dwight	Schrute
\.


--
-- Data for Name: stationdata; Type: TABLE DATA; Schema: public; Owner: admin
--

COPY public.stationdata (id, station_id, available, latitude, longitude) FROM stdin;
1	1	t	48.205113	16.38342
2	2	t	48.238836	16.379323
3	3	t	48.196068	16.337239
\.


--
-- Name: chstation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.chstation_id_seq', 4, true);


--
-- Name: customerlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.customerlist_id_seq', 3, true);


--
-- Name: stationdata_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.stationdata_id_seq', 3, true);


--
-- Name: customerdata chstation_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.customerdata
    ADD CONSTRAINT chstation_pkey PRIMARY KEY (id);


--
-- Name: customerlist customerlist_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.customerlist
    ADD CONSTRAINT customerlist_pkey PRIMARY KEY (id);


--
-- Name: stationdata stationdata_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.stationdata
    ADD CONSTRAINT stationdata_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

