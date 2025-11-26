@echo off
REM Script de compilação e execução do projeto Escola de Música Nota Máxima

echo ========================================
echo    Escola de Musica Nota Maxima
echo ========================================
echo.

REM Cria diretório bin se não existir
if not exist "bin" (
    mkdir bin
    echo [OK] Diretorio 'bin' criado
)

echo Compilando o projeto...
echo.

REM Compila todos os arquivos Java
dir /s /B src\main\java\*.java > sources.txt
javac -d bin -encoding UTF-8 @sources.txt
del sources.txt

if %ERRORLEVEL% EQU 0 (
    echo [OK] Compilacao concluida com sucesso!
    echo.
    echo Executando a aplicacao...
    echo.
    
    REM Executa a aplicação
    java -cp bin com.escolamusica.EscolaMusicaApp
) else (
    echo [ERRO] Erro na compilacao!
    pause
    exit /b 1
)

pause
