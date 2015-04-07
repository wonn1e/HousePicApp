# HousePicApp
Final App called "HousePic" released in Android Appstore on September, 2013.

.:: Introduction ::.
This android application is targeted at those who wish to buy furniture. The users cannot buy the items using this app but,
the app connects to each webpage where they can actually buy those.
Not only introducing the items, users get to have their "House" and they can make their own "rooms" and select the items they
wish to keep in those rooms. 

.:: Settings ::.
In order to run the program, you need to download several libraries.
1) google play services
  You should download this library and set it up. 
2) FacebookSDK 
  Download Facebook APK from developer.facebook.com.
3) sdk
  This SDK is KakaoTalk APK and you should be able to download it from developer.kakao.com
4) Other Libraries
  The libraries listed underneath are uploaded as repositories. Please download them for the app to work perfectly.
  -AndroidHorizontalListView
  -viewpagerindicatorlibrary
  -slidingmenuSupportv7library
  -AndroidStaggeredGridLibrary
  -StickyGridHeaderLibrary
  -AndroidAysncHttpLibrary
  -HorizontalVariableListView
  
Once you download all the libraries and APKs that are referenced, you should include them to HousePic project 'Properties -> java build path'
If some warning shows, you should copy appcompat_v7 library's(that has been created once you create any kind of Android project
in Eclipes) appcompat_v7.jar file in bin folder and paste/replace appcompat_v7.jar file in the referenced APKs. If there isn't 
any appcompat_v7.jar file to begin with, you can ignore that library or APK.
  
