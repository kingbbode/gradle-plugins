package com.github.kingbbode

import com.moowork.gradle.node.NodePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by YG-MAC on 2018. 1. 11..
 */

@SuppressWarnings("GroovyUnusedDeclaration")
class WebpackPlugin implements Plugin<Project>{
    
    @Override
    void apply(Project project) {
        project.plugins.apply(NodePlugin.class)
        project.task(type: WebpackTask, "webpack")
    }
}
