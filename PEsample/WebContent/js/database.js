	let labelChoice = ['academic_year', 'faculty','semester','program','module','class','lecturer']
	let select = ['aca','fal','sem','pro','mod','cla','lec']
	let labelChoice1 = ['AcaFac','AcaFacPro','AcaFacProMod']
	let select1 = ['aca-fal-db', 'aca-fal-pro-db', 'aca-fal-pro-mod-db']
$(document).ready(function(){
	$("#db-show").click(function(){
		$(".db-table td").remove();
		for (const init in labelChoice){
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=infodatabase" + "&choice=" + labelChoice[init],
			success: function(data){
				obj = JSON.parse(data)
				console.log(obj)
				
				console.log(init)
				for (var i = 0; i<obj.length;i++){
				console.log(i)
				$("#" + select[init] +"-db").children(".db-table").append('<tr><td>' + obj[i].Code + '</td><td>' + obj[i].Name + '</td></tr>')
				}
			} 			
		})
}
	 for (const init in labelChoice1){
		if( init == 0) {// AcaFac
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=" + labelChoice1[init],	
			success: function(data){
				obj = JSON.parse(data)
				obj.sort(function(a, b){return a.AFCode - b.AFCode;});
				for (var i = 0; i <obj.length;i++){
					console.log(i)
					$("#" + select1[0]+"").children(".db-table").append('<tr><td>' + obj[i].AFCode + '</td><td>' + obj[i].AYName + '</td><td>' + obj[i].FName + '</td></tr>')
				}
			}			
		})
		}
		if( init == 1){
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=" + labelChoice1[init],	
			success: function(data){
				obj = JSON.parse(data)
				obj.sort(function(a, b){ return a.PFCode - b.PFCode;});
				for (var i = 0; i <obj.length;i++){
					console.log(i)
					$("#" + select1[1]+"").children(".db-table").append('<tr><td>' + obj[i].PFCode + '</td><td>' + obj[i].AYName + '</td><td>'+ obj[i].FName + '</td><td>' + obj[i].PName +'</td></tr>')
				}
			}			
		})	
		}
		
		if( init == 2){
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=" + labelChoice1[init],	
			success: function(data){
				obj = JSON.parse(data)
				obj.sort(function(a, b){  return a.PFCode - b.PFCode;});
				for (var i = 0; i <obj.length;i++){
					console.log(i)
					$("#" + select1[2]+"").children(".db-table").append('<tr><td>' + obj[i].PFCode + '</td><td>' + obj[i].MCode + '</td><td>'+ obj[i].AYName + '</td><td>' + obj[i].FName + '</td><td>'+ obj[i].PName +'</td><td>'+ obj[i].MName +'</td></tr>')
				}
			}			
		})	
		}
	}	
	})
})

