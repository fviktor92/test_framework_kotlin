package com.fviktor.test_framework_kotlin.common

import java.io.FileNotFoundException

class ResourceFileReader
{
    companion object
    {
        /**
         * Locates the resource by the given [fileName] related to the given [clazz] class and returns its content as a text.
         *
         * **Replaces CRLF line endings to LF**
         */
        fun getResourceAsText(clazz: Class<*>, fileName: String): String
        {
            val resource = clazz.getResource(fileName)
                ?: throw FileNotFoundException("Could not find resource file for given $clazz and $fileName file name")
            return resource.readText().replace("\r\n", "\n")
        }
    }
}