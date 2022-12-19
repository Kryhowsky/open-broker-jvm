package org.openbroker.common

internal fun Int?.requireMin(min: Int, propertyName: String? = null) {
    if(this == null)
        return
    require(this >= min) {
        val descriptor: String = propertyName?.let { " '$it'" } ?: ""
        "{\"message\": \"Value$descriptor must be at least $min\", \"$propertyName\": \"$this\"}"
    }
}

internal fun Int?.requireInRange(min: Int, max: Int, propertyName: String) {
    require(this in min .. max) {
        "{\"message\": \"Invalid $propertyName, should be in range $min-$max\", \"$propertyName\": \"$this\"}"
    }
}

internal fun Int.requireLessThanOrEqual(compareTo: Int, compareToPropertyName: String, propertyName: String) {
    require(this <= compareTo) {
        "{\"message\": \"Value for $propertyName cannot be greater than value for $compareToPropertyName\", \"$propertyName\": \"$this\", \"$compareToPropertyName\": \"$compareTo\"}"
    }
}

internal fun String.requireNotBlank(propertyName: String) {
    require(this.isNotBlank()) {
        "{\"message\": \"$propertyName cannot be blank\"}"
    }
}

internal fun String.requireNotEmpty(propertyName: String) {
    require(this.isNotEmpty()) {
        "{\"message\": \"$propertyName cannot be empty\"}"
    }
}

internal fun String.requireMatchRegex(regex: Regex, propertyName: String) {
    require(this.matches(regex)) {
        "{\"message\": \"Invalid $propertyName value, does not match regex\", \"$propertyName\": \"$this\"}"
    }
}

internal fun List<String>.requireAllMatchRegex(regex: Regex, propertyName: String) {
    var allValid = true
    val notValidElements: MutableList<String> = mutableListOf()
    this.forEach {
        if (!it.matches(regex)) {
            allValid = false
            notValidElements.add(it)
        }
    }
    require(allValid) {
        "{\"message\": \"Invalid element in $propertyName, does not match regex\", \"$propertyName\": [${notValidElements.joinToString { it }}]}"
    }
}

internal fun <T> List<T>.requireNotEmpty(propertyName: String) {
    require(this.isNotEmpty()) {
        "{\"message\": \"$propertyName cannot be empty\"}"
    }
}