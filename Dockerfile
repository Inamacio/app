FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /opt/app

COPY /target/app*.jar app.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 8080
EXPOSE 5005

CMD java ${ADDITIONAL_OPTS} -jar app.jar --spring.profiles.active=${PROFILE}