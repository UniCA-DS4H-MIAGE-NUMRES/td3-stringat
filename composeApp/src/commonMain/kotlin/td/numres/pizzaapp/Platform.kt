package td.numres.pizzaapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
