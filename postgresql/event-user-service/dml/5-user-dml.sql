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


INSERT INTO user_service.roles(
	name)
	VALUES ('ROLE_ADMIN');
INSERT INTO user_service.roles(
	name)
	VALUES ('ROLE_USER');


INSERT INTO user_service.users(
	uuid, dt_create, dt_update, mail, nick, status, password)
	VALUES ('2f809ea2-bd79-478c-a792-994833cc33b3', now(), now(), 'admin@gmail.com', 'admin', 'ACTIVATED', '$2a$10$w/qt2sBP//Jb2nxUzM9mBe6b2hsbA2HSaLomFNtj8EdVCitYDtyz.');
INSERT INTO user_service.users(
	uuid, dt_create, dt_update, mail, nick, status, password)
	VALUES ('6666a6a5-72e5-4290-874f-29d7cdcad4b8', now(), now(), 'user@gmail.com', 'user', 'ACTIVATED', '$2a$10$0exjiJfWjiDjxKcMbOB5fueo0if1gBPqL3lPr78d/Ig12xtcxJtu2');

INSERT INTO user_service.users_roles(
	user_mail, role_name)
	VALUES ('admin@gmail.com', 'ROLE_ADMIN');
INSERT INTO user_service.users_roles(
	user_mail, role_name)
	VALUES ('admin@gmail.com', 'ROLE_USER');
INSERT INTO user_service.users_roles(
	user_mail, role_name)
	VALUES ('user@gmail.com', 'ROLE_USER');