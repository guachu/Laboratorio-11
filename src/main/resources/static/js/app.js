var c=(function(){
	function crear(){
		var cinema = document.getElementById("cinema").value;
		var tabla= $('#Cine');
		var html;
		
		CinemaByname =function(cine){
			 var tabla2=$('#funcion');
			 html="";
			 tabla2.empty();
			 if(cine!=null){
					var funciones=cine[0].functions;				
					tabla2.append(html);
					for(var i =0;i<funciones.length;i++){
						var asientos=funciones[i].seats.length*funciones[i].seats[0].length;
						html='<tr>';
						html+='<td>'+funciones[i].movie.name + '</td>';
						html+='<td>'+funciones[i].genre + '</td>';
						html+='<td>'+funciones[i].date + '</td>';
						html+='</tr>'
						tabla2.append(html);					
					}				
			 }
			 else{
				 alert("cine no encontrado");
			 }
				 
		}
	};
	
	return {		
		metodoNombre:function (){
			var movie= $('#movie').val();
			apimock.getCinemaByName(movie,CinemaByname);			
		},
		revisar:function () {
			var chequear=document.crear.getElementById('cinema');
			console.log( chequear);		
	    }	
		
	}
})();