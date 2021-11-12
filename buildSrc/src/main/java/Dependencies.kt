
object Versions {
    const val lifecycle_version = "2.3.1"
    const val coroutines_version = "1.5.2"
    const val kotlin_version = "1.5.30"
    const val hilt_version = "2.38.1"
    const val activity_ktx_version = "1.3.1"
    const val arc_core_version = "2.1.0"

    const val retrofit2_version = "2.9.0"
    const val okhttp_logging_interceptor_version = "4.9.0"

    const val glide_version = "4.12.0"

    const val android_gradle_plugin_version = "7.0.2"

    const val navigation_comp_version = "2.3.5"

    const val appcompat_version = "1.3.1"
    const val constraint_layout_version = "2.1.0"
    const val preference_ktx_version = "1.1.1"
    const val material_design_version = "1.4.0"

    const val core_ktx_version = "1.6.0"

    const val multidex_version = "1.0.3"

    const val junit_version = "4.13"
    const val ext_junit_version = "1.1.3"
    const val espresso_version = "3.4.0"
    const val mockito_version = "3.2.0"

    const val coroutines_play_services_version = "1.2.1"

    const val facebook_shimmer_version = "0.4.0"

    const val paging_version = "3.0.1"

    const val room_version = "2.3.0"

}

object buildConfigVersions{
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30
    const val versionCode = 1
    const val versionName = "1.0"
}
object BuildPluginsDependencies{
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.android_gradle_plugin_version}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}"
    const val navigationGradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation_comp_version}"
}
object androidxsupportDependencies {
    //const val legacy_support = "androidx.legacy:legacy-support-v4:${Versions.legacy_support_version}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat_version}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout_version}"
    const val preference = "androidx.preference:preference-ktx:${Versions.preference_ktx_version}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
    const val lifecycle_runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle_version}"
    const val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_ktx_version}"
    const val material = "com.google.android.material:material:${Versions.material_design_version}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_comp_version}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_comp_version}"
    const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
    const val multidex = "com.android.support:multidex:${Versions.multidex_version}"
}

object kotlinDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines_version}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    const val coroutines_play_services =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutines_play_services_version}"



}

object retrofitDependencies {
    const  val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2_version}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2_version}"
    const val adapter_rxjava = "com.squareup.retrofit2:adapter-rxjava:${Versions.retrofit2_version}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_logging_interceptor_version}"
}

object glideDependencies {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
}

object testingDepencies {
    const val junit = "junit:junit:${Versions.junit_version}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.ext_junit_version}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso_version}"
    const val mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito_version}"
    const val room_testing = "androidx.room:room-testing:${Versions.room_version}"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines_version}"
    const val arc_core = "androidx.arch.core:core-testing:${Versions.arc_core_version}"
}
object hiltDependencies {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"
}
object shimmerDependencies {
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.facebook_shimmer_version}"
}

object pagingDependencies{
    const val paging = "androidx.paging:paging-runtime:${Versions.paging_version}"
}

object roomDependencies {
    const val room_ktx = "androidx.room:room-ktx:${Versions.room_version}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room_version}"

}

