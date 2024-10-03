import kotlin.random.Random

fun main() {
    if_expression()
    when_expression()
    range_expression()
    do_flow_exercise()

    for_loop()
    while_loop()
    do_loop_exercise()
}

fun if_expression() {
    println("========== if_expression ==========")
    var d: Int
    var check = true

    if (check) {
        d = 1
    } else {
        d = 2
    }

    println("d: ${d}")


    // kotlin 不支持三元表达式，可以使用 if-else 表达式代替
    // 当 if 表达式只有一行代码时，可以省略大括号
    val a = 1
    val b = 2
    val max = if (a > b) a else b
    println("max: ${max}")
}

fun when_expression() {
    println("========== when_expression ==========")

    // when 表达式类似于其他语言的 switch case 表达式
    val obj = "Hello"

    when (obj) {
        "1" -> println("One")
        "Hello" -> println("Greeting")
        else -> println("Unknown")
    }

    // when 的表达式可以直接返回一个值
    val result = when (obj) {
        "1" -> "One"
        "Hello" -> "Greeting"
        else -> "Unknown"
    }
    println("result: ${result}")

    // when 表达式可以有多个条件
    val x = 1
    when (x) {
        1, 2 -> println("x is 1 or 2")
        else -> println("x is neither 1 nor 2")
    }

    // 不提供 when 的 object 
    val trafficLightState = "Red" // Red, Yellow, Green
    val trafficAction = when {
        trafficLightState == "Red" -> "Stop"
        trafficLightState == "Yellow" -> "Slow Down"
        trafficLightState == "Green" -> "Go"
        else -> "Malfunction"
    }
    println("trafficAction: ${trafficAction}")
}

fun range_expression() {
    println("========== range_expression ==========")

    // 范围表达式
    println("range 1..4: " + (1..4).toList())
    println("range 1 until 4: " + (1 until 4).toList())
    println("range 1..4 step 2: " + (1..4).step(2).toList())
    println("range 1..<4: " + (1..<4).toList())
    println("range 4 downTo 1: " + (4 downTo 1).toList())
    println("range 4 downTo 1 step 2: " + (4 downTo 1).step(2).toList())
    println("'a'..'z': " + ('a'..'z').toList())
    println("'z' downTo 'a': " + ('z' downTo 'a').toList())
    println("'z' downTo 'a' step 2: " + ('z' downTo 'a').step(2).toList())
}

fun do_flow_exercise() {
    println("========== do_exercise 1: shoot dice ==========")

    val firstResult = (1..6).random()
    val secondResult = Random.nextInt(1, 7)


    println("firstResult: ${firstResult}")
    println("secondResult: ${secondResult}")
    if (firstResult == secondResult) {
        println("You win!")
    } else {
        println("You lose!")
    }
}
    
fun for_loop() {
    println("========== for_loop ==========")

    // range 迭代
    for (i in 1..6) {
        println("i: ${i}")
    }

    // collection 迭代
    val cakes = listOf("carrot", "cheese", "chocolate")
    for (cake in cakes) {
        println("Yummn, it's a ${cake} cake!")
    }

    // 迭代 map
    val map = mapOf("Alice" to 25, "Bob" to 30, "Charlie" to 35)
    for ((name, age) in map) {
        println("${name} is ${age} years old")
    }
}

fun while_loop() {
    println("========== while_loop ==========")

    // while 循环
    var x = 10
    while (x > 0) {
        println("x: ${x}")
        x--
    }
    var cakeEaten = 0
    while (cakeEaten < 3) {
        println("Eat a cake!")
        cakeEaten++
    }

    // do while 循环
    var cakeBaked = 0
    do {
        println("Baked a cake!")
        cakeBaked++
    } while (cakeBaked < cakeEaten)
}

fun do_loop_exercise() {
    println("========== count pizza slices ==========")
    var pizzaSlices = 0
    while (pizzaSlices < 10) {
        println("There's only ${pizzaSlices} slices of pizza :(")
        pizzaSlices++
    }
    println("There are ${pizzaSlices} slices of pizza. Hooray! We have enough pizza!")

    pizzaSlices = 0
    do {
        println("There's only ${pizzaSlices} slices of pizza :(")
        pizzaSlices++
    } while (pizzaSlices < 10)
    println("There are ${pizzaSlices} slices of pizza. Hooray! We have enough pizza!")  

    println("========== FizzBuzz ==========")
    for (i in 1..100) {
        // 原来 when省略(条件)是这样用的，一个参数进行不同的条件判断
        when {
            i % 15 == 0 -> print("FizzBuzz ")
            i % 3 == 0 -> print("Fizz ")
            i % 5 == 0 -> print("Buzz ")
            else -> print("${i} ")
        }
    }

    println("========== print words that start with letter 'l' ==========")
    val words = listOf("apple", "banana", "grape", "lemon", "melon", "orange", "peach", "pear", "watermelon")
    for (word in words) {
        if (word.startsWith("l")) {
            println(word)
        }
    }
}