package com.example.ecommerceappchallenge

import android.util.Log

/**
 * This class is a non-static logger
 */
open class Logger {
    open fun e(tag: String?, message: String?) {
        if (message != null) {
            Log.e(tag, message)
        }
    }

    open fun w(tag: String?, message: String?) {
        if (message != null) {
            Log.w(tag, message)
        }
    }

    open fun v(tag: String?, message: String?) {
        if (message != null) {
            Log.v(tag, message)
        }
    }

    open fun d(tag: String?, message: String?) {
        if (message != null) {
            Log.d(tag, message)
        }
    }
}