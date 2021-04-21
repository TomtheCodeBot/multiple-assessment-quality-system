$(document).ready(function (){
		$("#login-btn").click(function () {
	console.log('Make fake request for questionaire')
	postQuestionaire();
});
})



function postQuestionaire() {
  	let Answers = {
		"cname" : "012",
		"lname" : "Alexei Stukov",
		"gender" : "Male",
		"attend" : "Always",
		"q1" : "1",
		"q2" : "2",
		"q3" : "3",
		"q4" : "3",
		"q5" : "4",
		"q6" : "2",
		"q7" : "3",
		"q8" : "1",
		"q9" : "5",
		"q10" : "2",
 		"q11" : "1",
		"q12" : "4",
		"q13" : "1",
		"q14" : "3",
		"q15" : "2",
		"q16" : "5",
		"q17" : "1",
		"q18" : "asdcda"
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
			alert(data)
			}
	})
}