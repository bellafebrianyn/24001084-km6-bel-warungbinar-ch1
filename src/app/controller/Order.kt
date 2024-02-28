package app.controller

import app.model.FoodMenu
import java.text.NumberFormat
import java.util.*

class Order {

    // Payment Processor
    class PaymentProcessor {
        private val rupiahFormatted = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

        fun getOrderPayment(selectedFood: FoodMenu) {
            rupiahFormatted.maximumFractionDigits = 0
            val totalPrice = selectedFood?.foodPrice ?: 0

            print("Masukkan Pembayaran : ")

            try {
                val payment = readln().toInt()
                if (payment == totalPrice) {
                    println("Terima kasih, Anda berhasil memesan makanan")
                } else if (payment != null && payment > totalPrice) {
                    val change = payment - totalPrice
                    println(" - Pembayaran Berhasil! -")
                    println("Kembalian Anda sebesar ${rupiahFormatted.format(change)}")
                } else {
                    println("Maaf, Pembayaran Anda Gagal!")
                    getOrderPayment(selectedFood)
                }
            } catch (e: NumberFormatException) {
                println("Invalid Input! Mohon inputkan dengan angka.")
                getOrderPayment(selectedFood)
            }
        }
    }

    // Delivery Method
    interface DeliveryMethodHandler {
        fun getDeliveryMethod(): Int
    }

    class DeliveryMethodManager : DeliveryMethodHandler {
        override fun getDeliveryMethod(): Int {
            println(
                """
                ----------------------------
                Metode Pengiriman
                1. Take Away
                2. Delivery
                Pilih :
            """.trimIndent()
            )
            try {
                val deliveryMethod = readln().toInt()
                if (deliveryMethod !in 1..2) {
                    println("Metode Pengiriman Tidak Sesuai")
                    return getDeliveryMethod()
                }
                return deliveryMethod
            } catch (e: NumberFormatException) {
                println("Invalid Input! Mohon inputkan dengan angka.")
                return getDeliveryMethod()
            }
        }
    }

    // Order Processor
        interface DeliveryProcess {
            fun startDeliveryProcess()
        }

        class TakeAway : DeliveryProcess {
            override fun startDeliveryProcess() {
                print("Makananmu sudah siap! Silahkan ambil di resto ya! (5 detik)")
                for (i in 1..5) {
                    Thread.sleep(1000)
                    print(".")
                }
                println()
                print("Pesananmu selesai! (3 detik)")
                for (i in 1..3) {
                    Thread.sleep(1000)
                    print(".")
                }
                println()
            }
        }

        class Delivery : DeliveryProcess {
            override fun startDeliveryProcess() {
                print("Makananmu sudah siap! Driver segera menuju tempatmu! (5 detik)")
                for (i in 1..5) {
                    Thread.sleep(1000)
                    print(".")
                }
                println()
                print("Driver sampai! Pesanan selesai! (3 detik)")
                for (i in 1..3) {
                    Thread.sleep(1000)
                    print(".")
                }
                println()
            }
        }

        class OrderProcessor(private val deliveryProcess: DeliveryProcess) {
            fun getProcessOrder(selectedFood: FoodMenu?, deliveryMethod: Int) {
                println("----------------------------")
                print("Makananmu sedang dimasak (5 detik)")
                for (i in 1..5) {
                    Thread.sleep(1000)
                    print(".")
                }
                println()

                deliveryProcess.startDeliveryProcess()
            }
        }
}
