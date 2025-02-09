package td.numres.pizzaapp

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.parameter.parametersOf
import td.numres.pizzaapp.di.appModule
import td.numres.pizzaapp.data.OrderStorage

class PizzaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val koin = startKoin {
            androidContext(this@PizzaApp)
            modules(appModule)
        }.koin

        // Création de OrderStorage avec le contexte Android
        val orderStorage: OrderStorage = koin.get { parametersOf(this) }
    }
}