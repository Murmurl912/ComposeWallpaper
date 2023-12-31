import dependency.SdkVersion
import dependency.Dependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.unsplash"
    compileSdk = SdkVersion.COMPILE_SDK

    defaultConfig {
        minSdk = SdkVersion.ANDROID_MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = SdkVersion.SOURCE_JAVA_VERSION
        targetCompatibility = SdkVersion.TARGET_JAVA_VERSION
    }
    kotlinOptions {
        jvmTarget = SdkVersion.KOTLIN_JVM_TARGET
    }
}

dependencies {


    implementation(Dependencies.Moshi)
    implementation(Dependencies.MoshiAdapters)
    kapt(Dependencies.MoshiCodeGen)
    implementation(Dependencies.Retrofit)
    implementation(Dependencies.RetrofitMoshiConverter)

    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    testImplementation(Dependencies.Junit)
}