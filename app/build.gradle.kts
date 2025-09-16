plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.9.20-1.0.13" // Use a version matching your Kotlin
}

android {
    namespace = "com.example.a226complete"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.a226complete"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v293)
    implementation(libs.kotlinx.coroutines.core) // Replace with the latest stable version
    implementation(libs.kotlinx.coroutines.android) // Replace with the latest stable version
    implementation(libs.androidx.lifecycle.livedata.ktx) // Replace with the latest stable version
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v280) // Replace with the latest stable version

    //room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.junit.ktx)
//    kapt(libs.androidx.room.compiler)
    ksp(libs.androidx.room.compiler) // <--- change from kapt to ksp

    implementation(libs.kotlinx.metadata.jvm)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.robolectric)

    // location
    implementation(libs.play.services.location)
    implementation(libs.play.services.maps)
    implementation(libs.okhttp)

    //material
    implementation(libs.material.v1120)

    implementation(libs.androidx.recyclerview)

    implementation(libs.androidx.cardview)
}