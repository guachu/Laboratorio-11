clienteRest=(function(){

	return {
		getCinemaByName:function(name,callback){
			$.getJSON( "/cinema/"+name, function( data ) {				
				callback(
						data
				);				
			});

		}
	}
	

})();