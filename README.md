# Yelp Restaurant Stack: Fully built in Kotlin using MVVM architecture and RxJava
This app was built using the best practices as described in Google's ["Guide to app architecture"](https://developer.android.com/jetpack/guide#best-practices). There is a **clear separation of concerns** for each of the different layers with business logic living in the ViewModel and a repository that's backed by a remote data source (via Retrofit) and a very basic local data source (a list in memory). This separation also helps with writing tests and being able to mock certain layers. The **UI is driven by the repository** that feeds the ViewModel/UI with `Resource` objects (as seen [here](https://developer.android.com/jetpack/guide#addendum)) that essentially encapsulate the data and the status (Success, Loading, or Error).

## Libraries Used
* **RxJava** for reactive programming and threading
* **Retrofit** for the network layer
* **Kotlinx** Serialization library for JSON deserialization
* **Glide** for image loading
* **Koin** as a basic service location
* **LeakCanary** was used along the way for sanity checks

## Key spec requirements and how they were addressed

* Location API to get user's current location for Yelp API
  * **FusedLocationProviderClient** is used to grab the user's current location
  * It's wrapped around a layer of abstraction in cause of future API changes as Android's location APIs tend to change pretty often
  * The user is greeted with the Android system's permission dialog when the app is opened
  * The `FusedLocationProviderClient`'s result is wrapped around an Rx Single and is then emitted in a chain that triggers the API call
* Main stack of cards view
  * This was the least trivial part of the assignment as Android doesn't provide a stack of cards out of the box
  * A lot of thought and investigation was put into figuring out the best approach before touching any code
  * In the end, **`ViewPager2`** worked best!
    * It's backed by a `RecyclerView` which provides great memory usage thanks to it constantly recycling view holders (esp when compared to the first ViewPager)
    * It allowed me to use a `RecyclerView Adapter` in which I could set up my own pagination logic using two different view holders
    * It has support for `PageTransformer` which gives fine control over page scroll animations
    * It's usually designed as a "one item at a time" view which worked perfectly with the `Previous` and `Next` buttons
* Infinite Scrolling
  * To implement infinite scrolling, I used a native RecyclerView Adapter approach, where as we got closer to the end of the list, an API call would be triggered which would then update the adapter.
  * A ProgressBar is displayed at the end of the list in case the user is in a place with slow data speeds
  * If given more time, I would've opted for Android's `PagedList` library which provides a pretty powerful pagination implementation, where items are paged in AND paged out of memory
  * The problem with my current design is that eventually the memory would fill up with a lot of data
* Implementation works in landscape mode
  * Ensured that landscape mode
    * Looks good and usable (i.e. scales across different Android screens and sizes)
    * And more importantly, that no redundant API calls are being made as a result of rotation. This is in part thanks to the use of RxJava's `BehaviorSubject` which "emits the most recently emitted item and all the subsequent items of the source Observable when an observer subscribes to it"
    * Speaking of RxJava, ensured that subscriptions were being disposed/cleared of when no longer needed to ensure that no memory leaks happen (and double check with Leak Canary also)

## Answering questions from write up
* **How long did it take you? Most difficult part?**

This problem took longer than 3 hours. It was important for me to not cut corners and to fully demonstrate what I am capable of. The least trivial part of this would definitely have to be the UI component as Android did not provide anything like this natively out of the box. At the same time though, I did enjoy looking into all of my options and finding the best View for this use case that check marked all the requirements that I was looking for (animation, decent memory use, scroll one item at a time, and pagination)

* **Trade-offs? What did you spend time on? What did you ignore?**

One of the biggest trade-offs I had to make was no implementing an actual local repository. If I did have time, I definitely would have used Android's Room library to implement a local database along with a `NetworkBoundResource` as Google describes [here](https://developer.android.com/jetpack/guide) to ensure that the **single source of truth principle** isn't being broken.

Another trade-off was not using the PagedList library for pagination and implementing my own pagination. The current approach doesn't scale well once a lot of items are loaded into memory (though the API only provides a max of 1000 items each of which is a handful of Strings). The PagedList library is excellent in regards to memory usage as it loads items out of memory as it loads new items in.

I did enjoy working on the UI component and making it as presentable as possible. It was also fun creating the layer of abstraction around the Location API, feeding that into the Yelp API, and then seeing the personal API results!

* **If given extra time, what improvements would I have done?**

I believe I answered that with the previous answer while discussing trade offs. Thanks for reading this far :)!