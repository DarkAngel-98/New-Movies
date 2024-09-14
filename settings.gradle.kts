pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io") // Add JitPack repository here
    }
}

rootProject.name = "My Movies"
include(":app")
include(":home")
include(":common")
include(":favorite")
include(":category")
include(":shared")
