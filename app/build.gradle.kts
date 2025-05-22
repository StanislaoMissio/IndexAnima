plugins {
    alias(libs.plugins.android.plugin)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.google.services)
}

android {
    namespace = "com.desafio.animeapi"
    compileSdk = 35

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }

    defaultConfig {
        applicationId = "com.desafio.animeapi"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"https://kitsu.io/api/edge/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    //Retrofit / Okhttp
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    testImplementation(libs.okhttp.mockwebserver)

    //Compose
    //Downloading the compose Bom I don't need to set the version for every compose implementation
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.activity)
    implementation(libs.compose.lifecycle)
    implementation(libs.compose.ui)
    implementation(libs.compose.nav)

    //Setting up the tool to preview compose classes in android studio
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    //Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.compose)

    //Firebase
    //Downloading the firebase bom I don't need to set the version for every firebase implementation
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.auth)
    implementation(libs.firebase.firestore)
    implementation(libs.google.playservices.auth)

    //SplashScreen Api
    implementation(libs.splashscreen.api)

    //Coil
    implementation(libs.coil)

    implementation(libs.test.junit)

}