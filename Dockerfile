FROM openjdk:8
LABEL author="Stefan Meier <Stefan.Meier@cdi-ag.de>"

ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update
RUN apt-get install -y apt-utils
RUN apt-get -y install htop
RUN apt-get install -y maven

ENV APPDIR=/usr/app
WORKDIR $APPDIR

ADD . $APPDIR/

ENTRYPOINT mvn -pl CodeGuru -am spring-boot:run