import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class ExampleTest {
    @Test
    fun expense_model_list_test() {
        //GIVEN
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(1, 100.0, ExpenseCategory.CAR, "test")
        //WHEN
        expenseList.add(expense)
        //THEN
        assertContains(expenseList, expense)
    }

    @Test
    fun expense_model_param_test_success() {
        //GIVEN
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(1, 100.0, ExpenseCategory.CAR, "test")
        val expense2 = Expense(2, 199.0, ExpenseCategory.CAR, "Limpieza")
        //WHEN
        expenseList.add(expense)
        expenseList.add(expense2)
        //THEN
        assertEquals(expenseList[0].category,expenseList[1].category)
    }
    @Test
    fun expense_model_param_test_fail() {
        //GIVEN
        val expenseList = mutableListOf<Expense>()
        val expense = Expense(1, 100.0, ExpenseCategory.CAR, "test")
        val expense2 = Expense(2, 199.0, ExpenseCategory.HOUSE, "Limpieza")
        //WHEN
        expenseList.add(expense)
        expenseList.add(expense2)
        //THEN
        assertNotEquals(expenseList[0].category,expenseList[1].category)
    }
}