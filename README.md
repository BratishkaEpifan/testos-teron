# Микросервис testos-teron
- В данном проекте выполнено тестовое задание для работы с классом Task

## Как запустить:

- Скачайте проект
- Запустите следующую команду:
  -  docker-compose -f compose.yaml up --build
## Примеры запросов к API:


- Post: http://localhost:8080/open-api/tasks
  - Тело удачного запроса: {
    "title": "Тестовый заголовок задачи",
    "description": "Тестовое описание задачи",
    "taskStatus": "COMPLETED"
    } 
  - Тело ошибочного запроса: {
    "title": "Тестовый заголовок задачи",
    "description": "Тестовое описание задачи",
    "taskStatus": "COMPL"
    }
- Get: http://localhost:8080/open-api/tasks/{id}
  - Тело удачного запроса: валидный id
- Patch: http://localhost:8080/open-api/tasks/{id}
  - Тело удачного запроса: валидный id, body: {
    "title": "Заголовок",
    "description": "Описание",
    "taskStatus": "PENDING"
    }
- Delete: http://localhost:8080/open-api/tasks/{id}
  - Тело удачного запроса: валидный id
