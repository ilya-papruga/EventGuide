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

CREATE SCHEMA classifier_service;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE classifier_service.category (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying
);

CREATE TABLE classifier_service.country (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying(3),
    description character varying
);

ALTER TABLE ONLY classifier_service.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY classifier_service.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (uuid);