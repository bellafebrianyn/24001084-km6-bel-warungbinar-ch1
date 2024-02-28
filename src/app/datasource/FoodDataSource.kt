package app.datasource

import app.model.FoodMenu

interface FoodDataSource  {
    fun getFoodList() : List<FoodMenu>
}

class FoodDataSourceImpl() : FoodDataSource {
    override fun getFoodList(): List<FoodMenu> {
        return listOf(
            FoodMenu(
                foodName = "Ayam Bakar",
                foodPrice = 50000
            ),
            FoodMenu(
                foodName = "Ayam Goreng",
                foodPrice = 40000
            ),
            FoodMenu(
                foodName = "Ayam Geprek",
                foodPrice = 40000
            ),
            FoodMenu(
                foodName = "Kulit Ayam Crispy",
                foodPrice = 15000
            ),
            FoodMenu(
                foodName = "Sate Usus Ayam",
                foodPrice = 5000
            )
        )
    }
}