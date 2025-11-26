# ✅ Roteiro de Testes - Funcionalidades v2.0

## 📋 Objetivo
Este documento fornece um roteiro completo para testar todas as novas funcionalidades implementadas na versão 2.0 do sistema.

---

## 🚀 Preparação

### 1. Compilar o Projeto
```bash
cd /Users/sergiomendes/Documents/java/POO_P2
javac -d bin -sourcepath src/main/java $(find src/main/java -name "*.java")
```

### 2. Executar o Sistema
```bash
java -cp bin com.escolamusica.EscolaMusicaApp
```

### 3. Verificar Carregamento de Dados
Ao iniciar, deve aparecer:
```
✓ Dados de exemplo carregados com sucesso!
```

---

## 🧪 TESTE 1: Agendamento de Aulas

### Teste 1.1: Agendar Aula com Sucesso

**Passos:**
1. Menu Principal → `7` (Gerenciar Aulas)
2. Menu Aulas → `1` (Agendar Aula)
3. Preencher:
   ```
   ID do Curso: 1
   ID do Professor: 1
   ID da Sala: 1
   Data/Hora: 30/12/2024 09:00
   Tipo: 1
   Duração: 60
   ```

**Resultado Esperado:**
```
✓ Aula agendada com sucesso!
ID: AUL-XXXXX
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 1.2: Detectar Conflito de Professor

**Passos:**
1. Após Teste 1.1, agendar outra aula
2. Usar mesmo professor e horário conflitante:
   ```
   ID do Curso: 2
   ID do Professor: 1  (mesmo!)
   ID da Sala: 2
   Data/Hora: 30/12/2024 09:30  (conflita!)
   Tipo: 1
   Duração: 60
   ```

**Resultado Esperado:**
```
❌ Erro: Professor não está disponível neste horário
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 1.3: Detectar Conflito de Sala

**Passos:**
1. Agendar aula com sala já ocupada:
   ```
   ID do Curso: 2
   ID do Professor: 2  (diferente!)
   ID da Sala: 1  (mesma do Teste 1.1!)
   Data/Hora: 30/12/2024 09:15  (conflita!)
   Tipo: 1
   Duração: 60
   ```

**Resultado Esperado:**
```
❌ Erro: Sala não está disponível neste horário
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 1.4: Listar Aulas

**Passos:**
1. Menu Aulas → `2` (Listar Aulas)

**Resultado Esperado:**
- Deve mostrar pelo menos 3 aulas (2 pré-carregadas + 1 do Teste 1.1)
- Informações completas de cada aula

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 1.5: Marcar Aula como Realizada

**Passos:**
1. Anotar ID de uma aula da listagem
2. Menu Aulas → `3` (Marcar Aula como Realizada)
3. Informar ID da aula

**Resultado Esperado:**
```
✓ Aula marcada como realizada!
```

**Status:** [ ] Passou  [ ] Falhou

---

## 🧪 TESTE 2: Gerenciamento de Salas

### Teste 2.1: Cadastrar Sala

**Passos:**
1. Menu Principal → `8` (Gerenciar Salas e Aluguel)
2. Menu Salas → `1` (Cadastrar Sala)
3. Preencher:
   ```
   Número: 103
   Capacidade: 10
   Tipo: Sala Grande
   Valor Hora Aluguel: 100.0
   ```

**Resultado Esperado:**
```
✓ Sala cadastrada com sucesso!
ID: SALA-XXXXX
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 2.2: Listar Salas

**Passos:**
1. Menu Salas → `2` (Listar Salas)

**Resultado Esperado:**
- Deve mostrar 4 salas (3 pré-carregadas + 1 do Teste 2.1)
- Informações completas: número, capacidade, tipo, valor

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 2.3: Verificar Disponibilidade

**Passos:**
1. Menu Salas → `3` (Verificar Disponibilidade)
2. Preencher:
   ```
   ID da Sala: 1
   Data: 26/12/2024
   ```

**Resultado Esperado:**
- Deve mostrar aula pré-agendada para 26/12/2024
- Formato: "HH:mm - HH:mm (Aula)"

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 2.4: Alugar Sala - Aluno Regular

**Passos:**
1. Menu Salas → `4` (Alugar Sala)
2. Preencher:
   ```
   ID do Aluno: 1  (João - Regular)
   ID da Sala: 1
   Início: 31/12/2024 10:00
   Fim: 31/12/2024 12:00
   Finalidade: Prática de piano
   ```

**Resultado Esperado:**
```
✓ Sala alugada com sucesso!
Valor: R$ 100,00  (2h × R$ 50/h, sem desconto)
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 2.5: Alugar Sala - Aluno VIP (Desconto)

**Passos:**
1. Menu Salas → `4` (Alugar Sala)
2. Preencher:
   ```
   ID do Aluno: 3  (Roberto - VIP)
   ID da Sala: 3  (Estúdio - R$ 120/h)
   Início: 01/01/2025 14:00
   Fim: 01/01/2025 17:00
   Finalidade: Gravação de repertório
   ```

**Resultado Esperado:**
```
✓ Sala alugada com sucesso!
Valor: R$ 324,00
(3h × R$ 120/h = R$ 360,00 - 10% VIP = R$ 324,00)
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 2.6: Detectar Conflito de Aluguel

**Passos:**
1. Tentar alugar mesma sala em horário conflitante:
   ```
   ID do Aluno: 2
   ID da Sala: 3  (mesma do Teste 2.5)
   Início: 01/01/2025 15:00  (conflita!)
   Fim: 01/01/2025 18:00
   Finalidade: Ensaio
   ```

**Resultado Esperado:**
```
❌ Erro: Sala não está disponível neste período
```

**Status:** [ ] Passou  [ ] Falhou

---

## 🧪 TESTE 3: Avaliação de Desempenho

### Teste 3.1: Registrar Avaliação - Conceito A

**Passos:**
1. Menu Principal → `9` (Registrar Desempenho)
2. Menu Desempenho → `1` (Registrar Avaliação)
3. Preencher:
   ```
   ID do Aluno: 1
   ID do Curso: 1
   Nota: 9.5
   Nível: Avançado
   Observações: Excelente domínio técnico
   Pontos Fortes: Interpretação, dinâmica, técnica
   Pontos a Melhorar: Repertório mais complexo
   ```

**Resultado Esperado:**
```
✓ Avaliação registrada com sucesso!
Conceito: A
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.2: Registrar Avaliação - Conceito B

**Passos:**
1. Menu Desempenho → `1`
2. Preencher:
   ```
   ID do Aluno: 2
   ID do Curso: 2
   Nota: 7.5
   Nível: Intermediário
   Observações: Boa evolução
   Pontos Fortes: Ritmo, coordenação
   Pontos a Melhorar: Leitura de cifras
   ```

**Resultado Esperado:**
```
✓ Avaliação registrada com sucesso!
Conceito: B
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.3: Registrar Avaliação - Conceito C

**Passos:**
1. Menu Desempenho → `1`
2. Preencher:
   ```
   ID do Aluno: 2
   ID do Curso: 3
   Nota: 6.0
   Nível: Iniciante
   Observações: Precisa de mais prática
   Pontos Fortes: Dedicação
   Pontos a Melhorar: Afinação, respiração
   ```

**Resultado Esperado:**
```
✓ Avaliação registrada com sucesso!
Conceito: C
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.4: Registrar Avaliação - Conceito D

**Passos:**
1. Menu Desempenho → `1`
2. Preencher:
   ```
   ID do Aluno: 3
   ID do Curso: 1
   Nota: 5.0
   Nível: Iniciante
   Observações: Necessita revisar fundamentos
   Pontos Fortes: Esforço
   Pontos a Melhorar: Técnica básica, postura
   ```

**Resultado Esperado:**
```
✓ Avaliação registrada com sucesso!
Conceito: D
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.5: Registrar Avaliação - Conceito F

**Passos:**
1. Menu Desempenho → `1`
2. Preencher:
   ```
   ID do Aluno: 3
   ID do Curso: 2
   Nota: 3.5
   Nível: Iniciante
   Observações: Ausências prejudicaram desempenho
   Pontos Fortes: -
   Pontos a Melhorar: Frequência, dedicação
   ```

**Resultado Esperado:**
```
✓ Avaliação registrada com sucesso!
Conceito: F
```

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.6: Gerar Relatório de Progresso

**Passos:**
1. Menu Desempenho → `2` (Relatório de Progresso)
2. Informar ID do Aluno: `1`

**Resultado Esperado:**
- Relatório formatado com bordas
- Cabeçalho com nome do aluno
- Total de avaliações (deve ter 2: pré-carregada + Teste 3.1)
- Média geral calculada
- Avaliações agrupadas por curso
- Média por curso
- Detalhes de cada avaliação com data
- Taxa de aprovação

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.7: Listar Avaliações de Aluno

**Passos:**
1. Menu Desempenho → `3` (Listar Avaliações de Aluno)
2. Informar ID do Aluno: `2`

**Resultado Esperado:**
- Lista com 2 avaliações (Testes 3.2 e 3.3)
- Informações completas de cada uma
- Separadores entre avaliações

**Status:** [ ] Passou  [ ] Falhou

---

### Teste 3.8: Verificar Cálculo de Média

**Passos:**
1. Gerar relatório para aluno 3 (Testes 3.4 e 3.5)
2. Verificar média: (5.0 + 3.5) / 2 = 4.25

**Resultado Esperado:**
```
Média Geral: 4.25
Taxa de Aprovação: 0.0% (nenhuma nota ≥ 6.0)
```

**Status:** [ ] Passou  [ ] Falhou

---

## 📊 Resumo dos Testes

### Módulo 1: Agendamento de Aulas
- [ ] Teste 1.1: Agendar aula com sucesso
- [ ] Teste 1.2: Detectar conflito de professor
- [ ] Teste 1.3: Detectar conflito de sala
- [ ] Teste 1.4: Listar aulas
- [ ] Teste 1.5: Marcar aula como realizada

**Status do Módulo:** [ ] Aprovado  [ ] Reprovado

---

### Módulo 2: Gerenciamento de Salas
- [ ] Teste 2.1: Cadastrar sala
- [ ] Teste 2.2: Listar salas
- [ ] Teste 2.3: Verificar disponibilidade
- [ ] Teste 2.4: Alugar sala - Regular
- [ ] Teste 2.5: Alugar sala - VIP (desconto)
- [ ] Teste 2.6: Detectar conflito de aluguel

**Status do Módulo:** [ ] Aprovado  [ ] Reprovado

---

### Módulo 3: Avaliação de Desempenho
- [ ] Teste 3.1: Registrar avaliação - Conceito A
- [ ] Teste 3.2: Registrar avaliação - Conceito B
- [ ] Teste 3.3: Registrar avaliação - Conceito C
- [ ] Teste 3.4: Registrar avaliação - Conceito D
- [ ] Teste 3.5: Registrar avaliação - Conceito F
- [ ] Teste 3.6: Gerar relatório de progresso
- [ ] Teste 3.7: Listar avaliações de aluno
- [ ] Teste 3.8: Verificar cálculo de média

**Status do Módulo:** [ ] Aprovado  [ ] Reprovado

---

## ✅ Critérios de Aceitação

### Funcionalidades Essenciais
- [ ] Sistema compila sem erros
- [ ] Sistema executa sem exceções não tratadas
- [ ] Menu principal exibe 10 opções
- [ ] Navegação entre menus funciona
- [ ] Dados de exemplo carregam automaticamente

### Agendamento de Aulas
- [ ] Agendamento cria nova aula
- [ ] Conflito de professor é detectado
- [ ] Conflito de sala é detectado
- [ ] Listagem mostra todas as aulas
- [ ] Status de aula pode ser alterado

### Gerenciamento de Salas
- [ ] Cadastro cria nova sala
- [ ] Listagem mostra todas as salas
- [ ] Disponibilidade é calculada corretamente
- [ ] Aluguel calcula valor corretamente
- [ ] Desconto VIP é aplicado (10%)
- [ ] Conflito de aluguel é detectado

### Avaliação de Desempenho
- [ ] Registro cria nova avaliação
- [ ] Conceitos são calculados corretamente:
  - [ ] A (9.0-10.0)
  - [ ] B (7.0-8.9)
  - [ ] C (6.0-6.9)
  - [ ] D (4.0-5.9)
  - [ ] F (0.0-3.9)
- [ ] Relatório é gerado e formatado
- [ ] Média geral é calculada
- [ ] Média por curso é calculada
- [ ] Taxa de aprovação é calculada
- [ ] Listagem mostra todas as avaliações

---

## 🐛 Registro de Bugs

### Bug #1
**Teste:** _______________  
**Descrição:** _______________  
**Passos para Reproduzir:** _______________  
**Comportamento Esperado:** _______________  
**Comportamento Obtido:** _______________  

### Bug #2
**Teste:** _______________  
**Descrição:** _______________  
**Passos para Reproduzir:** _______________  
**Comportamento Esperado:** _______________  
**Comportamento Obtido:** _______________  

---

## 📈 Resultado Final

**Data do Teste:** _______________  
**Testador:** _______________  

**Estatísticas:**
- Total de Testes: 19
- Testes Aprovados: _____
- Testes Reprovados: _____
- Taxa de Sucesso: _____%

**Conclusão:**
- [ ] ✅ Sistema APROVADO - Pronto para uso
- [ ] ⚠️ Sistema APROVADO COM RESSALVAS - Pequenos ajustes necessários
- [ ] ❌ Sistema REPROVADO - Correções críticas necessárias

**Observações:**
_______________________________________________
_______________________________________________
_______________________________________________

---

**Assinatura:** _____________________________  
**Data:** _______________
