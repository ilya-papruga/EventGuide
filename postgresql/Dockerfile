FROM postgres:14.4-alpine
MAINTAINER ITAcademy

ADD /EventFilmService/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /EventConcertService/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /ClassifierService/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /UserService/ddl/*.sql /docker-entrypoint-initdb.d/
