package extension.youtubeplayer;

#if cpp
import cpp.Lib;
#elseif neko
import neko.Lib;
#end

#if (android && openfl)
import openfl.utils.JNI;
#end


class YouTubePlayer {
	private static var initialize:String->Void=null;
	public static var loadVideo:String->Bool->Void=function(s:String, b:Bool){};
	private static var YOUTUBEPLAYER_PATH = "org.haxe.extension.youtubeplayer.YoutubePlayer";

	static public function init(developerKey:String){
		#if android
		try{
			initialize = JNI.createStaticMethod (YOUTUBEPLAYER_PATH, "initialize", "(Ljava/lang/String;)V");
			trace("paso initialize");
			loadVideo = JNI.createStaticMethod (YOUTUBEPLAYER_PATH, "loadVideo", "(Ljava/lang/String;Z)V");
			initialize(developerKey);
		} catch(error:Dynamic){
			trace("error: "+Std.string(error));
		}
		#end	
	} 
}