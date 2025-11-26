# 💡 Exemplos Práticos de Uso - Escola de Música v2.0

## 📋 Índice
1. [Cenário 1: Agendamento de Aula Semanal](#cenário-1-agendamento-de-aula-semanal)
2. [Cenário 2: Aluno VIP Aluga Estúdio](#cenário-2-aluno-vip-aluga-estúdio)
3. [Cenário 3: Avaliação Trimestral](#cenário-3-avaliação-trimestral)
4. [Cenário 4: Conflito de Horários](#cenário-4-conflito-de-horários)
5. [Cenário 5: Relatório de Progresso Completo](#cenário-5-relatório-de-progresso-completo)

---

## Cenário 1: Agendamento de Aula Semanal

### 📝 Descrição
Professor Carlos precisa agendar aula de piano para o aluno João Pedro toda segunda-feira às 14h.

### 🔄 Fluxo de Execução

```
1. Iniciar sistema
2. Menu Principal → Opção 7 (Gerenciar Aulas)
3. Menu Aulas → Opção 1 (Agendar Aula)
```

### ⌨️ Entrada de Dados

```
ID do Curso: 1
ID do Professor: 1
ID da Sala: 1
Data/Hora: 30/12/2024 14:00
Tipo: 1 (INDIVIDUAL)
Duração: 60
```

### ✅ Resultado Esperado

```
✓ Aula agendada com sucesso!
ID: AUL-XXXXX

Detalhes:
- Curso: Piano Clássico
- Professor: Carlos Silva
- Sala: 101
- Data/Hora: 30/12/2024 14:00
- Duração: 60 minutos
- Status: AGENDADA
```

### 🎯 Validações Realizadas

✅ Curso ID=1 existe  
✅ Professor ID=1 existe  
✅ Sala ID=1 existe  
✅ Professor Carlos está disponível às 14h  
✅ Sala 101 está disponível às 14h  

---

## Cenário 2: Aluno VIP Aluga Estúdio

### 📝 Descrição
Roberto (aluno VIP) quer alugar o estúdio de gravação por 2 horas para preparar repertório para um recital.

### 🔄 Fluxo de Execução

```
1. Menu Principal → Opção 8 (Gerenciar Salas e Aluguel)
2. Menu Salas → Opção 3 (Verificar Disponibilidade) [Opcional]
3. Menu Salas → Opção 4 (Alugar Sala)
```

### ⌨️ Entrada de Dados

```
ID do Aluno: 3
ID da Sala: 3
Data/Hora Início: 02/01/2025 10:00
Data/Hora Fim: 02/01/2025 12:00
Finalidade: Gravação de repertório para recital
```

### ✅ Resultado Esperado

```
✓ Sala alugada com sucesso!

Detalhes do Aluguel:
- Aluno: Roberto Almeida (VIP)
- Sala: Estúdio 201
- Período: 02/01/2025 10:00 até 12:00
- Duração: 2 horas
- Valor Base: R$ 240,00
- Desconto VIP (10%): R$ 24,00
- Valor Total: R$ 216,00
```

### 💰 Cálculo Detalhado

```
Valor Hora Estúdio: R$ 120,00
Duração: 2 horas
Valor Base = R$ 120,00 × 2 = R$ 240,00
Desconto VIP = R$ 240,00 × 10% = R$ 24,00
Valor Final = R$ 240,00 - R$ 24,00 = R$ 216,00
```

### 🎯 Benefício VIP

💎 **Economia:** R$ 24,00 (10% de desconto)  
📊 **Comparação:** Aluno regular pagaria R$ 240,00  

---

## Cenário 3: Avaliação Trimestral

### 📝 Descrição
Fim do trimestre: Professor Carlos avalia o desempenho de João Pedro no curso de piano.

### 🔄 Fluxo de Execução

```
1. Menu Principal → Opção 9 (Registrar Desempenho)
2. Menu Desempenho → Opção 1 (Registrar Avaliação)
```

### ⌨️ Entrada de Dados

```
ID do Aluno: 1
ID do Curso: 1
Nota: 8.5
Nível: Intermediário
Observações: Demonstrou excelente evolução técnica no trimestre
Pontos Fortes: Postura correta, técnica de dedilhado, leitura de partitura
Pontos a Melhorar: Velocidade nas escalas, expressividade na interpretação
```

### ✅ Resultado Esperado

```
✓ Avaliação registrada com sucesso!

Conceito: B (Bom)
Data: 25/12/2024

Resumo da Avaliação:
- Aluno: João Pedro
- Curso: Piano Clássico
- Nota: 8.5
- Conceito: B
- Nível: Intermediário
- Status: Aprovado (nota ≥ 6.0)
```

### 📊 Classificação Automática

```
Nota 8.5 → Faixa 7.0-8.9 → Conceito B (Bom)
```

---

## Cenário 4: Conflito de Horários

### 📝 Descrição
Tentativa de agendar duas aulas para o mesmo professor no mesmo horário (deve ser bloqueado).

### 🔄 Fluxo de Execução

**Primeira Aula (sucesso):**
```
Menu: 7 > 1
ID do Curso: 1
ID do Professor: 1
ID da Sala: 1
Data/Hora: 05/01/2025 15:00
Tipo: 1
Duração: 60

✓ Aula agendada com sucesso!
```

**Segunda Aula (conflito):**
```
Menu: 7 > 1
ID do Curso: 2
ID do Professor: 1  (mesmo professor!)
ID da Sala: 2
Data/Hora: 05/01/2025 15:30  (sobreposição!)
Tipo: 1
Duração: 60

❌ Erro: Professor não está disponível neste horário
```

### 🔍 Análise do Conflito

```
Aula 1: 15:00 - 16:00 (Professor Carlos)
Aula 2: 15:30 - 16:30 (Professor Carlos)

Sobreposição: 15:30 - 16:00 (30 minutos)
Resultado: CONFLITO DETECTADO ❌
```

### ✅ Resolução

Para agendar a segunda aula, use:
- **Opção 1:** Outro professor (ID: 2 - Maria)
- **Opção 2:** Outro horário (16:00 ou depois)

**Agendamento Correto:**
```
ID do Professor: 2 (Maria Santos)
Data/Hora: 05/01/2025 15:30
✓ Aula agendada com sucesso!
```

---

## Cenário 5: Relatório de Progresso Completo

### 📝 Descrição
Após 3 meses de curso, João Pedro quer ver seu progresso completo em todos os cursos.

### 🔄 Fluxo de Execução

**1. Registrar múltiplas avaliações:**

```
Menu: 9 > 1 (3 vezes)

Avaliação 1:
- Curso: Piano (ID: 1)
- Nota: 8.5
- Nível: Intermediário

Avaliação 2:
- Curso: Piano (ID: 1)
- Nota: 9.0
- Nível: Intermediário

Avaliação 3:
- Curso: Canto (ID: 3)
- Nota: 7.5
- Nível: Iniciante
```

**2. Gerar relatório:**

```
Menu: 9 > 2
ID do Aluno: 1
```

### ✅ Resultado Esperado

```
========================================
   RELATÓRIO DE PROGRESSO DO ALUNO
========================================
Aluno: João Pedro (ID: 1)
Data: 25/12/2024

Total de Avaliações: 3
Média Geral: 8.33

----------------------------------------
CURSO: Piano Clássico
Avaliações: 2 | Média: 8.75

25/11/2024 - Nota: 8.5 | Conceito: B
Nível: Intermediário
Observações: Demonstrou evolução técnica
Pontos Fortes: Postura, dedilhado
A Melhorar: Velocidade nas escalas

25/12/2024 - Nota: 9.0 | Conceito: A
Nível: Intermediário
Observações: Excelente domínio técnico
Pontos Fortes: Interpretação, dinâmica
A Melhorar: Repertório mais avançado
----------------------------------------

----------------------------------------
CURSO: Canto Lírico
Avaliações: 1 | Média: 7.5

25/12/2024 - Nota: 7.5 | Conceito: B
Nível: Iniciante
Observações: Boa projeção vocal
Pontos Fortes: Respiração, afinação
A Melhorar: Técnica de apoio
----------------------------------------

Taxa de Aprovação: 100.0%
(3 de 3 avaliações com nota ≥ 6.0)
========================================
```

### 📊 Análise do Relatório

**Desempenho Geral:**
- 3 avaliações realizadas
- Média geral: 8.33 (Bom)
- Taxa de aprovação: 100%

**Por Curso:**
- Piano: Média 8.75 (Evolução de B para A)
- Canto: Média 7.5 (Conceito B mantido)

**Evolução:**
📈 Piano: +0.5 pontos (melhoria constante)
📊 Canto: Iniciante com bom desempenho

---

## 🎯 Cenários Adicionais Rápidos

### Verificar Disponibilidade de Sala

```
Menu: 8 > 3
ID da Sala: 1
Data: 27/12/2024

Resultado:
Horários Ocupados da Sala 101 em 27/12/2024:
- 14:00 - 15:00 (Aula de Piano)
- 16:00 - 18:00 (Aluguel - João Pedro)

Horários Livres:
- Antes de 14:00
- 15:00 - 16:00
- Após 18:00
```

### Listar Todas as Aulas

```
Menu: 7 > 2

--- LISTA DE AULAS ---
Total: 3

ID: AUL-001
Curso: Piano Clássico
Professor: Carlos Silva
Sala: 101
Data/Hora: 26/12/2024 14:00
Duração: 60 minutos
Status: AGENDADA
-----------------------------------

ID: AUL-002
Curso: Violão Popular
Professor: Maria Santos
Sala: 102
Data/Hora: 27/12/2024 15:00
Duração: 90 minutos
Status: AGENDADA
-----------------------------------
```

### Marcar Aula como Realizada

```
Menu: 7 > 3
ID da Aula: AUL-001

✓ Aula marcada como realizada!

Status atualizado:
AUL-001: AGENDADA → REALIZADA
```

---

## 📝 Dicas de Uso

### 1. Sempre Verifique Disponibilidade

Antes de agendar aula ou alugar sala:
```
Menu 7 > 2 (Listar Aulas) ou
Menu 8 > 3 (Verificar Disponibilidade)
```

### 2. Use IDs dos Dados Pré-carregados

**Alunos:**
- ID 1: João Pedro (Regular)
- ID 2: Ana Carolina (Regular)
- ID 3: Roberto Almeida (VIP) ← Use para testar descontos

**Professores:**
- ID 1: Carlos Silva (Piano/Teclado)
- ID 2: Maria Santos (Violão/Guitarra)

**Cursos:**
- ID 1: Piano Clássico
- ID 2: Violão Popular
- ID 3: Canto Lírico

**Salas:**
- ID 1: Sala 101 (Individual) - R$ 50/h
- ID 2: Sala 102 (Grupo) - R$ 80/h
- ID 3: Estúdio 201 - R$ 120/h

### 3. Formato de Datas

✅ Correto:
```
Data/Hora: 26/12/2024 14:00
Data: 26/12/2024
```

❌ Incorreto:
```
26-12-2024
2024/12/26
12/26/2024 (formato americano)
```

### 4. Teste Conflitos Propositalmente

Para entender a validação, tente:
1. Agendar aula com professor às 14h
2. Agendar outra com mesmo professor às 14:30
3. Observe o bloqueio automático

### 5. Acompanhe Evolução do Aluno

```
1. Registre avaliações periodicamente
2. Gere relatório ao final do período
3. Compare notas e conceitos
4. Identifique evolução
```

---

## ⚠️ Erros Comuns e Soluções

### Erro: "Entidade não encontrada"

**Causa:** ID inexistente

**Solução:**
```
1. Liste as entidades primeiro
   - Menu 1 > 2 (Listar Alunos)
   - Menu 2 > 2 (Listar Professores)
   - Menu 8 > 2 (Listar Salas)
2. Use IDs válidos da listagem
```

### Erro: "Professor não disponível"

**Causa:** Conflito de horário

**Solução:**
```
1. Liste aulas: Menu 7 > 2
2. Escolha horário sem conflito
3. OU use outro professor
```

### Erro: "Sala não disponível"

**Causa:** Sala ocupada com aula ou aluguel

**Solução:**
```
1. Verifique disponibilidade: Menu 8 > 3
2. Escolha horário livre
3. OU use outra sala
```

### Erro: "Data/hora inválida"

**Causa:** Formato incorreto

**Solução:**
```
✅ Use: dd/MM/yyyy HH:mm
Exemplo: 26/12/2024 14:00
```

---

## 🎓 Exercícios Práticos

### Exercício 1: Semana Completa
Agende uma semana completa de aulas para João Pedro:
- Segunda: Piano 14h
- Quarta: Piano 16h
- Sexta: Canto 15h

### Exercício 2: Aluguel Múltiplo
Roberto (VIP) quer alugar estúdio:
- Terça: 10h-12h (2 horas)
- Quinta: 14h-17h (3 horas)

Calcule economia total do desconto VIP.

### Exercício 3: Avaliação Completa
Registre 5 avaliações para João Pedro em diferentes cursos e gere relatório final.

### Exercício 4: Detecção de Conflitos
Tente agendar 3 aulas para Professor Carlos no mesmo dia:
- 14h-15h (Piano)
- 14h30-15h30 (Violão) ← Deve falhar
- 15h-16h (Canto) ← Deve funcionar

---

**📚 Para mais informações, consulte:**
- GUIA_RAPIDO.md - Guia de uso rápido
- NOVAS_FUNCIONALIDADES.md - Documentação completa
- README.md - Visão geral do sistema
