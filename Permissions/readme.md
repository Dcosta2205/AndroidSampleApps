This app demonstrates the runtime/dangerous permissions

The app has 2 activities 
1. `MainActivity`
2. `RequestMultiplePermissionsActivity`

`MainActivity` there are 2 buttons i.e contacts permission and location permission button, on click of which respective permissions are displayed. 
The result of the permission is handled in **onRequestPermissionsResult()**

`RequestMultiplePermissionsActivity` has one button on click of which multiple permissions are requested i.e contacts and storage permission and the result of both the permission is handled 
in **onRequestPermissionsResult()**.

Make sure you declare the permission in the manifest file 

```
<uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
```
