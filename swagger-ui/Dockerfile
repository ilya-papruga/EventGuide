FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademy

ADD /classifier-service/spec/classifier-service.yml /usr/share/nginx/html/doc/classifier-service.yml
ADD /events-service/spec/events-service.yml /usr/share/nginx/html/doc/events-service.yml
ADD /user-service/spec/user-service.yml /usr/share/nginx/html/doc/user-service.yml


ENV URLS="[{ url: 'doc/classifier-service.yml', name: 'Classifier'},{ url: 'doc/events-service.yml', name: 'Events'},{ url: 'doc/user-service.yml', name: 'Users'},]"
ENV URLS_PRIMARY_NAME="Events"