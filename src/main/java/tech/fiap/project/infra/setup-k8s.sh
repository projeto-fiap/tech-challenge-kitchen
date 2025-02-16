#!/bin/bash

check_command() {
    command -v "$1" >/dev/null 2>&1 || { echo >&2 "O comando '$1' não está instalado. Por favor, instale-o e tente novamente."; exit 1; }
}

check_command kubectl

if ! minikube status | grep -q "host: Running"; then
    echo "Minikube não está em execução. Inicie o Minikube usando 'minikube start' e tente novamente."
    exit 1
fi

cd src/main/resources/k8s || { echo "Diretório 'src/main/resources/k8s' não encontrado."; exit 1; }

kubectl apply -f db.yaml || { echo "Falha ao aplicar db.yaml"; exit 1; }

kubectl apply -f app-deployment.yaml || { echo "Falha ao aplicar app-deployment.yaml"; exit 1; }

kubectl apply -f service-tech-challenge-kitchen.yaml || { echo "Falha ao aplicar service.yaml"; exit 1; }

kubectl apply -f hpa-tech-challenge-kitchen.yaml || { echo "Falha ao aplicar hpa.yaml"; exit 1; }

echo "Infraestrutura do Kubernetes aplicada com sucesso!"
