# Pipeline CI — Trabalho 5

Pipeline mínimo e reproduzível em GitHub Actions. A definição está em [`.github/workflows/ci.yml`](../.github/workflows/ci.yml).

## Gatilho

O workflow `CI` inicia quando ocorre um destes eventos:

- `push` na branch `master`
- `pull_request` com destino `master`
- `workflow_dispatch` (execução manual em Actions → CI → Run workflow)

Cada execução fica ligada ao commit que a disparou (`github.sha`).

## Etapas

O job `build` roda em `ubuntu-latest`, um ambiente limpo a cada execução. A etapa seguinte só começa se a anterior terminar com sucesso. Uma falha interrompe o job e impede a publicação do artefato.

1. **Checkout** — obtém o código do commit do evento (`actions/checkout`).
2. **JDK 21** — instala Temurin 21 e restaura o cache do Maven (`actions/setup-java`).
3. **Build e testes** — `./mvnw -B verify` compila, executa os testes existentes e gera o JAR. O wrapper baixa o Maven 3.9.15; `-B` evita prompt interativo.
4. **Artefato** — publica `target/biblioteca-*.jar` com o nome `biblioteca-<sha-do-commit>`. Se o JAR não existir, a etapa falha.

## Dependências

- JDK 21 (Temurin), instalado pelo próprio pipeline
- Apache Maven 3.9.15, baixado pelo Maven Wrapper (`./mvnw`)
- Dependências do `pom.xml`, com cache do Maven entre execuções
- H2 em memória para os testes (nenhum banco externo)
- Secret `JWT_SECRET` do repositório, injetado como `API_SECURITY_TOKEN_SECRET` na etapa de build

O pipeline não faz deploy.

## Artefato

- Arquivo: `biblioteca-0.0.1-SNAPSHOT.jar` (empacotado pelo Spring Boot)
- Nome no Actions: `biblioteca-<sha-do-commit>`
- Onde baixar: página da execução → Artifacts
- O SHA no nome associa o JAR ao commit. A mesma associação aparece na execução do Actions.

## Como reproduzir

1. Confirme que o repositório tem o secret `JWT_SECRET` em Settings → Secrets and variables → Actions. O valor é a chave HMAC usada para assinar o JWT (pelo menos 32 bytes). O pipeline não imprime essa variável.
2. Envie um commit para `master`, abra um pull request contra `master`, ou dispare o workflow manualmente.
3. Acompanhe a execução em Actions → CI.
4. Na execução verde, baixe o artefato `biblioteca-<sha>`.

Para repetir o mesmo fluxo na máquina local, com JDK 21:

```bash
export API_SECURITY_TOKEN_SECRET='cole-aqui-uma-chave-de-pelo-menos-32-bytes'
./mvnw -B verify
```

O JAR fica em `target/`. Os testes usam um valor próprio em `src/test/resources/application.properties` e não precisam do secret de produção.

## Como investigar falhas

1. Abra a execução vermelha em Actions.
2. A etapa com falha fica marcada em vermelho; as seguintes não rodam. Se `Build e testes` falhar, `Publicar artefato` não publica o JAR.
3. Abra o log da etapa. A saída do Maven indica o teste ou o erro de compilação.
4. Associe a execução ao commit pelo SHA exibido no topo da run e no nome que o artefato teria (`biblioteca-<sha>`).
5. Corrija no código, envie outro commit e confira a nova execução.

Para demonstrar a interrupção: altere um teste para falhar, envie o commit e observe o job vermelho sem artefato. Reverta esse commit para voltar à execução verde.

## Como os segredos são protegidos

- `api.security.token.secret` no `application.properties` principal só referencia `${API_SECURITY_TOKEN_SECRET}`. O valor não está no repositório e não entra no JAR.
- No GitHub, o valor fica no secret `JWT_SECRET`. O workflow passa a variável para o Maven e não a escreve no log.
- `src/test/resources/application.properties` tem uma chave exclusiva de teste. Ela existe só no classpath de teste.
- Os logins `admin`/`admin` e `user`/`user` são usuários de demonstração criados na subida da aplicação. Não são o segredo do pipeline.

## Evidências de execução

Os links da execução verde e da execução em que uma falha interrompeu o fluxo entram aqui depois do push para o remoto:

- Sucesso: pendente
- Falha detectada: pendente
