# Usar OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar Gradle Wrapper e arquivos de build
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Copiar o código-fonte
COPY src src

# Garantir permissão de execução do gradlew
RUN chmod +x ./gradlew

# Fazer build da aplicação (sem rodar testes)
RUN ./gradlew clean bootJar -x test

# Expor a porta usada pelo Render
EXPOSE 10000

# Definir variável de ambiente para a porta
ENV PORT=10000

# Rodar a aplicação
CMD ["java", "-jar", "-Dserver.port=${PORT}", "build/libs/expenses.jar"]
