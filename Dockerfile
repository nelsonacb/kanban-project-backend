# Imagen base de Java 24
FROM openjdk:24-jdk-slim

# Directorio de trabajo
WORKDIR /app

# Copiar archivos de Maven
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Dar permisos y descargar dependencias
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copiar codigo fuente y construir
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Exponer puerto
EXPOSE 8080

# Ejecutar aplicacion
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
