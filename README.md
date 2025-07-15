# Sistema de Agendamento de Consultório

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![SQL Server](https://img.shields.io/badge/Microsoft%20SQL%20Server-CC2927?style=for-the-badge&logo=microsoftsqlserver&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## 📖 Sobre o Projeto

Este é um projeto de portfólio que simula um sistema de desktop para gerenciamento de um consultório. Desenvolvido em Java com a biblioteca Swing para a interface gráfica, o sistema visa aplicar conceitos fundamentais de programação orientada a objetos, arquitetura em camadas e manipulação de banco de dados.

O projeto foi construído utilizando o NetBeans IDE e o Maven para gerenciamento de dependências.

---

## ✨ Funcionalidades Implementadas

- [x] **Segurança:** Tela de login para acesso ao sistema.
- [x] **Gestão de Pacientes (CRUD Completo):**
  - [x] Cadastro de novos pacientes com dados pessoais e endereço.
  - [x] Listagem de todos os pacientes cadastrados em uma tabela.
  - [x] Edição dos dados de um paciente existente.
  - [x] Exclusão de pacientes.
- [x] **Gestão de Agendamentos:**
  - [x] Tela para agendar uma nova consulta para um paciente existente.
- [ ] **Próximos Passos (Funcionalidades Futuras):**
  - [ ] Visualização de agendamentos em um calendário ou lista.
  - [ ] Cadastro de profissionais de saúde.
  - [ ] Geração de relatórios.
  - [ ] Melhoria de design (UI/UX).

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17
* **Interface Gráfica:** Java Swing
* **Banco de Dados:** Microsoft SQL Server
* **Build e Dependências:** Apache Maven
* **IDE:** Apache NetBeans

---

## 🚀 Como Executar o Projeto

Para executar este projeto localmente, siga os passos abaixo:

1.  **Pré-requisitos:**
    * Ter o [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior instalado.
    * Ter o [Microsoft SQL Server](https://www.microsoft.com/pt-br/sql-server/sql-server-downloads) instalado e rodando.
    * Ter o [Apache NetBeans IDE](https://netbeans.apache.org/download/index.html) instalado.

2.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/vitoriamaria-bp/Sistema-de-Agendamento-Consultorio.git](https://github.com/vitoriamaria-bp/Sistema-de-Agendamento-Consultorio.git)
    ```

3.  **Configure o Banco de Dados:**
    * Abra o SQL Server Management Studio (SSMS).
    * Execute o script SQL completo que está no seu projeto para criar o banco `db_consultorio` e as tabelas.

4.  **Configure o Projeto:**
    * Abra o projeto clonado no NetBeans.
    * Navegue até o arquivo `src/main/resources/config.properties`.
    * Altere a linha `db.password=sua_senha_aqui` para a sua senha real do SQL Server.

5.  **Compile e Execute:**
    * Clique com o botão direito no projeto e selecione **"Clean and Build"**.
    * Após a compilação, clique com o botão direito no projeto novamente e selecione **"Run"**. A tela de login deverá aparecer.
    * **Usuário:** `admin`
    * **Senha:** `admin123`
---

## 🖼️ Screenshots

**Tela de login**

<img width="584" height="513" alt="image" src="https://github.com/user-attachments/assets/090ba701-38af-4bdb-a627-72e38a237b17" />


**Tela Principal**

<img width="595" height="488" alt="image" src="https://github.com/user-attachments/assets/df5eb367-d93b-4d06-9f8a-f676f4326eda" />


**Tela de Cadastro de Pacientes**

<img width="891" height="709" alt="image" src="https://github.com/user-attachments/assets/e781f7ab-5377-4671-8ca3-15b8f48a2a00" />
