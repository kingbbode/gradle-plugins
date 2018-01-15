package com.github.kingbbode

import com.moowork.gradle.node.NodePlugin
import com.moowork.gradle.node.npm.NpmInstallTask
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by YG-MAC on 2018. 1. 11..
 */

@SuppressWarnings("GroovyUnusedDeclaration")
class NpmTaskGeneratorPlugin implements Plugin<Project>{

    Project project
    NpmTaskGeneratorPluginExtension extension

    @Override
    void apply(Project project) {
        project.plugins.apply(NodePlugin.class)
        this.project = project
        def whenConfigurationAdded = { String name, NpmTaskGeneratorConfig npmTaskConfig ->
            NpmCustomTask task = project.tasks.create(name, NpmCustomTask)
            task.group = "npmTask"
            task.description = npmTaskConfig.description
            task.setNpmCommand(npmTaskConfig.command.split(" "))
            if(npmTaskConfig.npmInstall) {
                task.dependsOn(project.getTasks().getByName(NpmInstallTask.NAME))
            }
            if(!npmTaskConfig.hasDependsOn()) {
                project.getTasks().getByName(npmTaskConfig.dependsOn).dependsOn(task)
            }
        }
        extension = project.extensions.create(NpmTaskGeneratorPluginExtension.NAME, NpmTaskGeneratorPluginExtension.class, whenConfigurationAdded)
    }
}
