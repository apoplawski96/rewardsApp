plugins {
    kotlin("android") version "1.6.10"
    kotlin("kapt") version "1.6.10"
    id("com.android.application") version "7.1.3"
    id("dagger.hilt.android.plugin") version "2.41"
    id("de.mannodermaus.android-junit5") version "1.8.0.0"
}

android {

    compileSdk = 31

    defaultConfig {
        applicationId = "com.futuremind.loyaltyrewards"
        minSdk = 26
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions { jvmTarget = "1.8" }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    val coroutines = "1.6.0"
    val junit5 = "5.8.0"

    implementation(files("libs/mockapi.jar"))

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.activity:activity-ktx:1.4.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.6.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines")

    implementation("com.google.dagger:hilt-android:2.41")
    kapt("com.google.dagger:hilt-compiler:2.41")

    implementation("io.coil-kt:coil:2.0.0-rc03")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junit5")
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines")
    testImplementation("io.kotest:kotest-assertions-core:5.2.2")
    testImplementation("app.cash.turbine:turbine:0.8.0")

}

kapt {
    correctErrorTypes = true
}