package com.github.lynxie.oxocarbon

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.util.registry.Registry

class Oxocarbon : ProjectActivity {

    override suspend fun execute(project: Project) {
        Registry.get("editor.paint.empty.text").setValue(false)
    }
}