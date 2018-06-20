package extension.youtubeplayer;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#end

#if (android && openfl)
	#if (openfl < "4.0.0")
	import openfl.utils.JNI;
	#else
	import lime.system.JNI;
	#end
#end

#if ios
import extension.webview.WebView;
#end


class YouTubePlayer {

	private static var initialize:String->Void=null;
	public static var loadVideo:String->Bool->Void=function(s:String, b:Bool){};

	private static var YOUTUBEPLAYER_PATH = "org.haxe.extension.youtubeplayer.YoutubePlayer";
	private static var htmlString1:String = "<!DOCTYPE html>
	<html>
	<head>
	<style>
	body { margin: 0; width:100%; height:100%;  background-color:#000000; }
	html { width:100%; height:100%; background-color:#000000; }

	.embed-container iframe,
	.embed-container object,
	.embed-container embed {
	position: absolute;
	top: 0;
	left: 0;
	width: 100% !important;
	height: 100% !important;
	}
	</style>
	</head>
	<body>
	<div class=\"embed-container\">
	<div id=\"player\"></div>
	</div>
	<script src=\"https://www.youtube.com/iframe_api\" onerror=\"window.location.href='ytplayer://onYouTubeIframeAPIFailedToLoad'\"></script>
	<script>
	var player;
	var error = false;

	YT.ready(function() {
	    player = new YT.Player('player', {
	  \"playerVars\" : {
	    \"showinfo\" : 0,
	    \"autohide\" : 1,
	    \"controls\" : 0,
	    \"modestbranding\" : 1,
	    \"playsinline\" : 0
	  },
	  \"width\" : \"100%\",
	  \"videoId\" : \"";

	private static var htmlString2 = "\",
	  \"events\" : {
	    \"onPlaybackQualityChange\" : \"onPlaybackQualityChange\",
	    \"onStateChange\" : \"onStateChange\",
	    \"onError\" : \"onPlayerError\",
	    \"onReady\" : \"onReady\"
	  },
	  \"height\" : \"100%\"
	});
	    player.setSize(window.innerWidth, window.innerHeight);
	    window.location.href = 'ytplayer://onYouTubeIframeAPIReady';
	    
	    // this will transmit playTime frequently while playng
	    function getCurrentTime() {
	        var state = player.getPlayerState();
	        if (state == YT.PlayerState.PLAYING) {
	            time = player.getCurrentTime();
	            window.location.href = 'ytplayer://onPlayTime?data=' + time;
	        }
	    }
	    
	    window.setInterval(getCurrentTime, 500);
	    
	});

	function onReady(event) {
	    window.location.href = 'ytplayer://onReady?data=' + event.data;
	}

	function onStateChange(event) {
	    if (!error) {
	        window.location.href = 'ytplayer://onStateChange?data=' + event.data;
	    }
	    else {
	        error = false;
	    }
	}

	function onPlaybackQualityChange(event) {
	    window.location.href = 'ytplayer://onPlaybackQualityChange?data=' + event.data;
	}

	function onPlayerError(event) {
	    if (event.data == 100) {
	        error = true;
	    }
	    window.location.href = 'ytplayer://onError?data=' + event.data;
	}

	window.onresize = function() {
	    player.setSize(window.innerWidth, window.innerHeight);
	}
	</script>
	</body>
	</html>";

	static public function init(developerKey:String){
		#if android
		try{
			initialize = JNI.createStaticMethod (YOUTUBEPLAYER_PATH, "initialize", "(Ljava/lang/String;)V");
			loadVideo = JNI.createStaticMethod (YOUTUBEPLAYER_PATH, "loadVideo", "(Ljava/lang/String;Z)V");
			initialize(developerKey);
		} catch(error:Dynamic){
			trace("error: "+Std.string(error));
		}
		#elseif ios
		try{
			WebView.onClose = onClose;
			WebView.onURLChanging = onURLChanging;
			loadVideo = loadVideoFromHtml;
		} catch(error:Dynamic){
			trace("error: "+Std.string(error));
		}
		#end	
	} 

	static function onClose(){
        trace("youTube player has been closed!");
    }

    static function onURLChanging(url:String){
        trace("YouTube player is about to open: "+url);
    }

    static function loadVideoFromHtml(videoID:String, fullscreen:Bool){
    	#if ios
    	WebView.openHtml(getHtml(videoID), true);
    	#end
    }

    static function getHtml(videoID:String):String{
    	return htmlString1+videoID+htmlString2;
    }
}
