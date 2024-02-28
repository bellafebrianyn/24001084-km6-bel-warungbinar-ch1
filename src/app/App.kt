package app

import app.controller.Menu
import app.controller.Order
import app.model.FoodMenu
import java.text.NumberFormat
import java.util.*

class App {
    val menu = Menu()

    private val rupiahFormatted = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

    fun run() {
        displayMainMenu()

        val selectedMenu = selectFood()
        processPayment(selectedMenu)

        val deliveryMethodManager = Order.DeliveryMethodManager()
        val deliveryMethod = deliveryMethodManager.getDeliveryMethod()
        processOrder(selectedMenu, deliveryMethod)
    }

    private fun displayMainMenu() {
        rupiahFormatted.maximumFractionDigits = 0

        println("""
            ==========================================
            |     Selamat Datang di Warung Binar!    |
            ==========================================
            List Menu :
        """.trimIndent())
        menu.getMenu()
        println("==========================================")
    }

    private fun selectFood(): FoodMenu? {
        val (selectedMenu, foodList) = menu.selectFood()
        selectedMenu?.let {
            val index = foodList.indexOf(it) + 1
            println("----------------------------")
            println("Kamu memilih menu $index")
            println("Nama Menu : ${it.foodName}")
            println("Harga : ${rupiahFormatted.format(it.foodPrice)}")
            println("----------------------------")
            return it
        }
        return null
    }

    private fun processPayment(selectedFood: FoodMenu?) {
        val paymentProcessor = Order.PaymentProcessor()
        paymentProcessor.getOrderPayment(selectedFood ?: return)
    }

    private fun processOrder(selectedMenu: FoodMenu?, deliveryMethod: Int) {
        val deliveryProcess: Order.DeliveryProcess = when (deliveryMethod) {
            1 -> Order.TakeAway()
            2 -> Order.Delivery()
            else -> throw IllegalArgumentException("Invalid delivery method")
        }

        val orderProcessor = Order.OrderProcessor(deliveryProcess)
        orderProcessor.getProcessOrder(selectedMenu, deliveryMethod)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            App().run()
        }
    }
}