--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2
-- Dumped by pg_dump version 14.2

-- Started on 2022-07-29 00:59:59

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
-- TOC entry 3325 (class 1262 OID 16820)
-- Name: user_service; Type: DATABASE; Schema: -; Owner: -
--

CREATE DATABASE user_service WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Russian_Russia.1251';


\connect user_service

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
-- TOC entry 6 (class 2615 OID 16821)
-- Name: user_service; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA user_service;


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 211 (class 1259 OID 17045)
-- Name: roles; Type: TABLE; Schema: user_service; Owner: -
--

CREATE TABLE user_service.roles (
    name character varying NOT NULL
);


--
-- TOC entry 210 (class 1259 OID 16829)
-- Name: users; Type: TABLE; Schema: user_service; Owner: -
--

CREATE TABLE user_service.users (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    mail character varying,
    nick character varying,
    status character varying,
    password character varying
);


--
-- TOC entry 212 (class 1259 OID 17057)
-- Name: users_roles; Type: TABLE; Schema: user_service; Owner: -
--

CREATE TABLE user_service.users_roles (
    user_mail character varying,
    role_name character varying
);


--
-- TOC entry 3173 (class 2606 OID 17070)
-- Name: users mail; Type: CONSTRAINT; Schema: user_service; Owner: -
--

ALTER TABLE ONLY user_service.users
    ADD CONSTRAINT mail UNIQUE (mail);


--
-- TOC entry 3175 (class 2606 OID 17072)
-- Name: users nick; Type: CONSTRAINT; Schema: user_service; Owner: -
--

ALTER TABLE ONLY user_service.users
    ADD CONSTRAINT nick UNIQUE (nick);


--
-- TOC entry 3179 (class 2606 OID 17056)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: user_service; Owner: -
--

ALTER TABLE ONLY user_service.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (name);


--
-- TOC entry 3177 (class 2606 OID 16835)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: user_service; Owner: -
--

ALTER TABLE ONLY user_service.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uuid);


--
-- TOC entry 3180 (class 2606 OID 17062)
-- Name: users_roles role; Type: FK CONSTRAINT; Schema: user_service; Owner: -
--

ALTER TABLE ONLY user_service.users_roles
    ADD CONSTRAINT role FOREIGN KEY (role_name) REFERENCES user_service.roles(name);


-- Completed on 2022-07-29 01:00:00

--
-- PostgreSQL database dump complete
--

