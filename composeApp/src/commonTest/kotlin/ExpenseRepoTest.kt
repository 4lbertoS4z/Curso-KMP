import data.ExpenseManager
import data.ExpenseRepoImplement
import model.Expense
import model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpenseRepoTest {
    private val expenseManager = ExpenseManager
    private val repo = ExpenseRepoImplement(expenseManager)

    @Test
    fun expense_list_is_not_empty() {
        val expenseList = mutableListOf<Expense>()

        expenseList.addAll(repo.getAllExpenses())

        assertTrue(expenseList.isNotEmpty())
    }

    @Test
    fun add_new_expense() {
        val expenseList = repo.getAllExpenses()

        repo.addExpense(
            Expense(
                amount = 100.0,
                category = ExpenseCategory.CAR,
                description = "test"
            )
        )

        assertContains(expenseList, expenseList.find { it.id == 7L })
    }

    @Test
    fun edit_expense() {
        val expenseListBefore = repo.getAllExpenses()

        val newExpenseId = 7L
        repo.addExpense(
            Expense(
                amount = 100.0,
                category = ExpenseCategory.CAR,
                description = "test"
            )
        )

        assertNotNull(expenseListBefore.find { it.id == newExpenseId })

        val updatedExpense =
            Expense(id = newExpenseId, amount = 4.5, ExpenseCategory.PARTY, description = "test")

        repo.editExpense(updatedExpense)

        val expenseListAfter = repo.getAllExpenses()
        assertEquals(updatedExpense, expenseListAfter.find { it.id == newExpenseId })
    }

    @Test
    fun get_all_categories() {
        val categoryList = mutableListOf<ExpenseCategory>()

        categoryList.addAll(repo.getCategories())

        assertTrue(categoryList.isNotEmpty())
    }
}