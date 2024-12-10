package com.example.myproject

data class QuizResult(
    var name: String? = null,
    var rollNo: String? = null,
    var score: Int = 0
) {

    constructor() : this(null, null, 0)
}