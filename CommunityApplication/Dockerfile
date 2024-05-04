#Define Base Docker İmage
FROM openjdk:21-jdk
WORKDIR /app
COPY . .
RUN ls -la
RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw
RUN /bin/sh mvnw dependency:resolve
RUN ./mvnw clean install -DskipTests
# Specify the default command to run when the container starts
ENTRYPOINT ["java", "-jar"]

# Provide default arguments
CMD ["target/swe578-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080



