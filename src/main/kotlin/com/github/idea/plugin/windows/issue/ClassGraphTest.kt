package com.github.idea.plugin.windows.issue

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.github.classgraph.ClassGraph


@TestDataPath("\$CONTENT_ROOT/src/test/testData")
class ClassGraphTest : BasePlatformTestCase() {

    override fun getTestDataPath() = "src/test/testData/common"

    fun testClassGraphScan() {
        try {
            val classes = ClassGraph()
                .acceptPackages("io.github.classgraph")
                .scan().use { scan ->
                    val foundClasses = scan.allClasses.loadClasses()
                    foundClasses.toSet()
                }

            assertNotEmpty(classes)
        } catch (e: Exception) {
            e.printStackTrace()
            fail("Failed to load classes from classpath: ${e.message}")
        }
    }
}
