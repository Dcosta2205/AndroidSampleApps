Webview Sample
==============

This application loads a web view inside the `MainActivity` and loads a url `https://www.google.com`.

Add the below code into the manifest file

```
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.xyz.webview">

    <!-- Providing internet permission is mandatory here as we need to load a web page -->
    <uses-permission android:name="android.permission.INTERNET" />

    <application
     .
     .
     .
     .
     .
    </application>

</manifest>
```

**Note**

Make sure your device is connected to the internet. Try to run it on a physical device
