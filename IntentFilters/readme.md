This app is just a demo application and there is no operation performed inside the app. 

The app manifest file has an Activity `CustomIntentActivity` and it looks like below

```
<activity android:name=".CustomIntentActivity">
            <intent-filter>
                <!-- When this action is used to open any activity then this activity will be opened -->
                <action android:name="com.xyz.intent.action" />
                <category android:name="android.intent.category.DEFAULT" />
            </intent-filter>
        </activity>
 ```
 
 When you run the `OpenFilterActivity` app (install the app from repo) and click the button inside the app , this `CustomIntentActivity` will open because of the same intent action
