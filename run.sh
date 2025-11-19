#!/bin/bash

# Script de compilação e execução do projeto Escola de Música Nota Máxima

echo "🎵 Escola de Música Nota Máxima 🎵"
echo "=================================="
echo ""

# Cores para output
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Cria diretório bin se não existir
if [ ! -d "bin" ]; then
    mkdir bin
    echo -e "${GREEN}✓${NC} Diretório 'bin' criado"
fi

echo -e "${YELLOW}Compilando o projeto...${NC}"

# Compila todos os arquivos Java
javac -d bin -encoding UTF-8 $(find src/main/java -name "*.java")

if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Compilação concluída com sucesso!${NC}"
    echo ""
    echo -e "${YELLOW}Executando a aplicação...${NC}"
    echo ""
    
    # Executa a aplicação
    java -cp bin com.escolamusica.EscolaMusicaApp
else
    echo "❌ Erro na compilação!"
    exit 1
fi
