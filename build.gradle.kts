// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath (BuildPluginsDependencies.androidGradlePlugin)
        classpath (BuildPluginsDependencies.kotlinGradlePlugin)
        classpath (BuildPluginsDependencies.hiltGradlePlugin)
        classpath (BuildPluginsDependencies.navigationGradlePlugin)
        classpath (BuildPluginsDependencies.hiltGradlePlugin)
        classpath (BuildPluginsDependencies.navigationGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")

    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}