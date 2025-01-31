package numres.stringat.pizzaapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform