# 🎵 Guia Rápido - Escola de Música Nota Máxima

## 📋 Índice
1. [Como Executar](#como-executar)
2. [Menu Principal](#menu-principal)
3. [Gerenciar Aulas](#gerenciar-aulas)
4. [Gerenciar Salas](#gerenciar-salas)
5. [Registrar Desempenho](#registrar-desempenho)
6. [Dicas e Atalhos](#dicas-e-atalhos)

---

## 🚀 Como Executar

### Compilar o Projeto
```bash
cd /Users/sergiomendes/Documents/java/POO_P2
javac -d bin -sourcepath src/main/java $(find src/main/java -name "*.java")
```

### Executar o Sistema
```bash
java -cp bin com.escolamusica.EscolaMusicaApp
```

---

## 🏠 Menu Principal

Ao iniciar o sistema, você verá:

```
============================================================
   BEM-VINDO À ESCOLA DE MÚSICA NOTA MÁXIMA
============================================================

1. Gerenciar Alunos
2. Gerenciar Professores
3. Gerenciar Cursos
4. Gerenciar Matrículas
5. Gerenciar Eventos
6. Gerenciar Pagamentos
7. Gerenciar Aulas              ⭐ NOVO
8. Gerenciar Salas e Aluguel    ⭐ NOVO
9. Registrar Desempenho         ⭐ NOVO
10. Relatórios
0. Sair
```

---

## 📅 Gerenciar Aulas

### Opção 7 > 1: Agendar Aula

**Dados Necessários:**
- ID do Curso: `1` (Piano), `2` (Violão), `3` (Canto)
- ID do Professor: `1` ou `2`
- ID da Sala: `1` (Individual), `2` (Grupo), `3` (Estúdio)
- Data/Hora: Formato `dd/MM/yyyy HH:mm` (ex: `26/12/2024 14:00`)
- Tipo: `1` (Individual), `2` (Grupo), `3` (Turma)
- Duração: Em minutos (ex: `60`)

**Exemplo de Uso:**
```
ID do Curso: 1
ID do Professor: 1
ID da Sala: 1
Data/Hora: 26/12/2024 14:00
Tipo: 1
Duração: 60
```

**✅ O Sistema Verifica:**
- Se o professor está disponível nesse horário
- Se a sala está disponível nesse horário
- Se curso, professor e sala existem

### Opção 7 > 2: Listar Aulas

Exibe todas as aulas agendadas com:
- ID da aula
- Curso, Professor, Sala
- Data e hora
- Duração
- Status (AGENDADA/REALIZADA/CANCELADA)

### Opção 7 > 3: Marcar Aula como Realizada

**Dados Necessários:**
- ID da Aula: Obtido através da listagem

---

## 🏢 Gerenciar Salas e Aluguel

### Opção 8 > 1: Cadastrar Sala

**Dados Necessários:**
- Número: Identificador da sala (ex: `102`)
- Capacidade: Número de pessoas (ex: `5`)
- Tipo: `Sala Individual`, `Sala Grupo`, `Estúdio`
- Valor Hora: Preço por hora (ex: `80.0`)

### Opção 8 > 2: Listar Salas

Exibe todas as salas cadastradas com:
- ID, Número, Capacidade
- Tipo, Valor/Hora
- Disponibilidade

**Salas Pré-cadastradas:**
- Sala 101 (Individual) - R$ 50/h
- Sala 102 (Grupo 5 pessoas) - R$ 80/h
- Estúdio 201 - R$ 120/h

### Opção 8 > 3: Verificar Disponibilidade

**Dados Necessários:**
- ID da Sala: `1`, `2` ou `3`
- Data: Formato `dd/MM/yyyy` (ex: `26/12/2024`)

**Mostra:**
- Horários ocupados com aulas
- Horários ocupados com aluguéis
- Períodos livres

### Opção 8 > 4: Alugar Sala

**Dados Necessários:**
- ID do Aluno: `1` (João), `2` (Ana), `3` (Roberto - VIP)
- ID da Sala: `1`, `2` ou `3`
- Data/Hora Início: `dd/MM/yyyy HH:mm`
- Data/Hora Fim: `dd/MM/yyyy HH:mm`
- Finalidade: Texto livre (ex: `Prática individual`)

**💡 Dica:** Use o aluno VIP (ID: 3) para receber 10% de desconto!

**Exemplo:**
```
ID do Aluno: 3
ID da Sala: 3
Data/Hora Início: 26/12/2024 10:00
Data/Hora Fim: 26/12/2024 12:00
Finalidade: Gravação de repertório

✓ Sala alugada com sucesso!
Valor: R$ 216,00 (desconto VIP aplicado)
```

---

## 📊 Registrar Desempenho

### Opção 9 > 1: Registrar Avaliação

**Dados Necessários:**
- ID do Aluno: `1`, `2` ou `3`
- ID do Curso: `1`, `2` ou `3`
- Nota: Valor de 0 a 10 (ex: `8.5`)
- Nível: `Iniciante`, `Intermediário` ou `Avançado`
- Observações: Texto livre
- Pontos Fortes: Texto livre
- Pontos a Melhorar: Texto livre

**Exemplo:**
```
ID do Aluno: 1
ID do Curso: 1
Nota: 8.5
Nível: Intermediário
Observações: Ótimo progresso técnico
Pontos Fortes: Postura, técnica de dedilhado
Pontos a Melhorar: Praticar mais escalas

✓ Avaliação registrada com sucesso!
Conceito: B
```

**Conceitos Automáticos:**
- `A` (9.0-10.0): Excelente
- `B` (7.0-8.9): Bom
- `C` (6.0-6.9): Regular
- `D` (4.0-5.9): Insuficiente
- `F` (0.0-3.9): Reprovado

### Opção 9 > 2: Relatório de Progresso

**Dados Necessários:**
- ID do Aluno: `1`, `2` ou `3`

**Mostra:**
- Total de avaliações
- Média geral do aluno
- Avaliações por curso
- Média de cada curso
- Taxa de aprovação
- Histórico completo com datas

**Exemplo de Saída:**
```
========================================
   RELATÓRIO DE PROGRESSO DO ALUNO
========================================
Aluno: João Pedro (ID: 1)
Data: 25/12/2024

Total de Avaliações: 2
Média Geral: 8.0

----------------------------------------
CURSO: Piano Clássico
Avaliações: 1 | Média: 8.5

25/12/2024 - Nota: 8.5 | Conceito: B
Nível: Intermediário
Observações: Ótimo progresso técnico
Pontos Fortes: Postura, técnica de dedilhado
A Melhorar: Praticar mais escalas
----------------------------------------

Taxa de Aprovação: 100.0%
========================================
```

### Opção 9 > 3: Listar Avaliações de Aluno

**Dados Necessários:**
- ID do Aluno: `1`, `2` ou `3`

Exibe lista completa de todas as avaliações do aluno.

---

## 💡 Dicas e Atalhos

### Dados Pré-carregados

**Alunos:**
- ID `1`: João Pedro (Regular)
- ID `2`: Ana Carolina (Regular)
- ID `3`: Roberto Almeida (VIP - 10% desconto)

**Professores:**
- ID `1`: Carlos Silva (Piano)
- ID `2`: Maria Santos (Violão)

**Cursos:**
- ID `1`: Piano Clássico - R$ 500/mês
- ID `2`: Violão Popular - R$ 350/mês
- ID `3`: Canto Lírico - R$ 400/mês

**Salas:**
- ID `1`: Sala 101 (Individual) - R$ 50/h
- ID `2`: Sala 102 (Grupo) - R$ 80/h
- ID `3`: Estúdio 201 - R$ 120/h

### Formato de Datas

- **Data Completa:** `dd/MM/yyyy HH:mm` (ex: `26/12/2024 14:00`)
- **Apenas Data:** `dd/MM/yyyy` (ex: `26/12/2024`)

### Testar Conflitos

1. Agende uma aula para amanhã às 14h
2. Tente agendar outra aula no mesmo horário
3. Sistema bloqueará por conflito

### Testar Desconto VIP

1. Use aluno ID `3` (Roberto - VIP)
2. Alugue qualquer sala
3. Observe desconto de 10% aplicado

### Navegação Rápida

- Digite `0` em qualquer submenu para voltar
- Digite `0` no menu principal para sair

---

## 🎯 Cenários de Teste Recomendados

### Cenário 1: Agendar Aula Completa
```
Menu: 7 > 1
Curso: 1
Professor: 1
Sala: 1
Data/Hora: 27/12/2024 15:00
Tipo: 1
Duração: 60
```

### Cenário 2: Aluguel com Desconto VIP
```
Menu: 8 > 4
Aluno: 3 (VIP)
Sala: 3 (Estúdio mais caro)
Início: 28/12/2024 10:00
Fim: 28/12/2024 12:00
Finalidade: Gravação profissional
```

### Cenário 3: Avaliar e Gerar Relatório
```
Menu: 9 > 1
Aluno: 1
Curso: 1
Nota: 9.5
Nível: Avançado
[Preencher observações]

Menu: 9 > 2
Aluno: 1
[Ver relatório completo]
```

---

## ⚠️ Mensagens de Erro Comuns

### "Professor não está disponível neste horário"
- Já existe aula agendada para este professor
- Verifique horários com opção "Listar Aulas"

### "Sala não está disponível neste horário"
- Sala ocupada com aula ou aluguel
- Use opção "Verificar Disponibilidade"

### "Entidade não encontrada"
- ID informado não existe
- Use opções de listagem para ver IDs válidos

### "Data/hora inválida"
- Formato incorreto de data
- Use formato: `dd/MM/yyyy HH:mm`

---

## 📞 Estrutura do Código

```
src/main/java/com/escolamusica/
├── model/          # Entidades (Aula, Sala, etc)
├── repository/     # Acesso a dados
├── service/        # Regras de negócio
├── util/           # Formatadores e validadores
├── exception/      # Exceções customizadas
└── main/           # Aplicação principal
```

---

## 🎓 Funcionalidades Principais

✅ Agendamento inteligente de aulas  
✅ Controle de disponibilidade  
✅ Gestão de salas e estúdios  
✅ Sistema de aluguel com desconto VIP  
✅ Registro de avaliações de desempenho  
✅ Relatórios formatados de progresso  
✅ Validação de conflitos de horário  
✅ Cálculo automático de valores  

---

**Última Atualização:** 25/12/2024  
**Versão:** 2.0  
**Status:** ✅ Totalmente Funcional
