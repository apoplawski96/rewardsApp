plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("de.mannodermaus.android-junit5") version "1.8.0.0"
}

val compose = "1.3.0-rc01"

android {

    compileSdk = 33

    defaultConfig {
        applicationId = "com.futuremind.loyaltyrewards"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions { jvmTarget = "1.8" }
    composeOptions { kotlinCompilerExtensionVersion = compose }

    buildFeatures {
        compose = true
    }

}

dependencies {

    val coroutines = "1.6.4"
    val accompanist = "0.26.5-rc"
    val junit5 = "5.8.0"

    implementation(files("libs/mockapi.jar"))

    // UI
    implementation("androidx.compose.ui", "ui", compose)
    implementation("androidx.compose.ui", "ui-tooling", compose)
    implementation("androidx.compose.foundation", "foundation", compose)
    implementation("androidx.compose.material", "material", compose)
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("com.google.accompanist:accompanist-insets:$accompanist")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist")
    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation(Dependencies.Koin.koinAndroid)
    implementation(Dependencies.Koin.koinCore)
    implementation(Dependencies.Koin.koinCompose)

    implementation(project(":common:common-util"))
    implementation(project(":common:common-ui"))

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5")
    testImplementation("io.mockk:mockk:1.12.7")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")
    testImplementation("io.kotest:kotest-assertions-core:5.4.2")
    testImplementation("app.cash.turbine:turbine:0.12.0")

}