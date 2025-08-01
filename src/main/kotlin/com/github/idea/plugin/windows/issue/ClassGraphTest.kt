package com.github.idea.plugin.windows.issue

import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import io.github.classgraph.ClassGraph

/**
 * ClassGraphTest doesn't fail with 2024.3.6, but it does with 2025.1.4.1 on Windows.
 */
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
