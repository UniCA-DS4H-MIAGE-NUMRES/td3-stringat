package td.numres.pizzaapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.module
import td.numres.pizzaapp.data.OrderStorage
import td.numres.pizzaapp.viewmodel.CartViewModel
import td.numres.pizzaapp.viewmodel.OrderViewModel
import td.numres.pizzaapp.viewmodel.PizzaViewModel

val appModule = module {
    single { OrderStorage(null) }
    single { CartViewModel() }
    single { OrderViewModel() }
    single { PizzaViewModel() }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}