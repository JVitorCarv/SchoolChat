# README - SchoolChat
Bem-vindo à solução de comunicação desenvolvida especialmente para atender às necessidades dos estudantes do 5º período da CESAR School. Nossa plataforma foi projetada para oferecer uma experiência de comunicação eficaz e conveniente, com foco nas demandas específicas desse público-alvo, e dessa forma tornar os nossos WhatsApps menos poluídos. Este é um projeto de chat em Java que consiste em um cliente e um servidor de chat. O projeto inclui funcionalidades para anúncios escolares e bate-papo em grupo.

## Estrutura do Projeto

O projeto está organizado da seguinte maneira:

- `AnnouncementClient` e `AnnouncementServer`: Classes relacionadas ao cliente e ao servidor de anúncios escolares.
- `ChatClient` e `ChatServer`: Classes relacionadas ao cliente e ao servidor de chat em grupo.
- `Client` e `Server`: Classes utilitárias que fornecem funcionalidades comuns para as classes de cliente e servidor.
- `ClientSelect`: Classe principal para selecionar o modo de operação do cliente.
- `ClientHandler`: Classe que lida com as conexões de cliente no servidor de chat.
- `UserInput`: Classe que lida com a entrada do usuário no cliente.

## Funcionalidades

### Anúncios Escolares

- O cliente de anúncios escolares (`AnnouncementClient`) permite aos usuários enviar e receber anúncios em um grupo multicast.
- O servidor de anúncios escolares (`AnnouncementServer`) recebe e distribui os anúncios para os clientes conectados.

### Chat em Grupo

- O cliente de chat em grupo (`ChatClient`) permite aos usuários se conectar ao servidor de chat e trocar mensagens em um grupo multicast.
- O servidor de chat em grupo (`ChatServer`) aceita conexões de clientes e encaminha as mensagens recebidas para todos os outros clientes conectados.

## Como Executar

- Para executar o cliente, você pode usar a classe `ClientSelect`, que permite escolher entre os modos de anúncio escolar e chat em grupo.
- Certifique-se de fornecer as informações necessárias, como o nome do autor ou identificação do usuário, quando solicitado.
- Você pode encerrar a execução do cliente digitando "exit" na entrada.
- Certifique-se de que o servidor correspondente esteja em execução antes de iniciar o cliente.

## Dependências

Este projeto utiliza as seguintes classes Java incorporadas:

- `MulticastSocket` e `InetAddress` para comunicação multicast.
- `Socket` para comunicação TCP.
- `ObjectInputStream` e `ObjectOutputStream` para serialização de objetos.
- `Thread` para gerenciar threads concorrentes.
- `Scanner` para entrada de usuário.
