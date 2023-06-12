fun main() {
    println(agoToText(60 * 60 * 24 + 1)) /*фунция для 1 задания*/
    println("Ваша комиссия составит: " + comission("Maestro", 70_000, 10_000) + " руб.") /*функция для 2 задания*/
}

//    Задача №1. Только что
fun agoToText(timeExitToNow: Int): String {
    val minutes = timeExitToNow / 60 /* выбор окончания для минут*/
    val minutesEnd = when (minutes) {
        1, 21, 31, 41, 51 -> "у"
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54 -> "ы"
        else -> ""
    }
    val hours = timeExitToNow / 60 / 60 /* выбор окончания для часов*/
    val hoursEnd = when (hours) {
        1, 21 -> ""
        2, 3, 4, 22, 23, 24 -> "а"
        else -> "ов"
    }
//    сборка фразы в зависимости от продолжительности timeExitToNow
    var timeToText = when (timeExitToNow) {
        in 0..60 -> "только что"
        in 61..60 * 60 -> minutes.toString() + " минут" + minutesEnd + " назад"
        in 60 * 60 + 1..60 * 60 * 24 -> hours.toString() + " час" + hoursEnd + " назад"
        in 60 * 60 * 24 + 1..60 * 60 * 24 * 2 -> "вчера"
        in 60 * 60 * 24 * 2 + 1..60 * 60 * 24 * 3 -> "позавчера"
        else -> "давно"
    }
    return ("Был(а) в сети " + timeToText)
}

//    Задача №2. Разная комиссия
fun comission(payTipe: String, monthPay: Int, thisPay: Int): Double = when (payTipe) {
    "Mastercard", "Maestro" -> masterComission(monthPay, thisPay) /*вызов функции для МастерМаэстро*/
    "Visa", "Мир" -> visaComission(monthPay, thisPay) /*вызов функции для ВизаМир*/
    else -> 0.0 /*VK Pay всегда бесплатно*/
}

fun visaComission(monthPay: Int, thisPay: Int): Double = when (thisPay * 0.0075) {
    in 0.0..35.0 -> 35.0 /*когда комиссия с платежа не больше 35 рублей*/
    else -> thisPay * 0.0075 /*когда комиссия с платежа больше 35 рублей*/
}

fun masterComission(monthPay: Int, thisPay: Int): Double = when (monthPay + thisPay) {
    in 0..75_000 -> 0.0 /*когда суммарный платеж с учетом текущей суммы monthPay + thisPay меньше 75 тыс*/
    else -> (monthPay + thisPay - 75_000) * 0.006 + 20 /*при превышении суммы 75 тыс, с суммы превышения*/
}