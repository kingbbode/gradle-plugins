package com.github.kingbbode

import com.moowork.gradle.node.NodePlugin
import com.moowork.gradle.node.npm.NpmInstallTask
import com.moowork.gradle.node.npm.NpmTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin

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
        extension = project.extensions.create(NpmTaskGeneratorPluginExtension.NAME, NpmTaskGeneratorPluginExtension)
        extension.configs.forEach{k,v ->createTask(k,v)}
    }

    void createTask(String name, NpmTaskGeneratorConfig npmTaskConfig) {
        NpmTask task = project.tasks.create(name, NpmTask)
        task.group = "nodeTask"
        task.description = npmTaskConfig.description
        task.setNpmCommand(npmTaskConfig.command.split(" "))
        if(npmTaskConfig.npmInstall) {
            task.dependsOn(project.getTasks().getByName(NpmInstallTask.NAME))
        }
        if(npmTaskConfig.resource) {
            project.getTasks().getByName(JavaPlugin.PROCESS_RESOURCES_TASK_NAME).dependsOn(task)
        }
    }
}
