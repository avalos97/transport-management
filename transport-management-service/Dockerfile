# Empezar con una imagen base conteniendo Java y Maven
FROM maven:3.8.4-openjdk-17-slim as builder

# Variables de entorno para Maven
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENV MAVEN_ARGS="--batch-mode --errors --fail-at-end --show-version -DinstallAtEnd=true -DdeployAtEnd=true"

# Crear la carpeta donde se ubicará la aplicación
WORKDIR /build

# Copiar los archivos pom.xml y src/ al directorio de trabajo
COPY pom.xml .
COPY src ./src

# Empaquetar la aplicación
RUN mvn clean package

# Segunda etapa: crear la imagen de producción
FROM openjdk:17-jre-slim

WORKDIR /app

# Copiar el jar del proyecto a la imagen de producción
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
