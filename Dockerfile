FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Instala o Maven
RUN apt-get update \
    && apt-get install --no-install-recommends -y maven \
    && apt-get clean

# Expõe a porta da aplicação
EXPOSE 8080

# Define o comando de entrada, rodando a aplicação com Maven
CMD ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=dev"]
