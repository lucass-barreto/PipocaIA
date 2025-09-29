# PipocaIA - Sistema de recomendação de filmes com IA

![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.5-green?logo=spring&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-4.0-red?logo=apachemaven&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-green?logo=thymeleaf&logoColor=white)
[![OpenAI API](https://img.shields.io/badge/OpenAI-API-10a37f?logo=openai&logoColor=white)](https://openai.com/api/)
[![TMDb API](https://img.shields.io/badge/TMDb-API-01d277?logo=themoviedatabase&logoColor=white)](https://developer.themoviedb.org/docs)


Aplicação web full-stack que utiliza Inteligência Artificial para gerar recomendações de filmes personalizadas. O projeto foi desenvolvido como um estudo aprofundado em arquiteturas reativas no ecossistema Spring, demonstrando a orquestração de múltiplas APIs externas e a construção de uma interface de usuário dinâmica e interativa.

---

### 📸 Visão Geral da Interface

A aplicação conta com um dashboard limpo e moderno, com uma temática de cinema, que centraliza todas as funcionalidades, desde o catálogo de filmes do usuário até a exibição das recomendações.

#### **Dashboard do Catálogo Vazio (Estado Inicial):**
*Tela de boas-vindas amigável para novos usuários, guiando-os para a primeira ação e demonstrando a experiência inicial do aplicativo.*

![Dashboard do catálogo vazio](assets/estado-vazio.PNG)

#### **Dashboard do Catálogo Preenchido:**
*Tabela com todos os filmes avaliados pelo usuário, com funcionalidades completas de CRUD (Criar, Ler, Atualizar, Deletar).*

![Dashboard do catálogo](assets/dashboard.PNG)

#### **Formulário de Adição/Alteração:**
*Formulários intuitivos para adicionar e alterar filmes no catálogo, com um seletor de avaliação de 5 estrelas.*

![Página de adição de filme](assets/form-adicionar.PNG)
![Página de alteração de filme](assets/form-alterar.PNG)

#### **Recomendações Geradas pela IA:**
*Grid responsivo com os filmes recomendados pela IA, enriquecidos com dados (pôster, sinopse, nota) obtidos da API da TMDb.*

![Exemplo de recomendação gerada](assets/recomendacoes.PNG)

---

### 📜 Descrição Completa

**PipocaIA** é um sistema inteligente que oferece recomendações de filmes baseadas nas avaliações prévias de um usuário. A aplicação permite o gerenciamento completo de um catálogo pessoal de filmes e, com base nesses dados, consome a API da OpenAI para gerar sugestões novas e criativas. Em seguida, orquestra uma segunda chamada à API da TMDb (The Movie Database) para enriquecer as sugestões com informações detalhadas, como pôsteres, sinopses e datas de lançamento.
O back-end foi construído com Spring WebFlux, utilizando o Project Reactor (Mono e Flux) para um processamento totalmente assíncrono e não-bloqueante. A interface de usuário foi renderizada no lado do servidor com Thymeleaf e estilizada com Bootstrap 5, proporcionando uma experiência de usuário moderna e responsiva.

---

### 🚀 Funcionalidades

- 🤖 **Recomendações com IA:** Integração com a API da OpenAI para gerar listas de filmes personalizadas.
- 🖼️ **Enriquecimento de Dados:** Consumo da API da TMDb para buscar detalhes e pôsteres dos filmes recomendados.
- 🚀 **Back-end Reativo:** Arquitetura 100% assíncrona com Spring WebFlux para alta performance.
- 🖥️ **Dashboard Interativo:** Interface web com Thymeleaf para visualização do catálogo.
- 📝 **CRUD Completo:** Funcionalidades para Adicionar, Listar, Alterar e Deletar filmes do catálogo pessoal.
- 🗄️ **Versionamento de Banco de Dados:** Uso do Flyway para gerenciar as migrações do esquema do banco.
- 📱 **Interface Responsiva:** Design construído com Bootstrap 5, adaptável a diferentes tamanhos de tela.

---

### 💪 Desafios Superados

Durante o desenvolvimento, vários desafios foram superados, servindo como grandes pontos de aprendizado:

- **Programação Reativa:** Aprendizado e aplicação aprofundada dos conceitos de **Mono e Flux** do Project Reactor para gerenciar o fluxo de dados assíncrono, especialmente na orquestração de múltiplas chamadas de API.
- **Orquestração de APIs Externas:** Implementação de uma cadeia de chamadas complexa: **Banco de Dados -> Serviço OpenAI -> Serviço TMDb**. O resultado de uma chamada servia como entrada para a próxima, exigindo um controle de fluxo reativo com operadores como **flatMap e concatMap**.
- **Rate Limiting:** Diagnóstico e solução de problemas de Rate Limiting (erro 522) de uma API externa, implementando estratégias como chamadas sequenciais (**concatMap**) e atrasos (**delayElements**) para garantir a estabilidade da aplicação.
- **Prompt Engineering:** Refinamento contínuo do prompt enviado à OpenAI para garantir que a resposta da IA viesse no formato exato esperado pela aplicação (uma lista de nomes de filmes, sem texto adicional), melhorando a robustez do parsing.
- **UI com Thymeleaf:** Implementação de componentes de UI complexos, como um seletor de avaliação de 5 estrelas e a renderização condicional de elementos com **th:if**.

---

### 🛠️ Tecnologias Utilizadas

- **Backend:**
    - Java 17+
    - Spring Boot 3.x
    - Spring WebFlux / Project Reactor
    - Spring Data JPA / Hibernate
- **Frontend:**
    - Thymeleaf
    - HTML5 / CSS3
    - Bootstrap 5
- **Banco de Dados:**
    - H2 Database (In-Memory)
    - Flyway (para versionamento de schema)
- **Build & Dependências:**
    - Apache Maven
    - Lombok
    - MapStruct
-  **Integrações:**
    - OpenAI API
    - TMDb API

---

### ⚙️ Instalação e Uso

Para executar este projeto localmente, siga os passos abaixo:

**Pré-requisitos:**

 * Java (JDK) 17 ou superior instalado.
 * Chaves de API para a OpenAI e a TMDb.
 * Apache Maven instalado*

**1. Clone o repositório:**
```bash
git clone https://github.com/lucass-barreto/pipocaIA.git
cd PipocaIA
```

**2. Configure a Chave da API:**

Crie um arquivo chamado `.env` na raiz do projeto e adicione a sua chave da OpenAI:
```bash
API_KEY_OPENAI=sua-chave-secreta-aqui
API_KEY_TMDB=sua-chave-secreta-aqui
```

*Lembre-se de adicionar o arquivo `.env` ao seu `.gitignore`!*
*Lembre-se de configurar suas Keys corretamente no `application.properties`!*

**3. Build do Projeto:**

Execute o comando Maven para construir o projeto.
```bash
./mvnw clean install
```

**4.  Execute a Aplicação:**

Inicie a aplicação através da sua IDE, executando a classe principal, ou via terminal:
    
```bash
./mvnw spring-boot:run
```

**5.  Acesse a Aplicação:**

* **Dashboard (Catálogo):** [http://localhost:8080/pipocaIA/dashboard](http://localhost:8080/pipocaIA/dashboard)
* **Adicionar novo filme:** [http://localhost:8080/pipocaIA/adicionar](http://localhost:8080/pipocaIA/adicionar)
* **Página de recomendações:** [http://localhost:8080/pipocaIA/recomendacoes](http://localhost:8080/pipocaIA/recomendacoes)

* **Console do Banco H2:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (Use as credenciais do `application.properties` se necessário).

## 📫 Contato

**Lucas Barreto Oliveira**

* **GitHub:** [@lucass-barreto](https://github.com/lucass-barreto)
* **LinkedIn:** [@lucass-barreto](https://www.linkedin.com/in/lucass-barreto)
* **Email:** lucasbo.dev@gmail.com
