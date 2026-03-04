# Task Manager Pro 📱

> **Desafio de Desenvolvimento Mobile** — Gerenciador de tarefas Android com autenticação e sincronização em tempo real.

## 🚀 Sobre o Projeto

O **Task Manager Pro** é um aplicativo Android desenvolvido como parte de um desafio de programação. O objetivo foi construir um gerenciador de tarefas completo, focando não apenas em fazer o app funcionar, mas em aplicar boas práticas de arquitetura, organização de código e separação de responsabilidades.

## 🏗️ Arquitetura

O projeto foi estruturado com **MVVM (Model-View-ViewModel)**, dividindo claramente as camadas:

```
app/
└── src/
    ├── data/
    │   ├── AuthRepository.kt
    │   └── TaskRepository.kt
    ├── models/
    │   └── Task.kt
    ├── ui/
    │   ├── auth/
    │   │   ├── LoginScreen.kt
    │   │   └── SignupScreen.kt
    │   ├── main/
    │   │   └── MainScreen.kt
    │   └── theme/
    ├── viewmodel/
    │   ├── AuthViewModel.kt
    │   └── TaskViewModel.kt
    ├── MainActivity.kt
    └── TaskApp.kt
```

## 🛠️ Tecnologias Utilizadas

- **Kotlin** — Linguagem principal
- **Jetpack Compose** — UI declarativa e reativa
- **Navigation Compose** — Navegação entre telas
- **ViewModel + StateFlow** — Gerenciamento de estado
- **Firebase Authentication** — Login e cadastro de usuários
- **Firebase Realtime Database** — Persistência e sincronização em tempo real
- **Coroutines** — Operações assíncronas

## ✨ Funcionalidades

- [x] Cadastro e login com email/senha (Firebase Auth)
- [x] Criar, concluir e deletar tarefas
- [x] Definir prioridade: Alta, Média ou Baixa
- [x] Busca por título ou descrição
- [x] Filtrar tarefas por status (todas / pendentes / concluídas)
- [x] Estatísticas em tempo real (total, concluídas, pendentes)
- [x] Sincronização automática via Realtime Database
- [x] Suporte a tema claro e escuro

## 📲 Como Rodar

### Pré-requisitos

- Android Studio Hedgehog ou superior
- JDK 11+
- Conta no [Firebase Console](https://console.firebase.google.com/)

### Configuração

```bash
# Clone o repositório
git clone https://github.com/SEU_USUARIO/TaskManagerPro.git

# Abra no Android Studio
# File > Open > selecione a pasta do projeto
```

> ⚠️ O arquivo `google-services.json` **não está incluído** no repositório por conter informações sensíveis. Você precisará configurar seu próprio projeto Firebase:

1. Acesse o [Firebase Console](https://console.firebase.google.com/) e crie um novo projeto
2. Adicione um app Android com o package `com.taskmanagerpro`
3. Faça o download do seu `google-services.json` e coloque em `app/`
4. Ative **Authentication** (Email/Senha) e **Realtime Database**


## 🤝 Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou pull request.

## 📄 Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

---

Desenvolvido por **[Gustavo Pavani](https://github.com/Pavani9190)** como parte de um desafio de desenvolvimento mobile.
