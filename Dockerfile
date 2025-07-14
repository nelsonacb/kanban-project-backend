# Imagen base con Maven y Java 24
FROM maven:3.9.6-openjdk-24-slim AS build

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar codigo fuente y construir
COPY src ./src
RUN mvn clean package -DskipTests

# Imagen final solo con Java
FROM openjdk:24-jre-slim

# Directorio de trabajo
WORKDIR /app

# Copiar el JAR construido
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicacion
CMD ["java", "-jar", "app.jar"]
