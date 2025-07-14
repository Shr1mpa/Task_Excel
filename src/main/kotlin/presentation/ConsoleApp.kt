package org.example.presentation

import org.example.presentation.TableController

class ConsoleApp {
    companion object {
        suspend fun run() {
            val controller = TableController()

            while (true) {
                println("Меню:")
                println("1. Створити нову таблицю")
                println("2. Завантажити таблицю з сервера")
                println("3. Обчислити таблицю")
                println("4. Зберегти таблицю на сервер")
                println("5. Вивести таблицю")
                println("0. Вийти")

                when (readlnOrNull()) {
                    "1" -> {
                        controller.createNewTable()
                        println("Таблицю створено")
                    }

                    "2" -> {
                        val success = controller.loadFromServer()
                        println(if (success) "Таблицю завантажено" else "Таблиця не знайдена")
                    }

                    "3" -> {
                        if (controller.hasTable()) {
                            controller.evaluateTable()
                            println("Таблицю обчислено")
                        } else {
                            println("Таблиця не створена")
                        }
                    }

                    "4" -> {
                        val success = controller.saveToServer()
                        if (!success) println("Збереження не виконано")
                    }

                    "5" -> {
                        if (controller.hasTable()) {
                            controller.printTable()
                        } else {
                            println("Таблиця не створена")
                        }
                    }

                    "0" -> return
                    else -> println("Невірний вибір")
                }
            }
        }
    }
}