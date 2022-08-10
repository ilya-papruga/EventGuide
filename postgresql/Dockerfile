FROM postgres:14.4-alpine
MAINTAINER ITAcademy

ADD /event-film-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /event-concert-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /event-classifier-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /event-user-service/ddl/*.sql /docker-entrypoint-initdb.d/
ADD /event-user-service/dml/*.sql /docker-entrypoint-initdb.d/

