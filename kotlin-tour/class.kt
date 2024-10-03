import kotlin.random.Random

// 声明一个类 
class Customer

// 声明类的属性
class Contact(val id: Int, var email: String = "default@abc.com") {
    val category: String = "Unknown"

    fun printId() {
        println("The id is $id")
    }

}

/*
|--------------------------------------------------------------------------------------
| 数据类
|--------------------------------------------------------------------------------------
| 
| Kotlin has data classes which are particularly useful for storing data. 
| Data classes have the same functionality as classes, but they come automatically 
| with additional member functions. These member functions allow you to easily print 
| the instance to readable output, compare instances of a class, copy instances, and more. 
| As these functions are automatically available, you don't have to spend time writing 
| the same boilerplate code for each of your classes.
|
| Kotlin 具有数据类，这些数据类对于存储数据特别有用。数据类具有与类相同的功能，但它们自动附带其他成员函数。
| 这些成员函数允许您轻松地将实例打印为可读输出、比较类的实例、复制实例等。由于这些函数是自动可用的，
| 因此您不必花时间为每个类编写相同的样板代码。
|
| 数据类最常用的预定义成员函数是：
| - toString() 打印类的实例及其属性的可读字符串。
| - equals() or == 比较两个实例是否相等。
| - copy() 复制一个实例。
| - componentN() 解构声明一个实例。
*/
data class User(val name: String, val id: Int)



fun main() {
    val contact = Contact(1, "test@example.com")
    contact.email = "test2@example.com"
    println("Their email is ${contact.email}")
    contact.printId()


    val user = User("Alex", 1)
    val secondUser = user.copy()
    val thirdUser = User("Max", 2)
    println(user)
    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")
    println(user.copy())
    println(user.copy("Max"))
    println(user.copy(id = 2, name = "Max"))

    do_practice_data_class()
    do_practice_data_class_2()
    do_practice_data_class_3()
}



fun do_practice_data_class() {
    data class Employee(val name: String, var salary: Int)

    val emp = Employee("Mary", 20)
    println(emp)

    emp.salary += 10
    println(emp)
}

fun do_practice_data_class_2() {
    data class City(val name: String, val country: String)
    data class Address(val street: String, val city: City)
    data class Name(val firstName: String, val lastName: String)
    data class Person(val name: Name, val address: Address, val ownsAPet: Boolean = true)

    val person = Person(
        Name("John", "Doe"), 
        Address("123 Main St", City("New York", "USA")),
        ownsAPet = false
    )

    println(person)
}

fun do_practice_data_class_3() {
    data class Employee(val name: String, var salary: Int)

    class RandomEmployee(var minSalary: Int, var maxSalary: Int) {
        val names = listOf("John", "Jane", "Doe", "Smith", "Johnson")

        fun generateEmployee(): Employee {
            val randomName = names.random()
            val randomSalary = (minSalary..maxSalary).random()
            return Employee(randomName, randomSalary)
        }

        fun generateEmployee2() = Employee(names.random(), Random.nextInt(from = minSalary, until = maxSalary))
    }

    val randomEmployee = RandomEmployee(1000, 2000)
    println(randomEmployee.generateEmployee())
    println(randomEmployee.generateEmployee2())
    println(randomEmployee.generateEmployee())
    randomEmployee.minSalary = 1500
    randomEmployee.maxSalary = 3000
    println(randomEmployee.generateEmployee())
    println(randomEmployee.generateEmployee2())
    println(randomEmployee.generateEmployee())
}
