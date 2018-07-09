# NotificationBanner
An easy to use library to show a notification banner. This is useful for cases in apps where the user is already in your app so using a system notifications could be confusing. Instead use this library to show a notification banner which is themed to your UI and can have custom behavior based on what part of the app they are in.

<img src="/sample1.png" width="32%"> <img src="/sample2.png" width="32%">

# Features

* Simple API to show a notification banner over other app content
* Convenient gestures like clicking and swiping away
* Advanced features to expand on Notification Banner functionality

# How Do I Use It?

## Setup

### Gradle

On your module's `build.gradle` file add this statement to the `dependencies` section:

```groovy
dependencies {
    implementation 'com.nick.mowen.notificationbanner:notificationbanner:1.0.2'
}
```

Also make sure that the `repositories` section includes both jcenter and `maven` section with the `"google()"` endpoint. 

```groovy
repositories {
    jcenter()
    google()
}
```

# Notification Banner

### Add to your layout

NOTE: It does not matter which type of parent the banner has, but for it to work right the banner must hover over the app content

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    ...

    <com.nick.mowen.notificationbanner.view.NotificationBanner
        android:id="@+id/banner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</Framelayout>
```

### Create And Setup The View

```kotlin
val banner: NotificationBanner = findViewById(R.id.banner)
...
banner.notify(BannerInfo(bitmap, string, string) //Use this to show content immediately
...
banner.info = BannerInfo(bitmap, string, string) //Use this if you want to set data and notify later
banner.notify()
```

### Customize The View

```kotlin
//Use the click listener to react to when the user clicks on the banner
banner.listener = object : BannerClickListener {
    override fun onClick(view: View, info: BannerInfo) {
                
    }
}
```

### Advanced Usage

You can set the banner to not hide when being clicked to enable the ability to show custom animations
```kotlin
banner.hideAfterClick = false
```

If you need more information when the banner is clicked, you can create your own BannerInfo

```kotlin
class NewBannerInfo (threadId: String, bitmap: Bitmap, title: String, text: String) : BannerInfo(bitmap, title, text)
```
Then simply submit that new class on notify
```kotlin
banner.notify(NewBannerInfo(string, bitmap, string, string))
```
Then in the onClick cast the info to your class
```kotlin
banner.listener = object : BannerClickListener {
    override fun onClick(view: View, info: BannerInfo) {
        val newInfo = info as NewBannerInfo
        //Do stuff with newInfo.threadId
    }
}
```

Applications using NotificationBanner
---
Icon | Application
------------ | -------------
<img src="https://github.com/NickM-27/Texpert/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png" width="48" height="48" /> | [Texpert](https://play.google.com/store/apps/details?id=com.nick.mowen.texpert)

Please [email](mailto:nick@nicknackdevelopment.com) me or send a pull request if you would like to be added here.

Developed By
---
Nick Mowen - <nick@nicknackdevelopment.com>

Contributions
-------

Any contributions are welcome!

License
---

    Copyright 2018 Nick Nack Developments

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
