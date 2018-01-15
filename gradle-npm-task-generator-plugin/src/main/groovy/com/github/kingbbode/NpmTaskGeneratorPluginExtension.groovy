package com.github.kingbbode
/**
 * Created by YG-MAC on 2018. 1. 11..
 */

class NpmTaskGeneratorPluginExtension {

    public static final String NAME = "npmTasks"

    final Set<String> names = []
    final Closure whenConfigAdded

    NpmTaskGeneratorPluginExtension(Closure whenConfigAdded) {
        this.whenConfigAdded = whenConfigAdded
    }

    def methodMissing(String name, args) {
        if (!args.length == 1 || !(args[0] instanceof Closure)) {
            throw new MissingMethodException(name, getClass(), args)
        }
        apply(name, args[0])

    }

    private void apply(String name, Closure closure) {
        def taskConfig = new NpmTaskGeneratorConfig()
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure.delegate = taskConfig
        closure()
        createTask(name, taskConfig)
    }

    private void createTask(String name, NpmTaskGeneratorConfig taskGeneratorConfig) {
        if(names.contains(name)){
            throw new IllegalArgumentException("exist duplicated task name.")
        }
        whenConfigAdded(name, taskGeneratorConfig)
        names.add(name)
    }
}
