--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-07-29 00:48:21

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
-- TOC entry 3316 (class 1262 OID 16795)
-- Name: classifier_service; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE classifier_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


\connect classifier_service

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
-- TOC entry 6 (class 2615 OID 16796)
-- Name: classifier_service; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA classifier_service;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 16804)
-- Name: category; Type: TABLE; Schema: classifier_service; Owner: -
--

CREATE TABLE classifier_service.category (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying
);


--
-- TOC entry 210 (class 1259 OID 16797)
-- Name: country; Type: TABLE; Schema: classifier_service; Owner: -
--

CREATE TABLE classifier_service.country (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(3),
    description character varying
);


--
-- TOC entry 3171 (class 2606 OID 16810)
-- Name: category category_pkey; Type: CONSTRAINT; Schema: classifier_service; Owner: -
--

ALTER TABLE ONLY classifier_service.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (uuid);


--
-- TOC entry 3169 (class 2606 OID 16803)
-- Name: country country_pkey; Type: CONSTRAINT; Schema: classifier_service; Owner: -
--

ALTER TABLE ONLY classifier_service.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (uuid);


-- Completed on 2022-07-29 00:48:22

--
-- PostgreSQL database dump complete
--

