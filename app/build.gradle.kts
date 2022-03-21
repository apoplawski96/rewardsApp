plugins {
    kotlin("android") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    id("com.android.application") version "7.1.3"
    id("dagger.hilt.android.plugin") version "2.39.1"
    id("de.mannodermaus.android-junit5") version "1.8.0.0"
}

val compose = "1.1.1"

android {

    compileSdk = 31

    defaultConfig {
        applicationId = "com.futuremind.loyaltyrewards"
        minSdk = 24
        targetSdk = 31
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

    val coroutines = "1.6.0"
    val accompanist = "0.23.1"
    val junit5 = "5.8.0"

    implementation(files("libs/mockapi.jar"))

    // UI
    implementation("androidx.compose.ui", "ui", compose)
    implementation("androidx.compose.ui", "ui-tooling", compose)
    implementation("androidx.compose.foundation", "foundation", compose)
    implementation("androidx.compose.material", "material", compose)
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1")
    implementation("com.google.accompanist:accompanist-insets:$accompanist")
    implementation("com.google.accompanist:accompanist-systemuicontroller:$accompanist")
    implementation("com.google.accompanist:accompanist-swiperefresh:$accompanist")
    implementation("io.coil-kt:coil-compose:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-compiler:2.41")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")
    testImplementation("io.kotest:kotest-assertions-core:5.2.2")
    testImplementation("app.cash.turbine:turbine:0.8.0")

}