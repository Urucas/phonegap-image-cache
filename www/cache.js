try {
	var icache = (new function(){
		this.config = function(days){
			cordova.exec(function(){}, function(){}, "ImageCachePlugin", "config", ["duration": days]);
		};
		this.clear = function(){
			cordova.exec(function(){}, function(){}, "ImageCachePlugin", "clear", []);
		}
	});
	window.icache = window.icache || icache;

}catch(e){
	console.log("Error creating com.urucas.plugins.ImageCachePlugin");
}
