package app.controller

import app.model.FoodMenu
import app.utils.IOUtils
import app.datasource.FoodDataSourceImpl
import java.text.NumberFormat
import java.util.*

class Menu {
    private val foodList = FoodDataSourceImpl().getFoodList()

    private val rupiahFormatted = NumberFormat.getCurrencyInstance(Locale("id", "ID"))

    // View Menu
    fun getMenu() {
        rupiahFormatted.maximumFractionDigits = 0

        foodList.forEachIndexed { index, data ->
            println("${index + 1}.${data.foodName} : ${rupiahFormatted.format(data.foodPrice)}")
        }
    }

    // Select Menu
    fun selectFood(): Pair<FoodMenu?, List<FoodMenu>> {
        print("Silahkan Pilih Menu 1/2/3/4/5 : ")
        try {
            val selectedIndex = IOUtils.getInputInteger()
            val selectedFood = if (selectedIndex != null && selectedIndex in 1..foodList.size) {
                foodList[selectedIndex - 1]
            } else {
                println("Invalid Input! Silahkan input sesuai pilihan.")
                return selectFood()
            }
            return Pair(selectedFood, foodList)
        } catch (e: NumberFormatException) {
            println("Invalid Input! Mohon inputkan dengan angka.")
            return selectFood()
        }
    }
}

