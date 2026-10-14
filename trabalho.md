
---

## Slide 62: Trabalho 5 — Lançamento

- Tema:
  - Pipeline CI mínimo e reprodutível
- Valor:
  - Até 1,0 ponto
- Equipes:
  - Conforme organização definida pelo professor
- Entrega:
  - Aula 10, após a semana da Jornada Acadêmica
- Ferramenta:
  - Jenkins, GitLab CI, GitHub Actions ou outra autorizada

---

## Slide 63: T5 — Objetivo

- Implementar um pipeline que possa ser repetido a partir do repositório
- O pipeline deve:
  - Ser iniciado por evento definido
  - Obter o código correto
  - Executar build
  - Executar testes disponíveis
  - Interromper em caso de falha
  - Produzir ou identificar um artefato
  - Manter evidências de execução
- A Aula 10 aprofundará configuração e ferramentas de CI

---

## Slide 64: T5 — Entregáveis

- Repositório da equipe
- Arquivo de definição do pipeline
- Instruções para execução
- Identificação de:
  - Gatilho
  - Etapas
  - Dependências
  - Artefato
- Evidência de execução bem-sucedida
- Evidência de uma falha detectada
- Breve explicação sobre:
  - Como reproduzir
  - Como investigar falhas
  - Como os segredos são protegidos

---

## Slide 65: T5 — Escopo Mínimo

- Obrigatório:
  - Checkout
  - Build
  - Testes existentes
  - Resultado visível
  - Artefato identificado
- Recomendado, quando aplicável:
  - Cache de dependências
  - Análise estática
  - Construção de imagem
  - Detecção de segredos
- Não é obrigatório nesta entrega:
  - Deploy automático em produção
  - Estratégias blue/green ou canary

---

## Slide 66: T5 — Critérios de Avaliação

| Critério | Valor |
|---|---:|
| Pipeline executável e reprodutível |  |
| Build, testes e tratamento de falhas |  |
| Artefato e rastreabilidade |  |
| Evidências e instruções |  |
| Segurança básica e organização |  |
| **Total** | **1,00** |

---

## Slide 67: Checklist do Trabalho 5

- O repositório pode ser acessado pelo professor?
- O pipeline possui um gatilho compreensível?
- O build parte de ambiente limpo?
- Testes são executados?
- Uma falha interrompe corretamente o fluxo?
- O artefato possui identificação?
- A execução pode ser associada ao commit?
- Logs e evidências estão disponíveis?
- Não existem segredos no repositório ou nos logs?
- Outra equipe conseguiria reproduzir o processo?

---