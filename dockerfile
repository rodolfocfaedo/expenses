# Usar OpenJDK 21
FROM openjdk:21-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar arquivos do Gradle
COPY gradle gradle
COPY gradlew .
#COPY gradle.properties .
COPY build.gradle .
COPY settings.gradle .

# Copiar código-fonte
COPY src src

# Dar permissão de execução ao Gradle Wrapper
RUN chmod +x ./gradlew

# Gerar o JAR da aplicação
RUN ./gradlew clean bootJar -x test

# Definir variável de ambiente para o Render
ENV PORT=10000

# Expor a porta (opcional para Render, mas útil localmente)
EXPOSE ${PORT}

# Executar o aplicativo
# Detecta automaticamente o nome do jar gerado
CMD ["sh", "-c", "java -jar -Dserver.port=${PORT} build/libs/*.jar"]
