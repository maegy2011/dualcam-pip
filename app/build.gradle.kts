plugins { id("com.android.application") }
android {
    namespace = "com.example.dualcampip"
    compileSdk = 34
    defaultConfig { applicationId = "com.example.dualcampip"; minSdk = 24; targetSdk = 34; versionCode = 1; versionName = "1.0" }
}
dependencies {
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.camera:camera-core:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")
}
