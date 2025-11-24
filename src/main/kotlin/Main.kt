package org.example

fun main() {
    // Define the configuration text with different keys and values
    val config = """
        userid, user-123; role admin
        authToken   token-xyz-789
        environment : productionc; expiry: 1672531199
    """.trimIndent()

    // Test extracting values from the config text
    println("role = ${extractValue(config, "role")}")        // admin
    println("authToken = ${extractValue(config, "authToken")}") // token-xyz-789
    println("hostname = ${extractValue(config, "hostname")}")   // null
}

// Function to extract a value based on a given key
fun extractValue(input: String, key: String): String? {
    // Split the text into separate lines
    val lines = input.split("\n")

    // Loop through each line
    for (line in lines) {
        // Clean the line and split it into parts (words)
        val parts = line
            .replace(",", " ")
            .replace(";", " ")
            .replace(":", " ")
            .split(" ")
            .filter { it.isNotBlank() }

        // Loop through each part in the line
        for (i in parts.indices) {
            val currentKey = parts[i].lowercase() // current key
            if (currentKey == key.lowercase()) {
                // If the key matches, return the next part as the value
                if (i + 1 < parts.size) {
                    val value = parts[i + 1].trim() // the value for the key
                    return value
                }
            }
        }
    }
    // Return null if the key was not found
    return null
}