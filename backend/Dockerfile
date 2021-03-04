FROM openjdk:11-jre-slim

EXPOSE 8080

ADD build/libs/kundenportal.jar app.jar

ENTRYPOINT exec java ${JAVA_OPTS:-} \
  -Djava.security.egd=file:/dev/urandom \
  -Duser.timezone="Europe/Berlin" \
  -Dhttps.protocols="TLSv1.2" \
  -jar /app.jar
