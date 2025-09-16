# === Stage 1: Build com Maven ===
FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

# Copia o pom.xml e faz o download das dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código fonte
COPY src ./src

# Build da aplicação
RUN mvn clean package -DskipTests

# === Stage 2: Runtime ===
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia o jar do estágio anterior
COPY --from=build /app/target/aluno-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
