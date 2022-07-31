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

CREATE SCHEMA user_service;

SET default_tablespace = '';

SET default_table_access_method = heap;

CREATE TABLE user_service.roles (
    name character varying NOT NULL
);

CREATE TABLE user_service.users (
    uuid uuid NOT NULL,
    dt_create timestamp(3) without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    mail character varying,
    nick character varying,
    status character varying,
    password character varying
);

CREATE TABLE user_service.users_roles (
    user_mail character varying,
    role_name character varying
);

ALTER TABLE ONLY user_service.users
    ADD CONSTRAINT mail UNIQUE (mail);

ALTER TABLE ONLY user_service.users
    ADD CONSTRAINT nick UNIQUE (nick);

ALTER TABLE ONLY user_service.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (name);

ALTER TABLE ONLY user_service.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uuid);

ALTER TABLE ONLY user_service.users_roles
    ADD CONSTRAINT role FOREIGN KEY (role_name) REFERENCES user_service.roles(name);