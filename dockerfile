FROM openjdk:21-ea-24-oracle

WORKDIR /app

COPY target/aneback-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet_AnemonaDB /app/Wallet_AnemonaDB

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]