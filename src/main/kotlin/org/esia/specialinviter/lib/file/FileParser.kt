package org.esia.specialinviter.lib.file

abstract class FileParser<T> {
    abstract fun parse(fileName: String): List<T>
}
