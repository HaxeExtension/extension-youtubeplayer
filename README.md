# extension-youtubeplayer

Native extension for in-app youtube videos on Android.


###Main Features

* Play YouTube Videos.

###Simple use Example

```haxe
// This example show a simple use case.

import extension.youtubeplayer.YouTubePlayer;

class MainClass {

	function new() {
		// first of all... call init on the main method.
		YouTubePlayer.init("BIcaSyDFTNiRQz56Wn146Mud6DwmaYNds2tG000"); //Google app developer Key
	}
	
	function loadVideo() {
		// The string parameter is the youtube video ID to play.
		// The boolean parameter forces the fullscreen mode and hide the player fullscreen button.
		YouTubePlayer.loadVideo("UbQgXeY_zi4", true);
	}	
}

```

###How to Install

To install this library, you can simply get the library from haxelib like this:
```bash
haxelib install extension-youtubeplayer
```

Once this is done, you just need to add this to your project.xml
```xml
<haxelib name="extension-youtubeplayer" />
```

###Disclaimer

YouTube is a registered trademark of Google Inc.
http://es.unibrander.com/estados-unidos/175497US/youtube.html

###License

The MIT License (MIT) - [LICENSE.md](LICENSE.md)

Copyright &copy; 2016 SempaiGames (http://www.sempaigames.com)

Author: Joaquin Bengochea
