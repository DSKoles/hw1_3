import org.junit.Test

import org.junit.Assert.*

class MainKtTest {

    @Test
    //ВК в пределах лимитов
    fun paymentVKinLimit() {
        val payTipe = "VK Pay"
        val monthPay = 0
        val thisPay = 100

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 0.0 руб.", result)
    }

    @Test
    //ВК вс превышением суточного лимитоа
    fun paymentVKoutLimit() {
        val payTipe = "VK Pay"
        val monthPay = 0
        val thisPay = 16_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Вы превысили максимальную сумму единовременного перевода VK Pay (15_000)", result)
    }

    @Test
    //ВК вс превышением месячного лимитоа
    fun paymentVKoutMonthLimit() {
        val payTipe = "VK Pay"
        val monthPay = 35_000
        val thisPay = 10_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Вы превысили месячный лимит переводов VK Pay (40_000)", result)
    }

    @Test
    //Карточный счет на примере Mastercard с превышением месячного лимита
    fun paymentCardOutMonthLimit() {
        val payTipe = "Mastercard"
        val monthPay = 590_000
        val thisPay = 20_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Вы превысили месячный лимит переводов по карте (600_000)", result)
    }

    @Test
    //Карточный счет на примере Mastercard с превышением суточного лимита
    fun paymentCardOutLimit() {
        val payTipe = "Mastercard"
        val monthPay = 0
        val thisPay = 160_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Вы превысили максимальную сумму суточного перевода по карте (150_000)", result)
    }

    @Test
    //Карточный счет Mastercard в пределах лимита
    fun paymentMasterInLimit() {
        val payTipe = "Mastercard"
        val monthPay = 76_000
        val thisPay = 120_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 740.0 руб.", result)
    }

    @Test
    //Карточный счет Mastercard в пределах лимита
    fun paymentMaestroInLimit() {
        val payTipe = "Maestro"
        val monthPay = 76_000
        val thisPay = 120_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 740.0 руб.", result)
    }

    @Test
    //Карточный счет Mastercard в пределах лимита перевод 300 рублей
    fun paymentMaster300() {
        val payTipe = "Mastercard"
        val monthPay = 0
        val thisPay = 300

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 21.8 руб.", result)
    }

    @Test
    //Карточный счет Mastercard до месячной суммы 75000 рублей
    fun paymentMasterBeforeMonth75000() {
        val payTipe = "Mastercard"
        val monthPay = 0
        val thisPay = 74_999

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 0.0 руб.", result)
    }

    @Test
    //Карточный счет Mastercard после превышения суммарного месячного платежа 75000 рублей
    fun paymentMaster75001() {
        val payTipe = "Mastercard"
        val monthPay = 74_500
        val thisPay = 1_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 23.0 руб.", result)
    }

    @Test
    //Карточный счет Visa с комиссией до 35 рублей
    fun paymentVisaComissionBefore35() {
        val payTipe = "Visa"
        val monthPay = 0
        val thisPay = 1_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 35.0 руб.", result)
    }

    @Test
    //Карточный счет Visa с комиссией больше 35 рублей
    fun paymentVisaComissionAfter35() {
        val payTipe = "Visa"
        val monthPay = 0
        val thisPay = 5_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 37.5 руб.", result)
    }

    @Test
    //Карточный счет Мир с комиссией до 35 рублей
    fun paymentMirComissionBefore35() {
        val payTipe = "Мир"
        val monthPay = 0
        val thisPay = 1_000

        val result = payment(payTipe, monthPay, thisPay)

        assertEquals("Ваша комиссия составит: 350.0 руб.", result) /*ломаем сборку*/
    }

}