FROM maven:3-openjdk-17 AS build
COPY . .
RUN mvn clean package -Dskiptests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/SpringInvoiceDatabase-1.0-SNAPSHOT.jar SpringInvoiceDatabase.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "SpringInvoiceDatabase.jar" ]