package dev.paulshields.aoc.common

import java.util.Stack

fun <T> List<T>.toStack() = Stack<T>().also {
    it.addAll(this)
}