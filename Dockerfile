FROM openjdk:8
LABEL author="Stefan Meier <Stefan.Meier@cdi-ag.de>"

RUN apt-get update
RUN apt-get install -y apt-utils
RUN apt-get -y install htop
RUN apt-get install -y maven

ENV AWS_REGION="us_west_2"
ENV APPDIR=/usr/app
WORKDIR $APPDIR

ADD . $APPDIR/
CMD ls

EXPOSE 9000

ENTRYPOINT mvn -pl CodeGuru -am spring-boot:run