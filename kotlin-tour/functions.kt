import kotlin.math.PI


fun main() {
    println("========== function ==========")
    println(greet("Alice"))
    println(sum(1, 2))

    printMessageWithPrefix("Hello, world!")
    printMessageWithPrefix("Hello, world!", "[DEBUG]")
    printAllWithPrefix("a", "b", "c", prefix = "prefix")
    log(sum(1, 2))
    log(multiply(2, 3))

    log(circleArea(5.0))
    log(circleArea(9.0f))

    log(upperCaseString("hello"))
    log(lowerCaseString("HELLO"))

    testFilter()

    // test toSeconds
    val timesInMinutes = listOf(2, 10, 15, 1)
    val min2seconds = toSeconds("minute")
    val totalTimeInSeconds = timesInMinutes.map(min2seconds).sum()
    println(totalTimeInSeconds) // total time is 1680 seconds

    testLambdaInvokeSeparately()
    testLambdaAsParameter()
    do_lambda_practice()

    val result = processUser("Alice", 30)
    println("processUser result: $result")
    printSystemInfo()
}

fun greet(name: String): String {
    return "Hello, $name!"
}

fun sum(a: Int, b: Int): Int {
    return a + b
}


// 命名参数，调用的时候传入参数名称，则顺序可以任意 
fun printMessageWithPrefix(message: String, prefix: String = "[INFO]") {
    println("$prefix $message")
}

// 可变参数，使用 vararg 关键字
fun printAllWithPrefix(vararg messages: String, prefix: String) {
    for (message in messages) {
        println("$prefix $message")
    }
}

// 单个表达式的函数，可以省略大括号 the curly braces
// fun functionName(parameters): ReturnType = expression
fun log(message: String) = println(message)
fun multiply(a: Int, b: Int) = a * b

// 函数重载，函数名称相同，参数类型不同
fun log(message: Number) = println(message)


fun circleArea(radius: Double): Double {
    return PI * radius * radius
}

fun circleArea(radius: Float): Float {
    return (PI * radius * radius).toFloat()
}

// lambda 表达式
var upperCaseString = { str: String -> str.uppercase() }

// 匿名函数
var lowerCaseString = fun(str: String): String {
    return str.lowercase()
}

// lamba 测试在函数内，定义前访问
fun testLambda() {
    var countString = { str: String -> str.length }
    log(countString("hello")) // 必须在定义后访问
}

// 布局变量和函数只允许在声明后引用
// 允许前向引用可能会导致复杂的依赖关系，特别是在涉及递归时。
// 要求函数在使用前定义可以提高代码的可读性。这鼓励开发者按照逻辑顺序组织代码。
// 这与局部变量的行为保持一致。局部函数本质上也是局部声明，因此遵循相同的规则有助于保持语言的一致性。

fun testFunction() {
    fun countString(str: String): Int {
        return str.length
    }
    log(countString("hello")) // 可以在定义前访问
}
// 顶层变量在整个文件都是可见的  
// 允许在声明前引用顶层变量使得处理循环依赖变得更容易。例如，两个相互依赖的顶层变量可以引用对方。
// 编译器可以更容易地优化顶层声明，因为它们的生命周期和可见性是预定义的。
 
// 高阶函数
// 高阶函数是将函数作为参数或返回值的函数。
// 高阶函数可以简化代码，提高代码的灵活性和可重用性。
// 高阶函数可以接受一个函数作为参数，或者返回一个函数。
// 高阶函数可以简化代码，提高代码的灵活性和可重用性。

// 函数作为参数传递
fun testFilter() {
    val numbers = listOf(1, 2, 3, -2, -4, 9, -5)
    val positiveNumbers = numbers.filter({ x -> x > 0 })

    val isNegative = { x: Int -> x < 0 }
    val negativeNumbers = numbers.filter(isNegative)
    println(positiveNumbers)
    println(negativeNumbers)

    // 如果函数是唯一的参数，则可以省略括号
    // 当 lambda 表达式只有一个参数时，如果你不显式声明参数，Kotlin 会自动为这个单一参数提供一个隐式名称 it。这是一种简化语法。
    // 当你显式声明参数时（如 x: Int -> x > 0），你需要指定参数名和可选的类型。
    val positiveNumbers2 = numbers.filter { it > 0 }
    val positiveNumbers3 = numbers.filter { number -> number > 0 }
    println(positiveNumbers2)
    println(positiveNumbers3)

    val tripled = numbers.map { it * 3 }
    println(tripled)
}

// 函数作为参数和返回值，需要声明函数类型
// (String) -> String
// (Int, Int) -> Int
// lambda 表达式参数为空，则保留()
// () -> Unit
val upperCaseString2: (String) -> String = { str: String -> str.uppercase() }
val multiply2: (Int, Int) -> Int = { a, b -> a * b }
val doNothing: () -> Unit = { println("I'm not doing anything") }
// 声明了一个函数类型，然后赋值一个 lambda 表达式
// 如果一个函数声明返回 Unit 即无返回值，可以省略 return 语句。函数体的最后一个表达式的结果会被忽略。


// 函数作为返回值
// 单表达式函数：当函数体只有一个表达式时，可以省略花括号和 return 关键字，直接使用 = 连接函数签名和表达式。
fun toSeconds(time: String): (Int) -> Int = when (time) {
    "hour" -> { hours -> hours * 60 * 60 }
    "minute" -> { minutes -> minutes * 60 }
    "second" -> { seconds -> seconds }
    else -> { value -> value }
}


// lambda 表达式可以在后面添加 () 传递参数进行立即调用
fun testLambdaInvokeSeparately() {
    println({ text: String -> "Hello, $text" }("world"))
}

// 如果 lambda 是唯一的参数，可以省略括号
// 如果 lambda 是函数最后一个参数，可以放在括号外面
fun testLambdaAsParameter() {
    println(listOf(1, 2, 3, 4, 5).map { it * 2 })
    println(listOf(1, 2, 3).fold(0) { acc, i -> acc + i })
}

fun do_lambda_practice() {
    println("========== do_lambda_practice 1: a list of URLs ==========")
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/books-info"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action" }
    urls.forEach { url -> println(url) }

    println("========== do_lambda_practice 2: repeat ==========")
    fun repeatN(n: Int, action: () -> Unit) {
        for (i in 1..n) {
            action()
        }
    }
    repeatN(5) { println("Hello, world!") }
}

// 有显式返回值的 lambda 表达式
val processUser = { name: String, age: Int ->
    println("Processing user data...")
    val greeting = "Hello, $name!"
    val ageGroup = when {
        age < 18 -> "minor"
        age < 65 -> "adult"
        else -> "senior"
    }
    println("Age group determined.")
    "$greeting You are a $ageGroup." // 这是 lambda 的返回值
}

// 没有显式返回值的 lambda 表达式
// 操，原来 lambda 可以有多行啊 
val printSystemInfo = {
    println("System Information:")
    println("OS: ${System.getProperty("os.name")}")
    println("Java Version: ${System.getProperty("java.version")}")
    println("Available Processors: ${Runtime.getRuntime().availableProcessors()}")
    println("Free Memory: ${Runtime.getRuntime().freeMemory() / 1024 / 1024} MB")
    // 这个 lambda 没有显式返回值，默认返回 Unit（相当于 void）
}
