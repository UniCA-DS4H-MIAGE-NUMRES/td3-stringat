package td.numres.pizzaapp.di

import org.koin.dsl.module
import td.numres.pizzaapp.data.OrderStorage

val appModule = module {
    single { (context: Any?) -> OrderStorage(context) }
}