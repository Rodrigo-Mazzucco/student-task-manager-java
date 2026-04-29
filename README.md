# Student Task Manager
 
> Sistema acadêmico de gerenciamento de tarefas e disciplinas desenvolvido em Java, com arquitetura MVC construída incrementalmente semana a semana.
 
---
 
## Sobre o Projeto
 
O **Student Task Manager** é uma aplicação de console que ajuda estudantes a organizarem seus estudos. É possível cadastrar disciplinas, gerenciar tarefas e escolher entre diferentes métodos de estudo para otimizar o aprendizado.
 
O projeto é desenvolvido de forma incremental, com uma entrega por semana, evoluindo desde os fundamentos de POO até padrões de design avançados.
 
---
 
## Funcionalidades
 
- 📖 **Cadastro de Disciplinas** — organize suas matérias
- ✅ **Gerenciamento de Tarefas** — crie e liste tarefas por disciplina
- 🧠 **Métodos de Estudo** — escolha entre:
  - 🍅 **Pomodoro** — ciclos de foco e pausa
  - 🃏 **Flashcards** — revisão por cartões
  - 🔁 **Revisão** — revisão tradicional de conteúdo
- 💬 **Menu interativo** — navegação por linha de comando
---
 
## Arquitetura
 
O projeto segue a arquitetura **MVC** com separação clara de responsabilidades:
 
```
src/
├── model/          # Entidades (Tarefa, Disciplina)
├── repository/     # Interface + implementação em memória
├── service/        # Regras de negócio e validações
├── controller/     # Fluxo da aplicação e menu
└── strategy/       # Padrões de estudo (Pomodoro, Flashcards, Revisão)
```
 
---
 
## Padrões de Design Implementados
 
| Padrão | Aplicação |
|--------|-----------|
| **MVC** | Separação entre Model, Controller e camada de dados |
| **Repository** | Isolamento da persistência via interface `TarefaRepository` |
| **Strategy** | Métodos de estudo intercambiáveis via interface `MetodoEstudo` |
 
---
 
## Roadmap de Desenvolvimento
 
| Semana | Tema | Status |
|--------|------|--------|
| 1 | Fundamentos POO — Classe `Tarefa` com atributos e instâncias | ✅ Concluído |
| 2 | Estrutura de Dados — Classe `Disciplina` e `ArrayList` de tarefas | ✅ Concluído |
| 3 | Camada de Dados — Interface `TarefaRepository` e implementação em memória | ✅ Concluído |
| 4 | Regras de Negócio — `TarefaService` com validações | ✅ Concluído |
| 5 | Fluxo MVC — `TarefaController` e menu interativo | ✅ Concluído |
| 6 | Padrão Strategy — Interface `MetodoEstudo` com Pomodoro, Flashcards e Revisão | ✅ Concluído |
| 7 | Padrão Factory — `MetodoEstudoFactory` para instanciar métodos de estudo | 🔄 Em andamento |
| 8 | Qualidade e Testes — JUnit para `Tarefa` e `TarefaService` | ⏳ Pendente |
 
---
 
## Tecnologias
 
- **Java** — linguagem principal
- **Eclipse IDE** — ambiente de desenvolvimento
- **JUnit** *(semana 8)* — testes unitários
---
 
## Como Executar
 
1. Clone o repositório
```bash
git clone https://github.com/Rodrigo-Mazzucco/student-task-manager-java.git
```
2. Abra no **Eclipse IDE**
3. Execute a classe `Main.java`
---
 
## Autor
 
**Rodrigo Marchione Mazzucco**
 
[![LinkedIn](https://img.shields.io/badge/-LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rodrigo-marchione-mazzucco)
[![GitHub](https://img.shields.io/badge/-GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Rodrigo-Mazzucco)
 
