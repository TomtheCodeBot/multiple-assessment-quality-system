$(document).ready(function() {
	getClasses()
	
	getClassesLecturer()
	getClassInfo()
	$("#submit").on("click",
	function(event) {
		submitQuestionaire()
		})
	$("#button1").on("click",
		function(event) {
			getTable()
		})
	
})


function getExample() {
	$.ajax({
		type: 'GET',
		url: "rest/example",
		success: function(data, textStatus, jqXHR) {
			alert(data)
		}
	});
}

function getProgramsCount() {
	$.ajax({
		type: 'GET',
		contentType: "application/json",
		url: "rest/programs/count",
		success: function(data, textStatus, jqXHR) {
			alert(data)
		}
	})
}
function getTable(){
    $.ajax({
    type : 'GET',
    url : "rest/programs/table",
    success : function(obj, textStatus, jqXHR) {
		obj = JSON.parse(obj);
        $("#mytable1").html("");
        for (var i = 0; i < obj.length; i++) {
            var tr = "<tr>";
            var td0 = "<td>" + (i + 1) + "</td>";
            var td1 = "<td>" + obj[i].academic_year + "</td>";
            var td2 = "<td>" + obj[i].Faculty + "</td>";
            var td3 = "<td>" + obj[i].Lecturer + "</td>";
            var td4 = "<td>" + obj[i].Module + "</td>";
			var td5 = "<td>" + obj[i].Semester + "</td>";
            var td6 = "<td>" + obj[i].Class + "</td>";
			var td7 = "<td>" + obj[i].Programm + "</td></tr>";
            $("#mytable1").append(tr + td0 + td1 + td2 + td3 + td4 + td5 +td6+td7);
	        	}
	    	}
		}
	);
}
function getClasses(){
	$.ajax({
		type: 'GET',
		url: "rest/questionaire/classes",
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cname = new String(obj[i].Class);
				console.log(cname);
			$("#class_name").append('<option value="'+ cname +'">'+ cname +'</option>')
			}
			
		}
	})
}

function getClassesLecturer(){
	$("#class_name").change(function(){	
		$("#lecturer_name").children("option").remove();
       	var name = new String($(this).children("option:selected").val());
	$.ajax({
		type:'GET',
		url: "rest/questionaire/lecturer/" + name,
		success : function(data, textStatus, jqXHR){
			var obj1 = JSON.parse(data);
			for (var i = 0; i < obj1.length; i++){
				lname = new String(obj1[i].Lecturer_Name);
			$("#lecturer_name").append(new Option(lname,lname));
			}
		}
	})
})
}
function getClassInfo(){
	$("#class_name").change(function(){
		$("#aca,#sem,#fal,#mod,#pro").children("h3").remove();
		var name = new String($(this).children("option:selected").val());
		console.log(name);
		$.ajax({
			type: 'GET',
			url: "rest/questionaire/info/" + name,
			success : function(data, textStatus, jqXHR){
				var obj2 = JSON.parse(data);
				$("#aca").append(`<h3> "${obj2.academic_name}" </h3>`)
				$("#sem").append(`<h3> "${obj2.semester_name}" </h3>`)
				$("#fal").append(`<h3> "${obj2.faculty_name}" </h3>`)
				$("#pro").append(`<h3> "${obj2.program_name}" </h3>`)
				$("#mod").append(`<h3> "${obj2.module_name}" </h3>`)	
			}		
		})
	})
}
function submitQuestionaire(){
	var value1 = $("#num1 :selected").val();
	var value2 = $("#num2 :selected").val();
	var value3 = $("#num3 :selected").val();
	var value4 = $("#num4 :selected").val();
	var value5 = $("#num5 :selected").val();
	var value6 = $("#num6 :selected").val();
	var value7 = $("#num7 :selected").val();
	var value8 = $("#num8 :selected").val();
	var value9 = $("#num9 :selected").val();
	var value10 = $("#num10 :selected").val();
	var value11 = $("#num11 :selected").val();
	var value12 = $("#num12 :selected").val();
	var value13 = $("#num13 :selected").val();
	var value14 = $("#num14 :selected").val();
	var value15 = $("#num15 :selected").val();
	var value16 = $("#num16 :selected").val();
	var value17 = $("#num17 :selected").val();
	var value18 = $("#review").val();
	var gender = $("#gender1 :selected").val();
	var attend = $("#attend1 :selected").val();
	var cname  = new String($("#class_name").children("option:selected").val());
	var lname = new String($("#lecturer_name").children("option:selected").val());
	let Answers = {
		"cname" : cname,
		"lname" : lname,
		"gender" : gender,
		"attend" : attend,
		"q1" : value1,
		"q2" : value2,
		"q3" : value3,
		"q4" : value4,
		"q5" : value5,
		"q6" : value6,
		"q7" : value7,
		"q8" : value8,
		"q9" : value9,
		"q10" : value10,
 		"q11" : value11,
		"q12" : value12,
		"q13" : value13,
		"q14" : value14,
		"q15" : value15,
		"q16" : value16,
		"q17" : value17,
		"q18" : value18
	}
	$.ajax({
		type: 'POST',
		contentType: "application/json",
		url: "rest/questionaire/answers",
		data: JSON.stringify(Answers),
		dataType: "text",
		error: function(e) {
 		   console.log(e);
 		 },
		success : function(data, textStatus, jqXHR){
			alert("Submit successful");
			}
	})
}
	