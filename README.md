# Дипломный проект по профессии «Тестировщик»

Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

* Обычная оплата по дебетовой карте.
* Уникальная технология: выдача кредита по данным банковской карты.
![image](https://github.com/FrustratTr/QA_diplom/assets/143522147/0ad82c11-331f-4462-8537-6f7e3c2d1162)

### Документация
1. [План автоматизации](https://github.com/FrustratTr/QA_diplom/blob/main/Documents/plan.md)
2. [Отчёт о проведенном тестировании](https://github.com/FrustratTr/QA_diplom/blob/main/Documents/Report.md)
3. [Отчёт по итогам автоматизации](https://github.com/FrustratTr/QA_diplom/blob/main/Documents/Summary.md)

## Запуск автотестов и начало работы:
* Склонировать [репозиторий](https://github.com/FrustratTr/QA_diplom) себе на ПК командой `git clone`
* Открыть проект в IDEA
* Запустить MySql, PostgreSQL и Node.js, используя в терминале IDEA команду `docker-compose up`
* Запустить SUT, для этого открыть новую вкладку терминала и использовать:

Для MySQL команду `java -jar aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`

Для PostgreSQL команду `java -jar aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`
* Запустить автотесты, открыв новую вкладку терминала и использовать команду:

Для MySQL команду .\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" allureReport

Для PostgreSQL команду .\gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432 /app" allureReport
* Использовать команду .\gradlew allureserve для генерации отчета в браузере
* После тестирования закрыть вкладки терминала для остановки .jar файла и ввести команду `docker-compose down` для приостановки работы контейнеров.
