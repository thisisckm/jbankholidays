FROM amazoncorretto:19-alpine

EXPOSE 8080

RUN mkdir /app
COPY target/bankholidays-0.0.1-SNAPSHOT.jar /app
WORKDIR /app

CMD ["java", "-jar", "bankholidays-0.0.1-SNAPSHOT.jar"]
