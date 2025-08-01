package com.github.idea.plugin.windows.issue.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class MyProjectService(project: Project) {

    fun getRandomNumber() = (1..100).random()
}
