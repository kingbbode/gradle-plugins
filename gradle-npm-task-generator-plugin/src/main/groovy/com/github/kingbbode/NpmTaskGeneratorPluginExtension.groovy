package com.github.kingbbode


/**
 * Created by YG-MAC on 2018. 1. 11..
 */

class NpmTaskGeneratorPluginExtension {

    public static final String NAME = "npmTasks"

    final Map<String, NpmTaskGeneratorConfig> configs = [:]

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
        configs[name] = taskConfig
    }
}
