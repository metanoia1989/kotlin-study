fun main() {
    var neverNull: String = "This can't be null"
    // neverNull = null // 编译错误

    var nullable: String? = "You can be null" // 可以为 null，在类型声明后添加了?
    nullable = null // 可以赋值为 null

    fun strLength(notNull: String): Int {
        println("notNull: $notNull")
        return notNull.length
    }

    fun strLength(nullable: String?): Int {
        println("nullable: $nullable")
        return nullable?.length ?: 0
    }
    
    println(strLength(neverNull))
    println(strLength(nullable))

    // 检测是否为null
    fun describeString(maybeString: String?): String {
        if (maybeString != null && maybeString.isNotEmpty()) {
            return "String of length ${maybeString.length}"
        } else {
            return "Empty string"
        }
    }

    println(describeString(neverNull))
    println(describeString(nullable))

    
    // 安全调用 
    fun strLengthSafe(maybeString: String?): Int? = maybeString?.length
    println(strLengthSafe(neverNull))
    println(strLengthSafe(nullable))

    // Elvis 操作符
    fun strLengthElvis(maybeString: String?): Int = maybeString?.length ?: 0
    println(strLengthElvis(neverNull))
    println(strLengthElvis(nullable))

    do_practice_nullable()
}


fun do_practice_nullable() {
    data class Employee(val name: String, val salary: Int)

    fun employeeById(id: Int): Employee? = when(id) {
        1 -> Employee("Alice", 1000)
        2 -> null
        3 -> Employee("Charlie", 3000)
        4 -> Employee("David", 4000)
        else -> null
    }

    fun salaryById(id: Int) = employeeById(id)?.salary ?: 0

    println((1..5).sumOf { id -> salaryById(id) }) // sumOf 只对非null的值求和
}
    