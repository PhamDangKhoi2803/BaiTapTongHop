plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services")
}

android {
    namespace = "vn.iotstar.bt10_ltmobile"
    compileSdk = 35

    defaultConfig {
        applicationId = "vn.iotstar.bt10_ltmobile"
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation ("com.google.android.gms:play-services-auth:20.4.1")
    /**firebase dependencies*/
    implementation ("com.google.firebase:firebase-auth:21.1.0")
    implementation ("com.google.firebase:firebase-database:20.1.0")
    implementation (platform("com.google.firebase:firebase-bom:31.2.3"))
    implementation ("com.google.firebase:firebase-database-ktx")
    implementation ("com.google.firebase:firebase-firestore:24.4.5")
    implementation ("com.google.firebase:firebase-storage:20.1.0")
    implementation ("com.firebaseui:firebase-ui-database:8.0.1")

    implementation("com.github.bumptech.glide:glide:4.14.2")
    implementation(libs.credentials)
    implementation(libs.credentials.play.services.auth)
    implementation(libs.googleid)
    annotationProcessor("com.github.bumptech.glide:compiler:4.14.2")

    // cloudinary
    implementation("com.cloudinary:cloudinary-android:3.0.2")

    implementation ("com.github.TutorialsAndroid:GButton:v1.0.19")
    implementation ("com.google.android.gms:play-services-auth:20.4.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}