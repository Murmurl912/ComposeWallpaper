package dependency

object Dependencies {

    const val Moshi = "com.squareup.moshi:moshi:${Versions.Mosi}"
    const val MoshiAdapters = "com.squareup.moshi:moshi-adapters:${Versions.Mosi}"
    const val MoshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Mosi}"

    const val Retrofit = "com.squareup.retrofit2:retrofit:${Versions.Retrofit}"
    const val RetrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.Retrofit}"

    const val AndroidxCoreKtx = "androidx.core:core-ktx:${Versions.AndroidXCoreKtx}"
    const val LifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LifecycleRuntimeKtx}"
    const val ActivityCompose = "androidx.activity:activity-compose:${Versions.ActivityCompose}"
    const val ComposeBom = "androidx.compose:compose-bom:${Versions.ComposeBom}"
    const val ComposeUi = "androidx.compose.ui:ui"
    const val ComposeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val ComposeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val ComposeMaterial3 = "androidx.compose.material3:material3"
    const val ComposeMaterialIconExtended = "androidx.compose.material:material-icons-extended"

    const val ComposeUiTooling = "androidx.compose.ui:ui-tooling"
    const val ComposeUiTestManifest = "androidx.compose.ui:ui-test-manifest"

    const val Junit = "junit:junit:4.13.2"
    const val AndroidxTestExtJunit = "androidx.test.ext:junit:1.1.5"
    const val AndroidTestEspressoCore = "androidx.test.espresso:espresso-core:3.5.1"
    const val AndroidxComposeUiTestJunit = "androidx.compose.ui:ui-test-junit4"

    const val HiltAndroid = "com.google.dagger:hilt-android:${Versions.HlitAndroid}"
    const val HiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.HiltAndroidCompiler}"
    const val HiltComposeNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.HiltComposeNavigation}"
    const val HlitAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.HlitAndroid}"

    const val ColiCompose = "io.coil-kt:coil-compose:${Versions.KoltinCompose}"

    const val AndroidxPaging3RuntimeKtx = "androidx.paging:paging-runtime-ktx:${Versions.AndroidXPaging3}"
    const val AndroidxPaging3Compose = "androidx.paging:paging-compose:${Versions.AndroidXPaging3}"
}