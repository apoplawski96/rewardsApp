plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(files("libs/mockapi.jar"))

    implementation(Dependencies.Koin.koinAndroid)
    implementation(project(":feature:feature-rewards-api"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    val ktorVersion = "1.6.4"
    implementation ("io.ktor:ktor-client-core:$ktorVersion")

// HTTP engine: The HTTP client used to perform network requests.

    implementation ("io.ktor:ktor-client-android:$ktorVersion")

// The serialization engine used to convert objects to and from JSON.
    implementation ("io.ktor:ktor-client-serialization:$ktorVersion")

// Logging
    implementation ("io.ktor:ktor-client-logging:$ktorVersion")

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}