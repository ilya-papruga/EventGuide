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

CREATE SCHEMA concert_service;

ALTER SCHEMA concert_service OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE concert_service.concert (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone,
    dt_update timestamp(3) without time zone,
    title character varying,
    description text,
    dt_event timestamp(3) without time zone,
    dt_end_of_sale timestamp(3) without time zone,
    type character varying,
    status character varying,
    category uuid
);

ALTER TABLE concert_service.concert OWNER TO postgres;

ALTER TABLE ONLY concert_service.concert
    ADD CONSTRAINT concert_pkey PRIMARY KEY (uuid);