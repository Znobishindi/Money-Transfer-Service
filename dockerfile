FROM openjdk:17-oracle

VOLUME /tmp

EXPOSE 5500

ADD /target/MoneyTransferService-0.0.1-SNAPSHOT.jar myapp.jar

CMD ["java","-jar","/myapp.jar"]
