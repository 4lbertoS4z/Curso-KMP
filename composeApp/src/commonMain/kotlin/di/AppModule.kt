package di

import data.ExpenseManager
import data.ExpenseRepoImplement
import domain.ExpenseRepository
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import presentation.ExpensesViewModel

fun appModule() = module {
    single { ExpenseManager }.withOptions { createdAtStart() }
    single<ExpenseRepository> { ExpenseRepoImplement(get()) }
    factory { ExpensesViewModel(get()) }
}
