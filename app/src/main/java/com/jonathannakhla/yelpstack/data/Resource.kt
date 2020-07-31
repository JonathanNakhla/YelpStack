package com.jonathannakhla.yelpstack.data

// A generic class that contains data and status about loading this data.
// From: https://developer.android.com/jetpack/guide#addendum
// and https://github.com/android/architecture-components-samples/blob/88747993139224a4bb6dbe985adf652d557de621/GithubBrowserSample/app/src/main/java/com/android/example/github/vo/Resource.kt
sealed class Resource<T>(
    val data: T? = null,
    val message: String = ""
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}