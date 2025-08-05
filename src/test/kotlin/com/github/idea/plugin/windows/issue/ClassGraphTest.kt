package com.github.idea.plugin.windows.issue

import com.github.idea.plugin.windows.issue.services.MyProjectService
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import com.intellij.util.lang.PathClassLoader
import io.github.classgraph.ClassGraph
import java.nio.file.FileSystems

/**
 * ClassGraphTest doesn't fail with 2024.3.6, but it does with 2025.1.4.1 on Windows.
 */
@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class ClassGraphTest : BasePlatformTestCase() {

    override fun getTestDataPath() = "src/test/testData/common"

    fun testClassGraphScan() {
        try {
            val classLoader = MyProjectService::class.java.classLoader as PathClassLoader
            val path = classLoader.files.get(0)

            println("Using class loader: " + classLoader)
            println("Trying to create File from Path: " + path.toString())
            println("Path filesystem: " + path.fileSystem + ", global file system: " + FileSystems.getDefault())

            // this fails on windows/mac os
            val file = path.toFile()

            // original failing code
//            val classes = ClassGraph()
//                .acceptPackages("io.github.classgraph")
//                .scan().use { scan ->
//                    val foundClasses = scan.allClasses.loadClasses()
//                    foundClasses.toSet()
//                }
//
//            assertNotEmpty(classes)
        } catch (e: Exception) {
            e.printStackTrace()
            fail("Failed to load classes from classpath: ${e.message}")
        }
    }
}