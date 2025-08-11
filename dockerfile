# Etapa 1: Build do projeto com Gradle
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copia os arquivos de configuração primeiro (cache mais eficiente)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Baixa as dependências antes de copiar todo o código
RUN ./gradlew dependencies --no-daemon || true

# Agora copia o código-fonte
COPY . .

# Gera o JAR final
RUN ./gradlew bootJar --no-daemon

# Etapa 2: Imagem final
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o JAR da etapa anterior
COPY --from=build /app/build/libs/expenses.jar app.jar

# Porta do Render
ENV PORT=8080
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "expenses.jar"]
