graph =[]
	
$(document).ready(function() {
    getFilterResources();
	getInitialize();
	let id = ['genderChart','attendChart','question1', 'question2', 'question3', 'question4','question5','question6', 'question7', 'question8', 'question9', 'question10', 'question11', 'question12', 'question13', 'question14', 'question15', 'question16','question17'] 
	var label=['Percentage of respondents by Gender','Percentage of respondents by class attendance','1. The module have been clear to me','2. The Learning Material have been sufficient and useful',
			'3. The Content of the module have always been relevant','4. The lesson have been interesting','5. The module workload outside classroom has been'
			,'6. The overall module workload has been','7. The level of difficulty of module has been','8. Module content have been presented understandably'
			,'9. Various learning activities have been used to teach the content','10. The learning activities have supported the intended learning outcomes',
			'11. The assessment methods are appropriate','12. Students have been encouraged to apply critical thinking and logics to understand courses ',
			'13. The lecturer has given feedback (about answers of performances, reports,etc...)','14. The languages skill of the lecturer was excellent',
			'15. The lecturer has listened to ideas and contributions of students','16. The lecturer has encouraged discussion and questions',
			'17. The lecturer has offered consultation for invididuals for academic support'];			
		labelGender = ['Male','Female','Other']
		labelAttend = ['Never','Rarely','Sometimes','Often','Always']
		labelModule = ['Less than 1 hours', '1-2 hours', '2-3 hours', '3-4 hours', 'more than 5 hours']
for (var init in id){
		// init = question - 1
		console.log(init + `${id[init]}`)
	if(init == 0){
		var ctx = document.getElementById(`${id[init]}`).getContext('2d');
		var myChart = new Chart(ctx, {		
					    type: 'bar',
					    data: {
					        labels: ['Male','Female','Other'],
					        datasets: [{
					            label: "percentage",
					            data: [0,0,0,],
					            backgroundColor: [
					                'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)'
					            ],
					            borderColor: [
					                'rgba(255, 159, 64, 1)',
					                'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 64, 1)'
					            ], 
					            borderWidth:1}
								]
					    },
					    options: {
							title: {
								display:true,
								text: label[init],
								fontsize: 15,	
							},
					        scales: {
					            yAxes: [{
				ticks: {

					   min: 0,
					   max: 100,
					   callback: function(value){return value+ "%"}
					},  
									scaleLabel: {
					   display: true,
					   labelString: "Percentage"
					}
				}]
					        }
					    }
					}
	);	
	}
else {
		var ctx = document.getElementById(`${id[init]}`).getContext('2d');
		var myChart = new Chart(ctx, {		
					    type: 'bar',
					    data: {
					        labels: ['1','2','3','4','5'],
					        datasets: [{
					            label: "percentage",
					            data: [0,0,0,],
					            backgroundColor: [
					                'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)',
									'rgba(255, 159, 64, 0.2)'
					            ],
					            borderColor: [
					                'rgba(255, 159, 64, 1)',
					                'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 64, 1)',
									'rgba(255, 159, 64, 1)'
					            ],
					            borderWidth:1}
						]
					    },
					    options: {
							title: {
								display:true,
								text: label[init],
								fontsize: 15,	
							},
					        scales: {
					            yAxes: [{
				ticks: {

					   min: 0,
					   max: 100,
					   callback: function(value){return value+ "%"}
					},  
									scaleLabel: {
					   display: true,
					   labelString: "Percentage"
					}
				}]
					        }
					    }
					}
	);
}
	graph.push(myChart)
}
	graph[1].data.labels = labelAttend;
	graph[1].update()
	for(let i = 6; i < 9;i++){
		graph[i].data.labels = labelModule;
		graph[i].update()
	}
	
$("#visual").on("click",
	function(event) {
		getGeneralInfo()
		})
$("#reset").on("click",
	function(event){
		getInitialize()
	})
})


function getFilterResources() {
	 $("#cla-select").change(function() {
		getcname()
 })
	
	 $("#aca-select").change(function() {
		getAcademicYear()
 })
     $("#sem-select").change(function() {
		getsname()
 })
	 $("#fal-select").change(function() {
		getfname()
 })
	$("#pro-select").change(function() {
		getpname()
 })
	$("#mod-select").change(function() {
		getmname()
 })
	$("#lec-select").change(function() {
		getlname()
 })

$("#aca-select").mouseover(function(){
	var usedNames = {};
		$("select[name='aca-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})
	$("#sem-select").mouseover(function(){
	var usedNames = {};
		$("select[name='sem-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})
	$("#fal-select").mouseover(function(){
	var usedNames = {};
		$("select[name='fal-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})
	$("#mod-select").mouseover(function(){
	var usedNames = {};
		$("select[name='mod-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})
	$("#pro-select").mouseover(function(){
	var usedNames = {};
		$("select[name='pro-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})
	$("#cla-select").mouseover(function(){
	var usedNames = {};
		$("select[name='cla-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})
	$("#lec-select").mouseover(function(){
	var usedNames = {};
		$("select[name='lec-select'] > option").each(function () {
   		 if(usedNames[this.text]) {
       		 $(this).remove();
    	} else {
        	usedNames[this.text] = this.value;
   				}
});
	})

 }
function getcname(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());	
//cname
	{	getaca()
		getsem()
		getfal()
		getpro()
		getmod()
		getlec()
		$("#cla-select").children("option").remove();
		$("#cla-select").append('<option value="'+ cname +'">'+ cname +'</option>')
	}	
}
function getAcademicYear(){
// ayname
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
		getcla()
		getsem()
		getfal()
		getpro()
		getmod()
		getlec()
		$("#aca-select").children("option").remove();
		$("#aca-select").append('<option value="'+ ayname +'">'+ ayname +'</option>')
		
		
}
function getsname(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
		getcla()
		getaca()
		getfal()
		getpro()
		getmod()
		getlec()
		$("#sem-select").children("option").remove();
		$("#sem-select").append('<option value="'+ sname +'">'+ sname +'</option>')
		
}
function getfname(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
		getcla()
		getaca()
		getsem()
		getpro()
		getmod()
		getlec()
		$("#fal-select").children("option").remove();
		$("#fal-select").append('<option value="'+ fname +'">'+ fname +'</option>')
		
}
function getpname(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
		getcla()
		getaca()
		getsem()
		getfal()
		getmod()
		getlec()
		$("#pro-select").children("option").remove();
		$("#pro-select").append('<option value="'+ pname +'">'+ pname +'</option>')
		
}
function getmname(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
//mname
		getaca()
		getsem()
		getfal()
		getpro()
		getcla()
		getlec()			
		$("#mod-select").children("option").remove();
		$("#mod-select").append('<option value="'+ mname +'">'+ mname +'</option>')
		
}
function getlname(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
//lname
	{	getaca()
		getfal()
		getpro()
		getcla()
		getsem()
		getmod()	
		$("#lec-select").children("option").remove();
		$("#lec-select").append('<option value="'+ lname +'">'+ lname +'</option>')
	}
		
}
function getGeneralInfo(){
		var a = getSelect()
		removePrevdata()
		console.log(graph.length)
		$(".tooltip").show()
		$.ajax({
			type: 'Get',
				url: "rest/graph/general?filter=gender&cname=" + a[0] + "&ayname="+ a[1] + "&sname=" + a[3] + "&fname=" + a[2] + "&mname=" + a[4] + "&lname=" + a[5] + "&pname=" + a[6],
				success: function(data){
						var obj = JSON.parse(data);
						var array = [obj.Male, obj.Female, obj.Other]
						var array1 = getPercentagevalue(array)
						console.log(array1)
						graph[0].data.datasets[0].data = array1
						graph[0].update()
				}
		})
// a[0] :cname, a[1]: ayname; a[2]: fname; a[3]: sname; a[4]:mname; a[5]: lname; a[6]: pname		
		$.ajax({
				type: 'Get',
				url: "rest/graph/general?filter=attends&cname=" + a[0] + "&ayname="+ a[1] + "&sname=" + a[3] + "&fname=" + a[2] + "&mname=" + a[4] + "&lname=" + a[5] + "&pname=" + a[6],
				success: function(data){
						var obj = JSON.parse(data);
						var array = [obj.Never,obj.Rarely,obj.Sometime,obj.Often,obj.Always]
						var array1 = getPercentagevalue(array)						
						graph[1].data.datasets[0].data = array1
						graph[1].update()
				}
		})
		var k;
		for (k = 2; k < 19; k++) {
				let i=k;
				console.log(k)
				$.ajax({
					type: 'Get',
					url: "rest/graph/question?qname=Q"+(i-1).toString()+"&cname=" + a[0] + "&ayname="+ a[1] + "&sname=" + a[3] + "&fname=" + a[2] + "&mname=" + a[4] + "&lname=" + a[5] + "&pname=" + a[6],
					success: function(data){
						var obj = JSON.parse(data);
						console.log(data);
						$("#q"+(i-1).toString()+"_count").append(`<span>${obj.Count}<span>`);
						$("#q"+(i-1).toString()+"_rate").append(`<span>${obj.Rate}<span>`);
						$("#q"+(i-1).toString()+"_average").append(`<span>${obj.Average}<span>`);
						$("#q"+(i-1).toString()+"_sd").append(`<span>${obj.Standard_Deviation}<span>`);
						var array = [obj.Percentage_of_1, obj.Percentage_of_2, obj.Percentage_of_3, obj.Percentage_of_4, obj.Percentage_of_5]
						graph[i].data.datasets[0].data = array
						graph[i].update()
						}
				})
			}
			console.log(graph)								
}

function getInitialize(){
		$("#aca-select").children("option").remove();
		$("#sem-select").children("option").remove();
		$("#fal-select").children("option").remove();
		$("#pro-select").children("option").remove();
		$("#mod-select").children("option").remove();
		$("#cla-select").children("option").remove();
		$("#lec-select").children("option").remove();
		$(".tooltip").hide()
		removePrevdata()
	// clear graph
		for (var i in graph){
// gender location in graph
		if(i == 0){
			var array = [0,0,0]
			graph[i].data.datasets[0].data = array
			graph[i].update()}
//other location
			else{ 
			var array = [0,0,0,0,0]
			graph[i].data.datasets[0].data = array
			graph[i].update()
		}
	}
	ayname = ""
	sname = ""
	fname = ""
	pname = ""
	mname = ""
	lname = ""
	cname = ""
	// a[0] :cname, a[1]: ayname; a[2]: fname; a[3]: sname; a[4]:mname; a[5]: lname; a[6]: pname		
	$("#aca-select").append($('<option>').val("").text("aca_year"));
	$("#sem-select").append($('<option>').val("").text("semester"));
	$("#fal-select").append($('<option>').val("").text("faculty"));
	$("#pro-select").append($('<option>').val("").text("program"));
	$("#mod-select").append($('<option>').val("").text("module"));
	$("#cla-select").append($('<option>').val("").text("class"));
	$("#lec-select").append($('<option>').val("").text("lecturer"));
	let resetLabel = ['cname','ayname','sname','fname','mname','pname','lname']
	let queryLabel = ['cla-select','aca-select','sem-select','fal-select','mod-select','pro-select','lec-select']
		for(const init in resetLabel){
			$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=" + `${resetLabel[init]}`,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i][`${resetLabel[init]}`]);
				console.log(cla)
				$("#" + `${queryLabel[init]}`+"").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
		}
		
	
}
function removePrevdata(){
		$('#q1 span').remove()
		$('#q2 span').remove()
		$('#q3 span').remove()
		$('#q4 span').remove()
		$('#q5 span').remove()
		$('#q6 span').remove()
		$('#q7 span').remove()
		$('#q8 span').remove()
		$('#q9 span').remove()
		$('#q10 span').remove()
		$('#q11 span').remove()
		$('#q12 span').remove()
		$('#q13 span').remove()
		$('#q14 span').remove()
		$('#q15 span').remove()
		$('#q16 span').remove()
		$('#q17 span').remove()
}

function getSelect(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
		return [cname,ayname,fname,sname,mname,lname,pname];
}
// a[0] :cname, a[1]: ayname; a[2]: fname; a[3]: sname; a[4]:mname; a[5]: lname; a[6]: pname		
function getaca(){
		var value = getSelect()
	$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a)
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
function getcla(){
		var value = getSelect()
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#cla-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].cname);
				console.log(a);
				$("#cla-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
function getfal(){
		var value = getSelect()
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#fal-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].fname);
				console.log(a);
				$("#fal-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
function getpro(){
		var value = getSelect()
			$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#pro-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].pname);
				console.log(a);
				$("#pro-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
function getsem(){
			// a[0] :cname, a[1]: ayname; a[2]: fname; a[3]: sname; a[4]:mname; a[5]: lname; a[6]: pname		
		var value = getSelect()
		console.log(value[0] + "  " + value[1])
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#sem-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].sname);
				console.log(a);
				$("#sem-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
function getmod(){
		// a[0] :cname, a[1]: ayname; a[2]: fname; a[3]: sname; a[4]:mname; a[5]: lname; a[6]: pname		
		var value = getSelect()
	$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
			$(`#mod-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].mname);
				console.log(a);
				$("#mod-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
function getlec(){
			// a[0] :cname, a[1]: ayname; a[2]: fname; a[3]: sname; a[4]:mname; a[5]: lname; a[6]: pname		
		var value = getSelect()
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + value[0] + "&ayname=" + value[1] + "&fname=" + value[2] + "&sname=" + value[3] + "&mname=" + value[4] + "&lname=" + value[5] + "&pname=" + value[6],
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
			$(`#lec-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].lname);
				console.log(a);
				$("#lec-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
}
 var test = ['1','2','3','4']
console.log(getPercentagevalue(test))
function getPercentagevalue(array){
	var array1 = array.map(function(x){
		return parseInt(x)
	})
	array2 = []
	var sum = array1.reduce((a,b) => a + b, 0)
	for ( var init in array1){
		array2.push(Math.round(array1[init]/sum*100*100)/100)
	}
	return array2;
}