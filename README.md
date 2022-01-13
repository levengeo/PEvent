## usage
PEvent is this cross process communication tools in Android

gradle
implementation 'com.github.wengeo:PEvent:v1.0.0'

// Server
@MainThread
@Route("/show/age")
public void showAge(final Bundle in, Bundle out) {
    out.putString("age", "10");
    String name = in.getString("age");
    tv.setText(name);
}

// publish
ServiceManager.getInstance().publish(this);

// Client
PEvent pEvent = PEvent.newBuilder(MainActivity.this).setAuthority("com.pevent.example").build();
Bundle bundle = pEvent.route("/show/age").withString("age", "20").post();

## proguard
-keep class com.pevent.library.*
-dontwarn com.pevent.library.*
