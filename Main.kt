fun main() {
    var continueProgram = true

    while (continueProgram) {
        println("ВЫБЕРИТЕ ЗАДАЧУ (1-6) ИЛИ 0 ДЛЯ ВЫХОДА:")
        println("1. Подсчет подряд идущих символов")
        println("2. Подсчет различных символов")
        println("3. Перевод из 10-ичной в двоичную систему")
        println("4. Калькулятор")
        println("5. Поиск целочисленного показателя степени")
        println("6. Создание нечетного числа из двух цифр")
        println("0. Выход из программы")
        print("Ваш выбор: ")

        when (readLine()?.toIntOrNull() ?: -1) {
            1 -> task1()
            2 -> task2()
            3 -> task3()
            4 -> task4()
            5 -> task5()
            6 -> task6()
            0 -> {
                println("Программа завершена")
                continueProgram = false
            }
            else -> println("Неверный выбор.Введите число от 0 до 6.")
        }

        if (continueProgram) {
            print("Нажмите Enter для продолжения...")
            readLine()
        }
    }
}

fun task1() {
    println("\nЗАДАЧА 1")
    println("Введите строку для подсчета подряд идущих одинаковых символов:")
    println("Пример:AAADSSSRRTTHAAAA -> A3DS3R2T2HA4")
    val inputString = readLine() ?: ""
    val result = cstring(inputString)
    println(result)
}
fun cstring(input: String): String {
    if (input.isEmpty()) {
        return ""
    }
    val result = StringBuilder()
    var count = 1
    var currchar = input[0]
    for (i in 1 until input.length) {
        if (input[i] == currchar) {
            count++
        } else {
            result.append(currchar)
            if (count > 1) {
                result.append(count)
            }
            currchar = input[i]
            count = 1
        }
    }
    result.append(currchar)
    if (count > 1) {
        result.append(count)
    }
    return result.toString()
}
fun task2() {
    println("\nЗАДАЧА 2")
    println("Введите строку для подсчета  символов:")
    println("Пример:AASADDSS -> A-3, D-2, S-3")
    val text = readln()

    text.toSortedSet().forEach { char ->
        println("$char - ${text.count { it == char }}")
    }
}
fun task3() {
    println("\nЗАДАЧA 3")
    println("Введите натуральное число для перевода в двоичную систему:")
    val input = readln()
    val number = input.toIntOrNull()

    if (number != null && number > 0) {
        println("$number в двоичной системе: ${number.toString(2)}")
    } else {
        println("Ошибка.Введите натуральное число.")
    }
}
fun task4() {
    println("\nЗАДАЧА 4")
    println("Введите выражение в формате:ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ")
    println("Доступные операции:+, -, *, /")
    println("Пример:10.5 2.5 *")
    val input = readLine()?.trim()
    if (input.isNullOrEmpty()) {
        println("Ошибка.ввод не может быть пустым")
        return
    }
    val parts = input.split(" ")
    if (parts.size != 3) {
        println("Ошибка.неверный формат. Используйте: ЧИСЛО1 ЧИСЛО2 ОПЕРАЦИЯ")
        return
    }

    // Определяем типы чисел
    val num1Int = parts[0].toIntOrNull()
    val num2Int = parts[1].toIntOrNull()
    val num1Double = parts[0].toDoubleOrNull()
    val num2Double = parts[1].toDoubleOrNull()
    val operation = parts[2]

    if (num1Double == null || num2Double == null) {
        println("Ошибка.введите корректные числа")
        return
    }

    // Если оба числа целые и операция не деление - используем Int
    if (num1Int != null && num2Int != null && operation != "/") {
        val result = when (operation) {
            "+" -> num1Int + num2Int
            "-" -> num1Int - num2Int
            "*" -> num1Int * num2Int
            else -> {
                println("Ошибка.неизвестная операция '$operation'")
                return
            }
        }
        println("Результат: $result")
    } else {
        // Для вещественных чисел или деления используем Double
        val result = when (operation) {
            "+" -> num1Double + num2Double
            "-" -> num1Double - num2Double
            "*" -> num1Double * num2Double
            "/" -> {
                if (num2Double == 0.0) {
                    println("Ошибка.деление на ноль")
                    return
                }
                num1Double / num2Double
            }
            else -> {
                println("Ошибка.неизвестная операция '$operation'")
                return
            }
        }
        println("Результат: $result")
    }
}
fun task5() {
    println("\nЗАДАЧА 5")
    println("Введите целое число n и основание степени x через пробел:")
    println("Пример: 8 2")
    val input = readLine()?.trim()
    if (input.isNullOrEmpty()) {
        println("ввод не может быть пустым")
        return
    }
    val parts = input.split(" ")
    if (parts.size != 2) {
        println("введите два числа через пробел")
        return
    }
    val n = parts[0].toIntOrNull()
    val x = parts[1].toIntOrNull()
    if (n == null || x == null || x <= 0 || x == 1) {
        println("введите корректные целые числа. Основание не может быть 0, 1 или отрицательным")
        return
    }
    if (n <= 0) {
        println("число n должно быть положительным")
        return
    }
    var y = 0
    var power = 1
    while (power <= n) {
        if (power == n) {
            println("Целочисленный показатель степени: $y")
            return
        }
        y++
        if (power > Int.MAX_VALUE / x) {
            break
        }
        power *= x
    }

    println("Целочисленный показатель не существует")
}

fun task6() {
    println("\nЗАДАЧА 6")
    println("Введите первую цифру (0-9):")
    val digit1 = readLine()?.toIntOrNull()

    println("Введите вторую цифру (0-9):")
    val digit2 = readLine()?.toIntOrNull()

    if (digit1 == null || digit2 == null ||
        digit1 !in 0..9 || digit2 !in 0..9 ||
        digit1 == digit2) {
        println("Ошибка:введите две различные цифры от 0 до 9")
        return
    }
    val num1 = digit1 * 10 + digit2
    val num2 = digit2 * 10 + digit1

    when {
        num1 % 2 != 0 -> println("Нечетное число: $num1")
        num2 % 2 != 0 -> println("Нечетное число: $num2")
        else -> println("Создать нечетное число никак")
    }
}
