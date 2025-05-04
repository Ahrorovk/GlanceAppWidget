plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.ksp)
}
android {
    namespace = "com.ahrorovk.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.ahrorovk.myapplication"
        minSdk = 24
        targetSdk = 35
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    //Hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.9.0"))

    // DatePicker
//    implementation(libs.core)
//    implementation(libs.calendar)
//    implementation(libs.clock)
//    //noinspection UseTomlInstead
//    implementation("com.maxkeppeler.sheets-compose-dialogs:state:1.0.2")

    //Room
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    implementation(libs.room.paging)
    //Retrofit
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    implementation(libs.androidx.datastore.preferences)

    // For AppWidgets support
    implementation (libs.androidx.glance.appwidget)
    //noinspection UseTomlInstead
    debugImplementation("com.google.android.glance.tools:appwidget-viewer:0.2.2")
    // For interop APIs with Material 3
    implementation (libs.androidx.glance.material3)
    implementation (libs.androidx.glance)
    // For interop APIs with Material 2
    implementation (libs.androidx.glance.material)

    //noinspection GradleDependency
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}