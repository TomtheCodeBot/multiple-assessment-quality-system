	let labelShow = ['academic_year', 'faculty','semester','program','module','class','lecturer']
	let select = ['academic_year','faculty','semester','program','module','class','lecturer']
	let labelShow1 = ['AcaFac','AcaFacPro','AcaFacProMod']
	let select1 = ['aca-fal-db', 'aca-fal-pro-db', 'aca-fal-pro-mod-db']
//single choice column
	let LabelChoice = ['aca-choice','fal-choice','sem-choice','pro-choice','mod-choice','cla-choice','lec-choice']
//combine choice column	
	let t;
	let labelChoice1 = ['aca-fal-choice', 'aca-fal-pro-choice', 'aca-fal-pro-mod-choice1', 'aca-fal-pro-mod-choice2']
$(document).ready(function(){
	$("#db-show").click(function(){
		getTable()
	
})

	$("#db-clear").click(function(){
		clear()
	})
})
function getTable(){
		clear()
		for (const init in labelShow){
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=infodatabase" + "&choice=" + labelShow[init],
			success: function(data){
				obj = JSON.parse(data)
				console.log(obj)
				obj.sort(function(a, b) {
 					 var nameA = a.Code.toUpperCase(); // bỏ qua hoa thường
 					 var nameB = b.Code.toUpperCase(); // bỏ qua hoa thường
					  if (nameA < nameB) {
					    return -1;
					  }
					  if (nameA > nameB) {
					    return 1;
					  }
					
					  // name trùng nhau
					  return 0;
					});	
				for (var i = 0; i<obj.length;i++){
				console.log(i)
				console.log(obj[i].Name)
				$("#" + select[init] +"-db").children(".db-table").append('<tr><td>' + obj[i].Code + '</td><td>' + `${obj[i].Name}` +'</td><td>'+ '<button type="submit" class ='+ select[init]+ '-delete'  +' id='+ obj[i].Code  + ' name='+ obj[i].Name.replaceAll(" ","_") +'>' + 'Delete'+ '</button>'+'</td> </tr>')
				$("#" + LabelChoice[init] + "").append(new Option(obj[i].Name,obj[i].Name));
				console.log(select[init])		
				}
				$("."+ select[init]+"-delete").click(function(){
					console.log(`${init}`)
					
					var t  = this.name.replaceAll("_"," ")
					$.ajax({
						type: 'DELETE',
						url: "rest/management/resources?filter=single&col1="+ `${select[init]}` + "&id=" + `${this.id}` + "&name=" + t,
						success: function(data){
							alert("successfully deleted")
							getTable()
						},
						error: function(data){
							alert("cannot delete")						
						}							
					})
				})
			} 			
		})
}
	 for (const init in labelShow1){
		if( init == 0) {// AcaFac
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=" + labelShow1[init],	
			success: function(data){
				obj = JSON.parse(data)
				console.log(obj)
				obj.sort(function(a, b){return a.AFCode - b.AFCode;});
				console.log(obj)
				for (var i = 0; i <obj.length;i++){
					console.log(i)
					console.log(obj[i].FName)
					$("#" + select1[0]+"").children(".db-table").append('<tr><td>' + obj[i].AFCode + '</td><td>' + obj[i].AYName + '</td><td>'+  obj[i].FName + '</td><td>'+ '<button class=' + select1[0] + '-delete' + ' id=' + obj[i].AFCode +  '>'+'Delete' + '</button>'+'</td></tr>')
					$("#" + labelChoice1[0] +"").append(new Option(obj[i].AFCode,obj[i].AFCode))		
				}
				$("." + select1[0]+ '-delete').click(function(){
						$.ajax({
							type: 'DELETE',
							url: "rest/management/resources?filter=combine&col1=year&col2=faculty&col3=&col4=" + "&afcode=" + `${this.id}`,
							success: function(data){
								alert("delete succesfully")
								getTable()
							}
						})
					})
			}			
		})
					
		}
		if( init == 1){
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=" + labelShow1[init],	
			success: function(data){
				obj = JSON.parse(data)
								console.log(obj)
				obj.sort(function(a, b){ return a.PFCode - b.PFCode;});
								console.log(obj)

				for (var i = 0; i <obj.length;i++){
					console.log(i)
					$("#" + select1[1]+"").children(".db-table").append('<tr><td>' + obj[i].PFCode + '</td><td>' + obj[i].AYName + '</td><td>'+ obj[i].FName  + '</td><td>' + obj[i].PName + '</td><td>'+ '<button class=' + select1[1] + '-delete' + ' id=' + obj[i].PFCode +'>'+'Delete' + '</button>'+'</td></tr>')
					$("#" + labelChoice1[1] +"").append(new Option(obj[i].PFCode,obj[i].PFCode))
				}
				$("." + select1[1]+'-delete').click(function(){
						console.log(this.id)
						$.ajax({
							type: 'DELETE',
							url: "rest/management/resources?filter=combine&col1=year&col2=faculty&col3=program&col4=" + "&pfcode=" + `${this.id}`,
							success: function(data){
								alert("delete succesfully")
								getTable()
							}
						})
					})
			}			
		})	
		}
		
		if( init == 2){
		$.ajax({
			type: 'GET',
			url: "rest/management/resources?filter=" + labelShow1[init],	
			success: function(data){
				obj = JSON.parse(data)
				obj.sort(function(a, b){  return a.PFCode - b.PFCode;});
				for (var i = 0; i <obj.length;i++){
					console.log(i)
					$("#" + select1[2]+"").children(".db-table").append('<tr><td>' + obj[i].PFCode + '</td><td>' + obj[i].MCode + '</td><td>'+ obj[i].AYName + '</td><td>' + obj[i].FName + '</td><td>'+ obj[i].PName +'</td><td>'+ obj[i].MName +  '</td><td>'+ '<button class=' + select1[2] + '-delete'+ ' id=' + obj[i].PFCode + '_' + obj[i].MCode  +'>'+'Delete' + '</button>'+'</td></tr>')
					$("#" + labelChoice1[2] +"").append(new Option(obj[i].PFCode,obj[i].PFCode))
					$("#" + labelChoice1[3] +"").append(new Option(obj[i].MCode,obj[i].MCode))
				}
					$("." + select1[2] +'-delete').click(function(){
						let t  = this.id.split("_");
						console.log(t)
					$.ajax({
						type: 'DELETE',
						url: "rest/management/resources?filter=combine&col1=year&col2=faculty&col3=program&col4=module" + "&pfcode=" + `${t[0]}` + "&mcode=" + `${t[1]}`,
						success: function(data){
							alert("successfully deleted")
							getTable()
						}
					})
					})
			}			
		})	
		}
	}
		 
}

function clear(){
	$(".db-table td").remove();
	$(`#aca-choice option`).not(":first").remove()
	$(`#fal-choice option`).not(":first").remove()
	$(`#sem-choice option`).not(":first").remove()
	$(`#pro-choice option`).not(":first").remove()
	$(`#mod-choice option`).not(":first").remove()
	$(`#cla-choice option`).not(":first").remove()
	$(`#lec-choice option`).not(":first").remove()
	$(`#aca-fal-choice option`).not(":first").remove()
	$(`#aca-fal-pro-choice option`).not(":first").remove()
	$(`#aca-fal-pro-mod-choice1 option`).not(":first").remove()
	$(`#aca-fal-pro-mod-choice2 option`).not(":first").remove()
}
String.prototype.replaceAll = function(search, replacement) {
    var target = this;
    return target.split(search).join(replacement);
};