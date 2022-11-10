plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.dokka)
    kotlin("kapt")
    alias(libs.plugins.ktLint)
}

android {
    namespace = "com.yunusbedir.playground"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.yunusbedir.playground"
        minSdk = 24
        targetSdk = 33

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

    flavorDimensions += listOf("App")
    productFlavors {
        create("Qa") {
            dimension = "App"
            versionCode = 1
            versionName = "1.0.0"
            buildConfigField("String", "MOCK_API_BASE_URL", "\"https://6347150504a6d45757a023b2.mockapi.io/\"")
        }
        create("Prod") {
            dimension = "App"
            versionCode = 1
            versionName = "1.0.0"
            buildConfigField("String", "MOCK_API_BASE_URL", "\"https://6347150504a6d45757a023b2.mockapi.io/\"")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {

    implementation(project(":base"))
    implementation(project(":services"))
    implementation(project(":widget"))

    implementation(libs.bundles.common)
    kapt(libs.bundles.commonKapt)
    testImplementation(libs.bundles.commonTest)

    /** ANDROIDX **/
    implementation(libs.fragmentKtx)
    implementation(libs.activityKtx)

    /** SPLASH **/
    implementation(libs.splashScreen)

    /** RETROFIT **/
    implementation(libs.retrofit)
    implementation(libs.retrofitMoshi)
    implementation(libs.loggingInterceptor)

    /** HILT **/
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)

    /** COROUTINES **/
    implementation(libs.kotlinCoroutines)
}