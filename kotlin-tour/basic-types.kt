var customers = 10

// 在顶层执行四则运算，仅初始化的时候可以 
// 目的是保持顶层声明的简洁性和可预测性。
// 顶层主要用于声明和初始化，而不是执行复杂的逻辑或多步骤的操作。
val result = customers + 5 - 2 * 3 / 2

// 有符号整数
var Die: Byte = 127
var money: Short = 1000
var year: Int = 2020
var sand: Long = 1000000000000000000

// 无符号整数
var age: UByte = 100u
var money2: UShort = 10000u
var year2: UInt = 2020u
var sand2: ULong = 1000000000000000000u

// 浮点数
var pi: Float = 3.14159265358f
var pi2: Double = 3.14159265358

// 布尔值
var hasMoney: Boolean = true

// 字符
var char: Char = 'A'
var separator: Char = '\u0020'
var tab: Char = '\t'
var comma: Char = ','

// 字符串
var name: String = "John"
var message: String = "Hello, $name"

// 不允许在变量未初始化前使用
// var notInitialized: Int  
// println("Not initialized: $notInitialized")

fun declare_correct() {
    val a: Int = 100
    val b: String = "log message"
    val c: Double = 3.14159265358
    val d: Int = 100_000_000
    var e: Boolean = false
    var f: Char = '\n'

    println("a: $a, b: $b, c: $c, d: $d, e: $e, f: $f")

}

fun main() {
    println("Top level calculation result: $result")
    
    // 原有的操作
    customers = 8
    customers = customers + 8
    customers += 7
    customers -= 3
    customers *= 2
    customers /= 3
    println("Final customer count: $customers")

    // 字符串操作
    println("Separator: $separator$comma")
    println("Message: $message")

    declare_correct()
}
