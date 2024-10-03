fun main() {
    collection()
    set()
    map()

    do_exercise()
}


/**
 * 集合
 */
fun collection() {
    println("========== collection ==========")

    // 只读集合
    val readOnlyCollection: Collection<Int> = listOf(1, 2, 3, 4, 5)
    val readOnlyCollection2 = listOf("triangle", "circle", "square")

    // 可变集合
    val shapes: MutableList<String> = mutableListOf("triangle", "circle", "square") 
    val shapesLocked: List<String> = shapes // 只读的可变列表 
    println(readOnlyCollection)
    println(readOnlyCollection2)
    println(shapes)


    // 通过 [] 访问元素
    println("shapes[0]: ${shapes[0]}")
    println("shapesLocked[1]: ${shapesLocked[1]}")

    // 通过 add 添加元素，remove 删除元素
    shapes.add("rectangle")
    println("after add: ${shapes}")
    shapes.remove("circle")
    println("after remove: ${shapes}")

    // 获取第一个和最后一个元素
    println("first: ${shapes.first()}")
    println("last: ${shapes.last()}")

    // 获取元素的个数
    println("size: ${shapes.size}")

    // 检查是否包含某个元素，通过 in 操作符 和 .contains 方法
    println("contains: ${shapes.contains("triangle")}")
    println("contains: ${"circle" in shapes}")

    // 检查是否不包含某个元素
    println("contains: ${!shapes.contains("circle")}")

    // 检查是否为空
    println("isEmpty: ${shapes.isEmpty()}")
}

fun set() {
    println("========== set ==========")

    // 只读集合
    val readOnlyFruit = setOf("apple", "banana", "orange")
    // 可变集合
    val fruit: MutableSet<String> = mutableSetOf("apple", "banana", "orange")

    println("readOnlyFruit: ${readOnlyFruit}")
    println("fruit: ${fruit}")
    println("readOnlyFruit set has items: ${readOnlyFruit.count()}")
    println("banana in readOnlyFruit: ${"banana" in readOnlyFruit}")
    println("banana in fruit: ${"banana" in fruit}")

    // 添加元素
    fruit.add("grape")
    println("after add: ${fruit}")

    // 删除元素
    fruit.remove("banana")
    println("after remove: ${fruit}")

}

fun map() {
    println("========== map ==========")

    // 只读 map
    val readOnlyJuiceMenu = mapOf("apple" to 100, "kiwi" to 150, "orange" to 120) 
    println("readOnlyJuiceMenu: ${readOnlyJuiceMenu}")

    // 可变 map
    val juiceMenu: MutableMap<String, Int> = mutableMapOf("apple" to 100, "kiwi" to 150, "orange" to 120)
    println("juiceMenu: ${juiceMenu}")

    // 将可变map赋值给只读视图
    var juiceMenuView: Map<String, Int> = juiceMenu
    println("juiceMenuView: ${juiceMenuView}")
    println("juiceMenuView is mutable: ${juiceMenuView is MutableMap}")

    // 打印所有的 key
    println("all keys: ${juiceMenuView.keys}")

    // 打印所有的 value
    println("all values: ${juiceMenuView.values}")

    // 通过 key 获取 value
    println("apple price: ${juiceMenuView["apple"]}")
    println("flower price: ${juiceMenuView["flower"]}") // 返回 null


    // 通过 key 获取 value，如果不存在，返回默认值
    println("grape price: ${juiceMenuView.getOrDefault("grape", 130)}")
    
    // 检查是否包含某个 key
    println("contains key apple by containsKey: ${juiceMenu.containsKey("apple")}")
    println("contains key apple by in: ${"apple" in juiceMenu}")
    println("contains key apple by in map.keys: ${"apple" in juiceMenu.keys}")
    println("contains value 100 by containsValue: ${juiceMenu.containsValue(100)}")
    println("contains value 100 by in: ${100 in juiceMenu.values}")


    // 遍历 map
    for ((key, value) in juiceMenu) {
        println("key: ${key}, value: ${value}")
    }
    
    // 修改 value
    juiceMenu["apple"] = 110
    println("after modify: ${juiceMenu}")

    // 删除元素
    juiceMenu.remove("kiwi")
    println("after remove: ${juiceMenu}")
    
    // 清空 map
    juiceMenu.clear()
    println("after clear: ${juiceMenu}")
}

fun do_exercise() {
    println("========== do_exercise 1: compute total numbers ==========")

    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    println("total numbers: ${greenNumbers.count() + redNumbers.count()}")

    println("========== do_exercise 2: check protocol whether is supported ==========")
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    var isSupported = requested in SUPPORTED
    println("Support for ${requested}: ${isSupported}")

    println("========== do_exercise 3: get spell of number ==========")
    var number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    var numbers = listOf(1, 2, 3)
    for (n in numbers) {
        println("${n} is spelt as ${number2word[n]}")
    }
}