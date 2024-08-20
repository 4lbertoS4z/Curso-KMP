package data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.ElectricCar
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FoodBank
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.PartyMode
import androidx.compose.material.icons.filled.ViewCozy
import model.Expense
import model.ExpenseCategory

object ExpenseManager {

    private var currentId = 1L


    val fakeExpenseList = mutableListOf(
        Expense(
            id = currentId++,
            amount = 70.0,
            category = ExpenseCategory.GROCERIES,
            description = "weekly buy"
        ),
        Expense(
            id = currentId++,
            amount = 440.0,
            category = ExpenseCategory.COFFEE,
            description = "qweqwe"
        ),
        Expense(
            id = currentId++,
            amount = 99.0,
            category = ExpenseCategory.CAR,
            description = "qweasd"
        ),
        Expense(
            id = currentId++,
            amount = 9999.0,
            category = ExpenseCategory.SNACKS,
            description = "zczcx"
        ),
        Expense(
            id = currentId++,
            amount = 999998.0,
            category = ExpenseCategory.HOUSE,
            description = "fghfhg"
        ),
        Expense(
            id = currentId++,
            amount = 44444.0,
            category = ExpenseCategory.OTHER,
            description = "vbn"
        )

    )

    fun addNewExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            fakeExpenseList[index] = fakeExpenseList[index].copy(
                amount = expense.amount,
                category = expense.category,
                description = expense.description
            )
        }
    }

    fun deleteExpense(expense: Expense){
        val index = fakeExpenseList.indexOfFirst { it.id == expense.id }
        fakeExpenseList.removeAt(index)
    }
    fun getCategories(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.SNACKS,
            ExpenseCategory.COFFEE,
            ExpenseCategory.CAR,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER
        )
    }

}

