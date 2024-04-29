#Define Base Docker İmage
FROM openjdk:21-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clean install -DskipTests
# Specify the default command to run when the container starts
ENTRYPOINT ["java", "-jar"]

# Provide default arguments
CMD ["target/swe578-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080



