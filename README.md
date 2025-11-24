Markdown

# Config Value Extractor (Kotlin)

A project written in **Kotlin** that demonstrates a robust way to extract values from a **configuration text** that contains different keys and values, even with loose or varied formatting.

**Example Configuration Text:**
userid, user-123; role admin   authToken   token-xyz-789   environment : productionc; expiry: 1672531199  


The program searches for a given key (such as `role` or `authToken`) and returns the associated value, or `null` if the key is not found.

***

## ⚙️ Features

* **Extract values** from loosely structured configuration text.
* Handles **multiple delimiters** such as `,`, `;`, and `:`.
* Returns `null` when the requested key does not exist.

***

## 🖥️ Example Usage

The following code demonstrates how to use the `extractValue` function:

```kotlin
fun main() {
    val config = """
        userid, user-123; role admin
        authToken   token-xyz-789
        environment : productionc; expiry: 1672531199
    """.trimIndent()

    println("role = ${extractValue(config, "role")}")        
    println("authToken = ${extractValue(config, "authToken")}") 
    println("hostname = ${extractValue(config, "hostname")}")
}
📌 Output
The expected output when running the example above:

role = admin
authToken = token-xyz-789
hostname = null
🛠️ Extract Function
The core logic is implemented in the extractValue function, which handles parsing the text and finding the key-value pairs:

Kotlin

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
To run this project, ensure you have Kotlin installed on your system.

Save the provided code (including the main and extractValue functions) in a file named Main.kt inside a Kotlin project.

Compile the program:

Bash

kotlinc Main.kt -include-runtime -d main.jar
Run the compiled .jar file:

Bash

java -jar main.jar
📂 Project Structure
The recommended file structure for the project:

project-root/
│── src/
│   └── main/
│       └── kotlin/
│           └── org/example/Main.kt
│── README.md
