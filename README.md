# Ziv


Implemented Function
Animation ,Custom Dialog Box,Cart entry,Right Swipe to Delete,After Placing Order Animation
architechture:
MVVM - Model-View-ViewModel
Tools:
Retrofit,Picasso

<p float="left">
  <img src="https://user-images.githubusercontent.com/52675676/179305133-2fc8fbc4-fe12-4e8b-b8df-e09dd456781d.png" width="200" height="400" />
  <img src="https://user-images.githubusercontent.com/52675676/179305141-5fb91aa0-7929-47ab-9361-e2040abc3c81.png" width="200" height="400" />
  <img src="https://user-images.githubusercontent.com/52675676/179305143-26bc7c71-3e85-4e7b-bb01-d1cb6db491c3.png" width="200" height="400" />
  <img src="https://user-images.githubusercontent.com/52675676/179305148-d3941be4-306e-43ab-969d-e690ec6b06a9.png" width="200" height="400" />
  <img src="https://user-images.githubusercontent.com/52675676/179305149-1e5de139-c963-497b-a24e-ee40f4b08871.png" width="200" height="400" />
  <img src="https://user-images.githubusercontent.com/52675676/179305150-5ee76662-a18d-41b5-9d5e-c6d27989d129.png" width="200" height="400" />
</p>


Dependency:

    implementation 'com.github.bumptech.glide:glide:4.13.0'
    // https://mvnrepository.com/artifact/androidx.room/room-runtime
    def room_version = "2.4.2"

    implementation "androidx.room:room-runtime:$room_version"
    annotationProcessor "androidx.room:room-compiler:$room_version"

    // optional - RxJava2 support for Room
    implementation "androidx.room:room-rxjava2:$room_version"

    // optional - RxJava3 support for Room
    implementation "androidx.room:room-rxjava3:$room_version"

    // optional - Guava support for Room, including Optional and ListenableFuture
    implementation "androidx.room:room-guava:$room_version"

    // optional - Test helpers
    testImplementation "androidx.room:room-testing:$room_version"


    // https://mvnrepository.com/artifact/com.airbnb.android/lottie
    implementation group: 'com.airbnb.android', name: 'lottie', version: '5.0.3'

    // optional - Paging 3 Integration
    implementation "androidx.room:room-paging:2.5.0-alpha01"

    implementation group: 'com.squareup.retrofit2', name: 'retrofit', version: '2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
    // https://mvnrepository.com/artifact/com.squareup.okhttp/okhttp
    implementation group: 'com.squareup.okhttp', name: 'okhttp', version: '2.7.5'
    // https://mvnrepository.com/artifact/com.squareup.okhttp3/logging-interceptor
    implementation group: 'com.squareup.okhttp3', name: 'logging-interceptor', version: '5.0.0-alpha.6'

    // https://mvnrepository.com/artifact/com.squareup.picasso/picasso
    implementation group: 'com.squareup.picasso', name: 'picasso', version: '2.71828'

    // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-android
    implementation "androidx.room:room-ktx:$room_version"

    implementation 'it.xabaras.android:recyclerview-swipedecorator:1.2.1'

    // https://mvnrepository.com/artifact/com.android.support.test/rules

    androidTestImplementation 'androidx.test:rules:1.1.1'
