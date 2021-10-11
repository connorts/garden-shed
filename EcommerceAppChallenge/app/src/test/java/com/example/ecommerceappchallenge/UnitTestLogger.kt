package com.example.ecommerceappchallenge

class UnitTestLogger : Logger() {
    override fun e(tag: String?, message: String?) {
        println("E $tag: $message")
    }

    override fun w(tag: String?, message: String?) {
        println("W $tag: $message")
    }

    override fun v(tag: String?, message: String?) {
        println("V $tag: $message")
    }

    override fun d(tag: String?, message: String?) {
        println("D $tag: $message")
    }
}