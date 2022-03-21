rootProject.name = "Loyalty Rewards"
include(":app")

pluginManagement {

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    resolutionStrategy {
        val idToModule = mapOf(
            "com.android.application" to "com.android.tools.build:gradle",
            "dagger.hilt.android.plugin" to "com.google.dagger:hilt-android-gradle-plugin"
        )

        eachPlugin {
            idToModule[requested.id.id]?.let { module ->
                useModule("$module:${requested.version}")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

