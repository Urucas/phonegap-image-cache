try {
	var icache = {
		config : function(days){
			cordova.exec(function(){}, function(){}, "ImageCachePlugin", "config", [{"duration": days}]);
		};
		clear : function(){
			cordova.exec(function(){}, function(){}, "ImageCachePlugin", "clear", []);
		}
	}
	module.exports = icache;

}catch(e){
	console.log("Error creating com.urucas.plugins.ImageCachePlugin");
}
