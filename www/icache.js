try{
    var icache = {
    
	  	load: function(){
      	cordova.exec(null, null, "ImageCachePlugin", "load", []);
      }
    }
    module.exports = icache;

}catch(e){
	console.log("icache error -->  "+e.message);
}

