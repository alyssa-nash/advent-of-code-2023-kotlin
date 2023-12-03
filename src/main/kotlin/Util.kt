import java.io.File

fun readLinesFromFile(fileName: String): Sequence<String> =
    File(ClassLoader.getSystemResource(fileName).file).useLines { it.toList() }.asSequence()

