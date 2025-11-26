# 📖 Guia de Compilação e Execução

## Opção 1: Scripts Automatizados

### No macOS/Linux:

```bash
# Torne o script executável (apenas primeira vez)
chmod +x run.sh

# Execute o script
./run.sh
```

### No Windows:

```batch
# Execute o script
run.bat
```

## Opção 2: Compilação Manual

### Passo 1: Criar diretório bin

```bash
mkdir bin
```

### Passo 2: Compilar o projeto

#### macOS/Linux:
```bash
javac -d bin -encoding UTF-8 $(find src/main/java -name "*.java")
```

#### Windows (CMD):
```batch
dir /s /B src\main\java\*.java > sources.txt
javac -d bin -encoding UTF-8 @sources.txt
del sources.txt
```

#### Windows (PowerShell):
```powershell
javac -d bin -encoding UTF-8 (Get-ChildItem -Path src\main\java -Filter *.java -Recurse | Select-Object -ExpandProperty FullName)
```

### Passo 3: Executar a aplicação

```bash
java -cp bin com.escolamusica.EscolaMusicaApp
```

## Opção 3: Usando IDE

### Eclipse:

1. Importe o projeto: `File → Import → Existing Projects into Workspace`
2. Selecione o diretório `POO_P2`
3. Clique com botão direito em `EscolaMusicaApp.java`
4. Selecione `Run As → Java Application`

### IntelliJ IDEA:

1. Abra o projeto: `File → Open` e selecione o diretório `POO_P2`
2. Aguarde a indexação
3. Clique com botão direito em `EscolaMusicaApp.java`
4. Selecione `Run 'EscolaMusicaApp.main()'`

### VS Code:

1. Abra o diretório do projeto
2. Instale a extensão "Extension Pack for Java" (se ainda não tiver)
3. Abra `EscolaMusicaApp.java`
4. Clique em `Run` acima do método `main()`

## Requisitos

- **Java JDK**: Versão 17 ou superior
- **Memória**: Mínimo 512 MB RAM
- **Sistema Operacional**: Windows, macOS ou Linux

## Verificar versão do Java

```bash
java -version
javac -version
```

Deve exibir Java 17 ou superior.

## Problemas Comuns

### "javac: command not found"
- Instale o JDK e configure a variável de ambiente JAVA_HOME

### "Class not found"
- Verifique se compilou corretamente
- Confirme que está executando do diretório correto

### Caracteres especiais não aparecem
- Use: `java -Dfile.encoding=UTF-8 -cp bin com.escolamusica.EscolaMusicaApp`

## Estrutura após Compilação

```
POO_P2/
├── src/
│   └── main/java/com/escolamusica/...
├── bin/
│   └── com/escolamusica/...          ← Classes compiladas (.class)
├── README.md
├── COMPILAR.md
├── run.sh                             ← Script Linux/Mac
└── run.bat                            ← Script Windows
```

## Limpeza (remover arquivos compilados)

```bash
# Linux/Mac
rm -rf bin/*

# Windows
rmdir /s /q bin
mkdir bin
```

---

**Dica:** Use os scripts automatizados (`run.sh` ou `run.bat`) para maior praticidade! 🚀
