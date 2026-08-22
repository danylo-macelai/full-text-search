FROM eclipse-temurin:26-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
RUN chmod +x mvnw
COPY src src
RUN ./mvnw clean package -DskipTests --no-transfer-progress


FROM eclipse-temurin:26-jre AS runtime
WORKDIR /app
COPY --from=build /app/target/full-text-search-lab-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]