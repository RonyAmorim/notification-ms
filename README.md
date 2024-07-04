# Microserviço de Notificações

Este projeto é um Microserviço de Notificações construído usando Spring Boot. Ele fornece funcionalidades para agendar, enviar e gerenciar notificações via uma API RESTful. O serviço também inclui uma tarefa periódica para processar notificações pendentes e com erro.

## Funcionalidades

- **Agendamento de Notificações:** Agende notificações para serem enviadas em um horário específico.
- **Gerenciamento de Notificações:** Recupere e cancele notificações através de endpoints da API.
- **Tarefa Periódica:** Uma tarefa do Spring Scheduler é executada a cada minuto para processar notificações pendentes e com erro.
- **Integração com Banco de Dados:** Usa MySQL para persistir notificações e dados relacionados.

## Tecnologias Utilizadas

- **Spring Boot:** Framework para construção do microserviço.
- **Spring Data JPA:** Para interações com o banco de dados.
- **Spring Scheduler:** Para execução de tarefas periódicas.
- **MySQL:** Banco de dados para armazenamento de dados de notificações.
- **Docker:** Para containerizar o banco de dados MySQL.

## Começando

### Pré-requisitos

- Java 21 ou superior
- Docker
- Maven

## Endpoints da API

- **Agendar uma Notificação:**
  - **POST** `/notifications`
  - Corpo da Requisição: `ScheduleNotificationDTO`

- **Recuperar uma Notificação:**
  - **GET** `/notifications/{id}`

- **Cancelar uma Notificação:**
  - **DELETE** `/notifications/{id}`
  - Nota: Isso não apaga a notificação, apenas atualiza seu status para 'canceled'.

## Tarefa Agendada

A aplicação inclui uma tarefa agendada que é executada a cada minuto para verificar notificações com status `pending` ou `error` que devem ser enviadas. Essas notificações são então processadas e seu status é atualizado para `success`.

