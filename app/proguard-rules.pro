# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-ignorewarnings
# i wanted to keep onCreate in main activity. Because this is the first function that the app executes (with UI)
-keep class com.example.elika.myapplication999.MainActivity {
        native <methods>;
}
# i wanted to keep member userChioce in main activity, because it saves the choice of user in menu.
-keepclassmembers class com.example.elika.myapplication999.MainActivity {
    private java.lang.Integer userChioce;
}
