pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        maven {
            url = uri("https://www.jitpack.io" )
        }
        google()
        mavenCentral()
    }
}



rootProject.name = "chart"
include(":app")
 