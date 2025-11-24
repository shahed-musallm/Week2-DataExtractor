
📌 Overview
This project is written in Kotlin and demonstrates how to extract values from a configuration text that contains different keys and values.
For example:
userid, user-123; role admin
authToken   token-xyz-789
environment : productionc; expiry: 1672531199


The program searches for a given key (such as role or authToken) and returns the associated value, or null if the key is not found.

⚙️ Features
- Extract values from loosely structured configuration text.
- Handles multiple delimiters such as , ; :.
- Returns null when the key does not exist.

🖥️ Example Usage
Main program:
fun main() {
    val config = """
        userid, user-123; role admin
        authToken   token-xyz-789
        environment : productionc; expiry: 1672531199
    """.trimIndent()

    println("role = ${extractValue(config, "role")}")        // admin
    println("authToken = ${extractValue(config, "authToken")}") // token-xyz-789
    println("hostname = ${extractValue(config, "hostname")}")   // null
}

📌 Output:
role = admin
authToken = token-xyz-789
hostname = null



🛠️ Extract Function
fun extractValue(input: String, key: String): String? {
    val lines = input.split("\n")
    for (line in lines) {
        val parts = line
            .replace(",", " ")
            .replace(";", " ")
            .replace(":", " ")
            .split(" ")
            .filter { it.isNotBlank() }

        for (i in parts.indices) {
            val currentKey = parts[i].lowercase()
            if (currentKey == key.lowercase()) {
                if (i + 1 < parts.size) {
                    return parts[i + 1].trim()
                }
            }
        }
    }
    return null
}



🚀 How to Run
- Make sure Kotlin is installed on your system.
- Save the code in a file named Main.kt inside a Kotlin project.
- Compile and run the program:
kotlinc Main.kt -include-runtime -d main.jar
java -jar main.jar



📂 Project Structure
project-root/
│── src/
│   └── main/
│       └── kotlin/
│           └── org/example/Main.kt
│── README.md



Would you like me to also add a Future Improvements section (e.g., support for JSON or YAML configs) to make the README more professional?
