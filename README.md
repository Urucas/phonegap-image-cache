phonegap-image-cache
====================

Cordova plugin to cache remote images in the local SD. For Android only and still BETA!!!

Install
=======
```bash
cordova plugin add https://github.com/Urucas/phonegap-image-cache.git
```

Usage
=====
As soon as the plugin it's installed it's fully working. The plugin override the url images request and download and stores them into the local SD for 5 days. 

You can change the days using:
```javascript
window.icache.config(2); // 2 days cache
```

TODO
====

* Test on android 2.3 and less
* Add clear method, to manually clear the app cache.
* Add preloading image
* Add default image
* Create the iOS plugin
