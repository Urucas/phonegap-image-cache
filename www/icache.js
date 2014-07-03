try{
    var icache = {
    
	  	load: function(){
      	cordova.exec( success, error, "ImageCachePlugin", "load", [] );
      }
    }
    module.exports = icache;

}catch(e){
	console.log("icache error -->  "+e.message);
}

