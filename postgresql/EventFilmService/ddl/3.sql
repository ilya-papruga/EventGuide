--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-07-29 01:06:05

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
-- TOC entry 3310 (class 1262 OID 16786)
-- Name: film_service; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE film_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


\connect film_service

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
-- TOC entry 5 (class 2615 OID 16787)
-- Name: film_service; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA film_service;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 210 (class 1259 OID 16788)
-- Name: film; Type: TABLE; Schema: film_service; Owner: -
--

CREATE TABLE film_service.film (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying,
    description text,
    dt_event timestamp(3) without time zone,
    dt_end_of_sale timestamp(3) without time zone,
    type character varying,
    status character varying,
    country uuid,
    release_year smallint,
    release_date timestamp(3) without time zone,
    duration smallint,
    author character varying
);


--
-- TOC entry 3165 (class 2606 OID 16794)
-- Name: film film_pkey; Type: CONSTRAINT; Schema: film_service; Owner: -
--

ALTER TABLE ONLY film_service.film
    ADD CONSTRAINT film_pkey PRIMARY KEY (uuid);


-- Completed on 2022-07-29 01:06:06

--
-- PostgreSQL database dump complete
--

