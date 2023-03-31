Money Transfer Service "Сервис перевода денег"
=
Описание проекта
==
Разработать приложение - REST-сервис. Сервис должен предоставить интерфейс для перевода денег с одной карты на другую по заранее описанной спецификации. Заранее подготовленное веб-приложение (FRONT) должно подключаться к разработанному сервису без доработок и использовать его функционал для перевода денег.

## Требования к приложению
* Сервис должен предоставлять REST интерфейс для интеграции с FRONT
* Сервис должны реализовывать все методы перевода с одной банковской карты на другую описанные в протоколе https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml
* Все изменения должны записываться в файл (лог переводов в произвольном формате с указанием даты, времени, карта с которой было списание, карта зачисления, сумма, комиссия, результат операции если был)
## Требования в реализации
* Приложение разработано с использованием Spring Boot
* Использован сборщик пакетов gradle/maven
* Для запуска используется docker, docker-compose
* Код размещен на github
* Код покрыт unit тестами с использованием mockito
* Добавлены интеграционные тесты с использованием testcontainers

FRONT доступен по адресу https://github.com/serp-ya/card-transfer, можно выкачать репозиторий и запустить nodejs приложение локально (в описании репозитория фронта добавлена информация как запустить) или использовать уже развернутое демо приложение по адресу https://serp-ya.github.io/card-transfer/ (тогда ваш api должен быть запущен по адресу http://localhost:5500/).

Весь api фронта был описан в соответствии yaml (https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml) файла по спецификации openapi (подробнее тут https://swagger.io/specification/ и тут https://starkovden.github.io/introduction-openapi-and-swagger.html)
## Описание выполненного проекта
* Реализовано RESTful интерфейс для интеграции с готовым FRONT (Rest controller, слои, валидация)
* Для обработки исключений используется Controller advance c созданием собственного Response. (Для тестового вызова исключений необходимо раскомментировать строчки кода в методах класса MoneyTransferServiceRepository)
* Для логирования применяется интерфейс SLF4j. Конфигурация  логгера в application.properties
* Код покрыт Unit - тестами, в том числе с использованием Mockito и Test Container
## Создание образа и запуск контейнера Docker
* ### Через Dockerfile:
  - В терминале cобираем jar архив с нашим spring boot приложением: mvn package
  - Создаем образ из написанного Dockerfile: docker build -t myapp:latest .
  - Запускаем контейнер из образа: docker run -itd --name money_transfer_service -p 5550:5500 myapp:latest
* ### Через docker-compose.yaml:
  -  В терминале cобираем jar архив с нашим spring boot приложением: mvn package
  - в терминале и выполнить команду: docker-compose up
  ## Как проверить?
  - Протестировать приложение в браузере: https://serp-ya.github.io/card-transfer/
  - Протестировать приложение с помощью Postman
1. POST request --> http://localhost:5500/transfer
````
{
"cardFromNumber": "1111222233334444",
"cardFromValidTill": "06/27",
"cardFromCVV": "112",
"cardToNumber": "9999888877776666",
"amount": {
"value": 10000,
"currency": "RUB"
}
````
````
response --> 200 OK
````

2. POST request --> http://localhost:5500/confirmOperation
````
{
"operationId":  "1",
"code": "1234"
}
````
````
response --> 200 OK
````
