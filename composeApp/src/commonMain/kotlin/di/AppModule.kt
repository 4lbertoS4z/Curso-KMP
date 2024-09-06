package di

import com.expenseApp.db.AppDatabase
import data.ExpenseRepoImplement
import domain.ExpenseRepository
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun appModule(appDatabase: AppDatabase) = module {

    single<ExpenseRepository> { ExpenseRepoImplement(appDatabase) }
    factory { ExpensesViewModel(get()) }
}
