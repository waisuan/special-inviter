package org.esia.specialinviter.lib.file

abstract class FileParser<out T> {
    abstract fun parse(fileName: String): List<T>
}
