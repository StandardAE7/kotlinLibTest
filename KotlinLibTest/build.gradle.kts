plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

group = "com.example.kotlinlibtest"
version = "1.0.0"

android {
    namespace = "com.example.kotlinlibtest"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("gpr") {
                run {
                    groupId = "com.example"
                    artifactId = "KotlinLibTest"
                    version = "1.0.0"
                    artifact("$buildDir/outputs/aar/KotlinLibTest-debug.aar")
                }
            }
        }

        repositories {
            maven {
                name = "GitHubPackages"
                uri("https://github.com/StandardAE7/kotlinLibTest") // Replace with your repository URL
                credentials {
                    username = "StandardAE7"
                    password = "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"
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
