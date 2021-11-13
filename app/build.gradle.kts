plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
}

android {
    compileSdk = buildConfigVersions.compileSdkVersion

    defaultConfig {
        applicationId ="com.avmogame.appcent"
        minSdk = buildConfigVersions.minSdkVersion
        targetSdk = buildConfigVersions.targetSdkVersion
        versionCode = buildConfigVersions.versionCode
        versionName = buildConfigVersions.versionName

        testInstrumentationRunner= ("androidx.test.runner.AndroidJUnitRunner")

    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
    sourceSets {
        getByName("main") {
            assets {
                srcDirs("src/main/assets")
            }
        }
        getByName("test"){
            resources {
                srcDir("src/test/assets")
            }
        }
    }
}

dependencies {

    implementation(androidxsupportDependencies.core_ktx)
    implementation(androidxsupportDependencies.appcompat)
    implementation(androidxsupportDependencies.material)
    implementation(androidxsupportDependencies.constraintlayout)
    //Lifecycle
    implementation(androidxsupportDependencies.lifecycle_viewmodel)
    implementation(androidxsupportDependencies.lifecycle_runtime)
    //Kotlin components
    implementation(kotlinDependencies.kotlin)
    implementation(kotlinDependencies.coroutines_core)
    implementation(kotlinDependencies.coroutines)
    //testImplementation(kotlinDependencies.coroutines_test)
    //retrofit
    implementation(retrofitDependencies.retrofit2)
    implementation(retrofitDependencies.converter_gson)
    implementation(retrofitDependencies.adapter_rxjava)
    implementation(retrofitDependencies.logging_interceptor)
    //Dagger - Hilt
    implementation(hiltDependencies.hilt)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    kapt(hiltDependencies.hilt_compiler)
    //navigation comp
    implementation(androidxsupportDependencies.navigation_fragment)
    implementation(androidxsupportDependencies.navigation_ui)
    // Activity KTX for viewModels()
    implementation(androidxsupportDependencies.activity_ktx)
    //glide
    implementation(glideDependencies.glide)
    kapt(glideDependencies.glide_compiler)

    //Fb Shimmer Animation
    implementation(shimmerDependencies.shimmer)

    implementation(kotlinDependencies.coroutines_play_services)

    implementation(pagingDependencies.paging)



    kapt(roomDependencies.room_compiler)
    implementation(roomDependencies.room_ktx)

    //unittest
    testImplementation(testingDepencies.junit)
    testImplementation(testingDepencies.espresso)
    testImplementation (testingDepencies.mockito)
    testImplementation (testingDepencies.coroutines_test)
    testImplementation(testingDepencies.room_testing)
    testImplementation(testingDepencies.arc_core)
    testImplementation ("com.google.truth:truth:1.1.3")
    testImplementation ("app.cash.turbine:turbine:0.7.0")

    //instrumented unit test
    androidTestİmplementation(testingDepencies.junit)
    androidTestİmplementation(testingDepencies.junit_ext)
    androidTestİmplementation(testingDepencies.coroutines_test)
    androidTestİmplementation(testingDepencies.espresso)
    androidTestİmplementation(testingDepencies.arc_core)
    androidTestİmplementation("com.google.truth:truth:1.1.3")


}