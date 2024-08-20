import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.ExpenseManager
import data.ExpenseRepoImplement
import data.TittleTopBarTypes
import kotlinproject.composeapp.generated.resources.Res
import moe.tlaster.precompose.PreComposeApp
import org.jetbrains.compose.ui.tooling.preview.Preview
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import navigation.Navigation
import presentation.ExpensesViewModel
import ui.ExpensesScreen

@Composable
@Preview
fun App() {


    PreComposeApp {
        val colors = getColorsTheme()


        AppTheme {
            val navigator = rememberNavigator()
            val tittleTopBar = getTittleTopAppBar(navigator)
            val isEditOrAddExpenses = tittleTopBar != TittleTopBarTypes.DASHBOARD.value
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        elevation = 0.dp,
                        title = {
                            Text(
                                text = tittleTopBar,
                                fontSize = 25.sp,
                                color = colors.textColor
                            )
                        },
                        navigationIcon = {
                            if (isEditOrAddExpenses) {
                                IconButton(onClick = {
                                    navigator.popBackStack()
                                }
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(start = 16.dp),
                                        imageVector = Icons.Default.ArrowBackIosNew,
                                        tint = colors.textColor,
                                        contentDescription = "arrow back"
                                    )
                                }
                            } else {
                                Icon(
                                    modifier = Modifier.padding(start = 16.dp),
                                    imageVector = Icons.Default.Apps,
                                    tint = colors.textColor,
                                    contentDescription = "Dashboard icon"
                                )
                            }

                        }, backgroundColor = colors.backgroundColor
                    )
                }, floatingActionButton = {
                    if (!isEditOrAddExpenses) {
                        FloatingActionButton(
                            modifier = Modifier.padding(8.dp),
                            onClick = { navigator.navigate("/addExpenses") },
                            shape = RoundedCornerShape(50),
                            backgroundColor = colors.addIconColor, contentColor = Color.White
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                tint = Color.White,
                                contentDescription = "Floating icon"
                            )
                        }
                    }

                }
            ) {
                Navigation(navigator)
            }
        }
    }
}

@Composable
fun getTittleTopAppBar(navigator: Navigator): String {
    var tittleTopBar = TittleTopBarTypes.DASHBOARD

    var isOnAddExpenses =
        navigator.currentEntry.collectAsState(null).value?.route?.route.equals("/addExpenses/{id}?")
    if (isOnAddExpenses) {
        tittleTopBar = TittleTopBarTypes.ADD
    }
    var isOnEditExpenses = navigator.currentEntry.collectAsState(null).value?.path<Long>("id")
    isOnEditExpenses?.let {
        tittleTopBar = TittleTopBarTypes.EDIT
    }
    return tittleTopBar.value
}
