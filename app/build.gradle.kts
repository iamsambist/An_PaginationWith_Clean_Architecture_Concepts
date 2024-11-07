plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.sunaa.pagingwithcache"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sunaa.pagingwithcache"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Room
    val  room_version = "2.6.1"
    implementation ("androidx.room:room-ktx:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-paging:$room_version")

    // Retrofit Implementation
    val recov = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$recov")
    implementation("com.squareup.retrofit2:converter-gson:$recov")

    // Hilt Implementation
    val hiltv = "2.52"
    implementation ("com.google.dagger:hilt-android:$hiltv")
    kapt ("com.google.dagger:hilt-compiler:$hiltv")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // pagination dependency for compose
    implementation("androidx.paging:paging-compose:3.3.2")

    // swipe refresh library
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.24.13-rc")

    // Flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}