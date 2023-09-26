import dependency.SdkVersion
import dependency.Dependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.composewallpaper"
    compileSdk = SdkVersion.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.composewallpaper"
        minSdk = SdkVersion.ANDROID_MIN_SDK
        targetSdk = SdkVersion.TARGET_SDK
        versionCode = SdkVersion.APP_VERSION_CODE
        versionName = SdkVersion.APP_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = SdkVersion.COMPOSE_COMPILER_EXTENSION
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
kotlin {
    jvmToolchain(SdkVersion.KOTLIN_JVM_TOOLCHAIN)
}
dependencies {

    implementation(Dependencies.Moshi)
    implementation(Dependencies.MoshiAdapters)
    kapt(Dependencies.MoshiCodeGen)

    implementation(Dependencies.Retrofit)
    implementation(Dependencies.RetrofitMoshiConverter)

    implementation(Dependencies.AndroidxCoreKtx)
    implementation(Dependencies.LifecycleRuntimeKtx)
    implementation(Dependencies.ActivityCompose)
    implementation(platform(Dependencies.ComposeBom))
    implementation(Dependencies.ComposeUi)
    implementation(Dependencies.ComposeUiGraphics)
    implementation(Dependencies.ComposeUiToolingPreview)
    implementation(Dependencies.ComposeMaterial3)
    implementation(Dependencies.ComposeMaterialIconExtended)

    testImplementation(Dependencies.Junit)
    androidTestImplementation(Dependencies.AndroidxTestExtJunit)
    androidTestImplementation(Dependencies.AndroidTestEspressoCore)
    androidTestImplementation(platform(Dependencies.ComposeBom))
    androidTestImplementation(Dependencies.AndroidxComposeUiTestJunit)
    debugImplementation(Dependencies.ComposeUiTooling)
    debugImplementation(Dependencies.ComposeUiTestManifest)
}