package com.github.kingbbode

import com.moowork.gradle.node.npm.NpmInstallTask
import com.moowork.gradle.node.npm.NpmTask
import org.gradle.api.plugins.JavaPlugin

/**
 * Created by YG-MAC on 2018. 1. 11..
 */

class WebpackTask extends NpmTask{

    WebpackTask()
    {
        this.group = 'node'
        this.description = 'bundle application from webpack.config.js.'
        WebpackPluginExtension extension = project.extensions.create(WebpackPluginExtension.NAME, WebpackPluginExtension)
        String[] command = ['webpack']
        String[] options = extension.options.split(" ")
        setNpmCommand(command + options)
        dependsOn(project.getTasks().getByName(NpmInstallTask.NAME))
        project.getTasks().getByName(JavaPlugin.PROCESS_RESOURCES_TASK_NAME).dependsOn(this)
    }
}
