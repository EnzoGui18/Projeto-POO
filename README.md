# Escola de Música Nota Máxima

## Sobre o Projeto

Sistema de gerenciamento completo para escola de música, desenvolvido em Java com foco em Programação Orientada a Objetos (POO). O sistema oferece funcionalidades abrangentes para gerenciar alunos, professores, cursos, matrículas, aulas, eventos, pagamentos, aluguel de salas e avaliação de desempenho acadêmico.

## Funcionalidades Principais

### 1. Gestão de Alunos
- Cadastro de alunos regulares e VIP
- Sistema de benefícios VIP com descontos progressivos
- Promoção de alunos regulares para VIP
- Controle de matrículas e desempenho

### 2. Gerenciamento de Professores
- Cadastro com múltiplas especialidades
- Controle de horários disponíveis
- Registro profissional único

### 3. Cursos e Aulas
- Diversos instrumentos (Piano, Violão, Guitarra, Bateria, Canto, entre outros)
- Três modalidades: Individual, Grupo e Turma
- Níveis: Iniciante, Intermediário e Avançado
- Precificação diferenciada por modalidade
- Agendamento inteligente de aulas com detecção de conflitos
- Controle de disponibilidade de professores e salas
- Visualização de horários ocupados

### 4. Aluguel de Salas e Estúdios
- Gestão de salas com equipamentos
- Cálculo automático de valores
- Desconto VIP automático
- Sistema completo de reserva de salas para prática individual
- Verificação de disponibilidade em tempo real
- Detecção de conflitos entre aulas e aluguéis

### 5. Avaliação de Desempenho
- Registro de notas e avaliações (0-10)
- Classificação automática por conceitos (A-F)
- Registro de pontos fortes e áreas de melhoria
- Relatórios de progresso formatados
- Cálculo de média por curso
- Taxa de aprovação do aluno

### 6. Eventos e Apresentações
- Recitais, workshops e apresentações
- Sistema de vagas com prioridade VIP
- Lista de espera automática
- Gestão de inscrições

### 7. Sistema de Pagamentos
- Geração automática de cobranças
- Cálculo de multas por atraso (2% + 0.033% ao dia)
- Controle de pendências
- Histórico de pagamentos

### 8. Programa de Fidelidade
- Sistema de pontos
- 4 níveis: Bronze, Prata, Ouro e Platina
- Descontos progressivos (0%, 5%, 10%, 15%)
- Promoção automática de níveis

## Arquitetura do Projeto

O projeto segue uma arquitetura em camadas com separação clara de responsabilidades:

```
src/main/java/com/escolamusica/
├── model/              # Entidades do domínio
├── service/            # Lógica de negócio
├── repository/         # Acesso a dados (in-memory)
├── util/               # Classes utilitárias
├── exception/          # Exceções personalizadas
└── EscolaMusicaApp.java # Aplicação principal
```

### Pacotes

#### model/ - Camada de Domínio
- `Pessoa.java` - Classe abstrata base
- `Aluno.java` - Aluno regular
- `AlunoVIP.java` - Aluno com benefícios VIP
- `Professor.java` - Professor com especialidades
- `Curso.java` - Curso de instrumento
- `Aula.java` - Sessão de aula
- `Sala.java` - Sala/estúdio
- `AluguelSala.java` - Aluguel de espaço
- `Matricula.java` - Matrícula em curso
- `Desempenho.java` - Avaliação de aluno
- `Evento.java` - Evento/recital
- `Pagamento.java` - Pagamento
- `PlanoFidelidade.java` - Programa de pontos
- `TipoInstrumento.java` - Enum de instrumentos
- `TipoAula.java` - Enum de modalidades
- `StatusMatricula.java` - Enum de status

#### service/ - Camada de Negócio
- `AlunoService.java` - Regras de negócio de alunos
- `ProfessorService.java` - Regras de professores
- `CursoService.java` - Regras de cursos
- `MatriculaService.java` - Regras de matrículas
- `EventoService.java` - Regras de eventos
- `PagamentoService.java` - Regras de pagamentos
- `AulaService.java` - Agendamento e controle de aulas
- `SalaService.java` - Gestão de salas e estúdios
- `AluguelSalaService.java` - Sistema de aluguel de salas
- `DesempenhoService.java` - Avaliação de desempenho

#### repository/ - Camada de Dados
- `RepositorioGenerico.java` - Repositório base
- Repositórios específicos para cada entidade
- Armazenamento em memória com HashMap

#### util/ - Utilitários
- `ValidadorCPF.java` - Validação de CPF
- `ValidadorEmail.java` - Validação de email
- `FormatadorData.java` - Formatação de datas
- `FormatadorMoeda.java` - Formatação de valores

#### exception/ - Exceções
- `EscolaMusicaException.java` - Exceção base
- `EntidadeNaoEncontradaException.java` - Entidade não encontrada
- `ValidacaoException.java` - Erro de validação
- `NegocioException.java` - Violação de regra de negócio

## Conceitos de POO Aplicados

### 1. Encapsulamento
- Atributos privados com getters e setters
- Validação interna de dados
- Controle de acesso aos dados

### 2. Herança
```java
Pessoa (abstrata)
├── Aluno
│   └── AlunoVIP
└── Professor
```

### 3. Polimorfismo
- Sobrescrita de métodos (`calcularDesconto`, `isVIP`)
- Comportamento especializado em subclasses
- Interface comum através da classe base

### 4. Abstração
- Classe abstrata `Pessoa`
- Repositório genérico
- Interfaces implícitas nos serviços

### 5. Composição
- Relacionamentos entre entidades
- Agregação de objetos (Matricula possui Aluno + Curso)
- Listas de relacionamentos

## Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- IDE Java (Eclipse, IntelliJ IDEA, VS Code, etc.)

### Passos

1. Clone ou baixe o projeto
```bash
cd POO_P2
```

2. Compile o projeto
```bash
# Via linha de comando
javac -d bin src/main/java/com/escolamusica/**/*.java

# Ou use sua IDE favorita
```

3. Execute a aplicação
```bash
java -cp bin com.escolamusica.EscolaMusicaApp

# Ou execute pela IDE
```

## Guia de Uso

### Menu Principal

O sistema apresenta um menu interativo com as seguintes opções:

```
1. Gerenciar Alunos
2. Gerenciar Professores
3. Gerenciar Cursos
4. Gerenciar Matrículas
5. Gerenciar Eventos
6. Gerenciar Pagamentos
7. Gerenciar Aulas
8. Gerenciar Salas e Aluguel
9. Registrar Desempenho
10. Relatórios
0. Sair
```

### Dados de Exemplo

O sistema carrega automaticamente dados de exemplo ao iniciar:
- 2 Professores (Piano/Teclado e Violão/Guitarra)
- 3 Cursos (Piano, Violão e Canto)
- 3 Alunos (2 regulares + 1 VIP)
- 3 Matrículas ativas
- 1 Evento futuro
- 3 Salas (Individual, Grupo e Estúdio)
- 2 Aulas agendadas
- 2 Avaliações de desempenho

### Fluxo de Uso Típico

1. Cadastrar Professor - Adicionar novo professor com especialidades
2. Cadastrar Curso - Criar curso de instrumento
3. Cadastrar Aluno - Registrar novo aluno
4. Matricular Aluno - Inscrever aluno em curso
5. Agendar Aula - Marcar horários de aula verificando disponibilidade
6. Alugar Sala - Reservar sala para prática individual
7. Gerar Pagamento - Criar cobrança mensal
8. Avaliar Desempenho - Registrar notas e progresso do aluno
9. Promover para VIP - Dar benefícios especiais ao aluno
10. Criar Evento - Organizar recital ou apresentação
11. Visualizar Relatórios - Acompanhar estatísticas

## Funcionalidades Avançadas

### Sistema de Agendamento Inteligente

O sistema valida automaticamente conflitos de horários:

```java
// Ao agendar uma aula, verifica:
- Professor está disponível?
- Sala está disponível?
- Curso, professor e sala existem?

// Previne:
- Double-booking de professores
- Double-booking de salas
- Sobreposição de horários
```

### Relatório de Desempenho Formatado

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
Pontos Fortes: Postura, técnica
A Melhorar: Praticar mais escalas
----------------------------------------

Taxa de Aprovação: 100.0%
========================================
```

### Conceitos de Avaliação

| Nota | Conceito | Classificação |
|------|----------|---------------|
| 9.0 - 10.0 | A | Excelente |
| 7.0 - 8.9 | B | Bom |
| 6.0 - 6.9 | C | Regular |
| 4.0 - 5.9 | D | Insuficiente |
| 0.0 - 3.9 | F | Reprovado |

## Exemplos de Funcionalidades

### Aluno VIP - Sistema de Descontos

```java
// Desconto base de 15%
double desconto = alunoVIP.calcularDesconto();

// Aumenta 1% a cada 3 meses (máx. 30%)
// 6 meses = 15% + 2% = 17%
// 12 meses = 15% + 4% = 19%
```

### Programa de Fidelidade

| Nível | Pontos Necessários | Desconto |
|-------|-------------------|----------|
| Bronze | 0 | 0% |
| Prata | 100 | 5% |
| Ouro | 500 | 10% |
| Platina | 1000 | 15% |

### Cálculo de Pagamento com Atraso

```java
Valor Original: R$ 350,00
Dias de Atraso: 10 dias
Multa: 2% fixo + (0.033% × 10 dias) = 2.33%
Total: R$ 358,16
```

### Modalidades de Aula

| Tipo | Capacidade | Ajuste no Valor |
|------|-----------|----------------|
| Individual | 1 aluno | +50% |
| Grupo | 2-8 alunos | Valor padrão |
| Turma | 1-15 alunos | -30% |

## Princípios SOLID Aplicados

- Single Responsibility - Cada classe tem uma responsabilidade única
- Open/Closed - Extensível através de herança (Aluno para AlunoVIP)
- Liskov Substitution - AlunoVIP pode substituir Aluno
- Interface Segregation - Interfaces específicas por funcionalidade
- Dependency Inversion - Serviços dependem de abstrações (repositórios)

## Documentação do Código

Todo o código está documentado com Javadoc:
- Descrição de classes e métodos
- Parâmetros e retornos
- Exceções lançadas
- Exemplos de uso

## Tratamento de Exceções

O sistema possui 4 tipos de exceções personalizadas:

1. EscolaMusicaException - Exceção base
2. EntidadeNaoEncontradaException - Busca sem resultado
3. ValidacaoException - Dados inválidos
4. NegocioException - Violação de regra de negócio

## Pontos de Extensão

O sistema pode ser facilmente estendido:

- Adicionar novos instrumentos no enum `TipoInstrumento`
- Criar novos tipos de alunos (ex: `AlunoBolsista`)
- Implementar persistência em banco de dados
- Adicionar API REST
- Implementar autenticação e autorização
- Gerar relatórios em PDF
- Integração com sistemas de pagamento

## Autor

Escola de Música Nota Máxima  
Projeto desenvolvido para demonstrar conceitos de POO em Java

## Licença

Este projeto é de uso educacional.

---

## Requisitos Implementados

- Cadastro de alunos, professores e cursos
- Sistema VIP com benefícios especiais
- Tipos de aula (Individual, Grupo, Turma)
- Aluguel de salas e estúdios
- Acompanhamento de desempenho
- Calendário de eventos
- Sistema de pagamentos com multas
- Programa de fidelidade com pontos
- Relatórios gerenciais
- Menu interativo completo
- Agendamento de aulas com detecção de conflitos
- Controle de disponibilidade de professores e salas
- Sistema de aluguel de salas para prática individual
- Avaliação de desempenho com conceitos automáticos
- Relatórios de progresso formatados
- Cálculo de média e taxa de aprovação

## Tecnologias Utilizadas

- Java 17+
- Programação Orientada a Objetos
- Collections Framework (HashMap, ArrayList)
- Java Time API (LocalDate, LocalDateTime)
- Pattern Matching (Java 17)
- Text Blocks (Java 17)

---

## Documentação Adicional

- GUIA_RAPIDO.md - Guia rápido de uso com exemplos práticos
- NOVAS_FUNCIONALIDADES.md - Documentação detalhada das funcionalidades
- RESUMO_PROJETO.md - Resumo técnico do projeto
- ESTRUTURA.md - Estrutura completa de arquivos e diretórios
- COMPILAR.md - Instruções detalhadas de compilação

---

Desenvolvido com paixão pela música e programação.

Status: Totalmente Funcional
