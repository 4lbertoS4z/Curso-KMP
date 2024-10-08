package navigation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import getColorsTheme
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.koin.koinViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import org.koin.core.parameter.parametersOf
import presentation.ExpensesViewModel
import ui.ExpensesDetailScreen
import ui.ExpensesScreen

@Composable
fun Navigation(navigator: Navigator) {
    val colors = getColorsTheme()
    val viewModel = koinViewModel(ExpensesViewModel::class){ parametersOf() }

    NavHost(
        modifier = Modifier.background(colors.backgroundColor),
        navigator = navigator,
        initialRoute = "/home"
    ) {
        scene(route = "/home") {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            ExpensesScreen(uiState) { expense -> navigator.navigate("/addExpenses/${expense.id}") }
        }
        scene(route = "/addExpenses/{id}?") {
            val idFromPath = it.path<Long>("id")
            val expenseToEditOrAdd = idFromPath?.let { id -> viewModel.getExpenseWithID(id) }

            ExpensesDetailScreen(expenseToEdit = expenseToEditOrAdd, categoryList = viewModel.getCategories()) { expense ->
                if (expenseToEditOrAdd == null) {
                    viewModel.addExpense(expense)
                }else{
                    viewModel.editExpense(expense)
                }
                navigator.popBackStack()
            }

        }
    }

}