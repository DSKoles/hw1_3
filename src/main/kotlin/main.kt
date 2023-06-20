fun main() {
//    закомментил функцию для занятия 1.4
//    println(agoToText(60 * 60 * 2 + 1)) /*фунция для 1 задания*/
//    println(payment("Мир", 500_000, 140_000)) /*функция для 2 задания*/
}

//    Задача №1. Только что
//    Закомментил задачу для занятия 1.4
//fun agoToText(timeExitToNow: Int): String {
//    val minutes = timeExitToNow / 60 /* выбор окончания для минут*/
////    Вариант 2_Обобщенный
//    val minutesEnd: String = when {
//        minutes % 10 == 1 && minutes != 11 -> "у"
//        minutes % 10 in 2..4 && minutes !in 12..14 -> "ы"
//        else -> ""
//    }
////    Вариант 1
////    val minutesEnd = when (minutes) {
////        1, 21, 31, 41, 51 -> "у"
////        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "ы"
////        else -> ""
////    }
//    val hours = timeExitToNow / 60 / 60 /* выбор окончания для часов*/
////    Вариант 2_Обобщенный
//    val hoursEnd: String = when {
//        hours % 10 == 1 && hours != 11 -> ""
//        hours % 10 in 2..4 && hours !in 12..14 -> "а"
//        else -> "ов"
//    }
////    Вариант 1
////    val hoursEnd = when (hours) {
////        1, 21 -> ""
////        2, 3, 4, 22, 23, 24 -> "а"
////        else -> "ов"
////    }
////    сборка фразы в зависимости от продолжительности timeExitToNow
//    var timeToText = when (timeExitToNow) {
//        in 0..60 -> "только что"
//        in 61..60 * 60 -> minutes.toString() + " минут" + minutesEnd + " назад"
//        in 60 * 60 + 1..60 * 60 * 24 -> hours.toString() + " час" + hoursEnd + " назад"
//        in 60 * 60 * 24 + 1..60 * 60 * 24 * 2 -> "вчера"
//        in 60 * 60 * 24 * 2 + 1..60 * 60 * 24 * 3 -> "позавчера"
//        else -> "давно"
//    }
//    return ("Был(а) в сети " + timeToText)
//}

//    Задача №2. Разная комиссия
fun payment(payTipe: String = "VK Pay", monthPay: Int = 0, thisPay: Int): String = when {
    payTipe == "VK Pay" && thisPay > 15_000 -> "Вы превысили максимальную сумму" +
            " единовременного перевода VK Pay (15_000)"

    payTipe == "VK Pay" && monthPay + thisPay > 40_000 -> "Вы превысили месячный" +
            " лимит переводов VK Pay (40_000)"

    payTipe != "VK Pay" && thisPay > 150_000 -> "Вы превысили максимальную сумму" +
            " суточного перевода по карте (150_000)"

    payTipe != "VK Pay" && monthPay + thisPay > 600_000 -> "Вы превысили месячный" +
            " лимит переводов по карте (600_000)"

    else -> "Ваша комиссия составит: " + comission(payTipe, monthPay, thisPay) + " руб."
}

fun comission(payTipe: String, monthPay: Int, thisPay: Int): Double = when (payTipe) {
    "Mastercard", "Maestro" -> masterComission(monthPay, thisPay) /*вызов функции для МастерМаэстро*/
    "Visa", "Мир" -> visaComission(monthPay, thisPay) /*вызов функции для ВизаМир*/
    else -> 0.0 /*VK Pay всегда бесплатно*/
}

fun visaComission(monthPay: Int, thisPay: Int): Double = when (thisPay * 0.0075) {
    in 0.0..35.0 -> 35.0 /*когда комиссия с платежа не больше 35 рублей*/
    else -> thisPay * 0.0075 /*когда комиссия с платежа больше 35 рублей*/
}

//Вариант 1_некорректный после перехода за 75_000
//fun masterComission(monthPay: Int, thisPay: Int): Double = when (monthPay + thisPay) {
//    in 0..75_000 -> 0.0 /*когда суммарный платеж с учетом текущей суммы monthPay + thisPay меньше 75 тыс*/
//    else -> (monthPay + thisPay - 75_000) * 0.006 + 20 /*при превышении суммы 75 тыс, с суммы превышения*/
//}
//Вариант 2_Откорректировано
fun masterComission(monthPay: Int, thisPay: Int): Double = when {
    monthPay + thisPay <= 300 -> thisPay * 0.006 + 20 /*добавлен платеж до 300 для занятия 1.4.*/
    monthPay + thisPay <= 75_000 -> 0.0
    monthPay <= 75_000 -> (monthPay + thisPay - 75_000) * 0.006 + 20
    else -> thisPay * 0.006 + 20
}