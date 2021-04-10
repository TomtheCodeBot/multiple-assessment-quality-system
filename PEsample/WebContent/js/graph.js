$(document).ready(function() {
    getFilterResources();
$("#visual").on("click",
	function(event) {
		getGeneralInfo()
		})
})
$("#reset").on("click",
	function(event){
		getInitialize()
	})

function getFilterResources() {
	getInitialize();
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
	{	$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + cname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a);
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		console.log(sname)
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a);
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		console.log(sname)
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a);
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a);
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
	{	$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + cname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a);
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=lname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		
		$("#mod-select").children("option").remove();
		$("#mod-select").append('<option value="'+ mname +'">'+ mname +'</option>')
	}
		
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
	{	$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=ayname&cname=" + cname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
			success: function(data, textStatus, jqXHR){
				var obj = JSON.parse(data);
				$(`#aca-select option`).not(":first").remove()
			for (var i = 0; i < obj.length; i++){
				a = new String(obj[i].ayname);
				console.log(a);
				$("#aca-select").append('<option value="'+ a +'">'+ a +'</option>')
			}
			}
		})
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=sname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=fname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=pname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=cname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		
		$.ajax({
			type: 'Get',
			url: "rest/graph/resources?selector=mname&cname=" + cname + "&ayname=" +ayname + "&fname=" + fname + "&sname=" + sname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
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
		
		$("#lec-select").children("option").remove();
		$("#lec-select").append('<option value="'+ lname +'">'+ lname +'</option>')
	}
		
}
function getGeneralInfo(){
		var cname = new String($("#cla-select").children("option:selected").val());
		var ayname = new String($("#aca-select").children("option:selected").val());
		var fname = new String($("#fal-select").children("option:selected").val());
		var sname = new String($("#sem-select").children("option:selected").val());
		var mname = new String($("#mod-select").children("option:selected").val());
		var lname = new String($("#lec-select").children("option:selected").val());
		var pname = new String($("#pro-select").children("option:selected").val());
		console.log(cname + ayname +fname + sname + mname + lname + pname)
			$.ajax({
				type: 'Get',
				url: "rest/graph/general?filter=gender&cname=" + cname + "&ayname="+ ayname + "&sname=" + sname + "&fname=" + fname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
				success: function(data){
					var obj = JSON.parse(data);
					var ctx = document.getElementById('genderChart');
					var genderChart = new Chart(ctx, {
						    type: 'bar',
						    data: {
						        labels: ['Male', 'Female', 'Other'],
						        datasets: [{
						            label: 'Percentage of respondents by gender',
						            data: [obj.Male, obj.Female, obj.Other,],
						            backgroundColor: [
						                'rgba(255, 159, 64, 0.3)',
										'rgba(255, 159, 64, 0.3)',
										'rgba(255, 159, 64, 0.3)'
						            ],
						            borderColor: [
						                'rgba(255, 159, 64, 1)',
										'rgba(255, 159, 64, 1)',
										'rgba(255, 159, 64, 1)'
						            ],
						            borderWidth: 1
						        }]
						    },
						    options: {
						        scales: {
						            yAxes: [{
						                ticks: {
						                    beginAtZero: true
						                }
						            }]
						        }
						    }
						});
				}
			})
		
			$.ajax({
				type: 'Get',
				url: "rest/graph/general?filter=attends&cname=" + cname + "&ayname="+ ayname + "&sname=" + sname + "&fname=" + fname + "&mname=" + mname + "&lname=" + lname + "&pname=" + pname,
				success: function(data){
						var obj = JSON.parse(data)
						var ctx = document.getElementById('attendChart');
						var genderChart = new Chart(ctx, {
							    type: 'bar',
							    data: {
								        labels: ['Never', 'Rarely', 'Sometimes', 'Often', 'Always'],
								        datasets: [{
								            label: 'Percentage of respondents by class attendance',
								            data: [ obj.Never, obj.Rarely, obj.Sometime, obj.Often, obj.Always,],
								            backgroundColor: [
								                'rgba(255, 159, 64, 0.3)',
												'rgba(255, 159, 64, 0.3)',
												'rgba(255, 159, 64, 0.3)',
												'rgba(255, 159, 64, 0.3)',
												'rgba(255, 159, 64, 0.3)'
								            ],
								            borderColor: [
								                'rgba(255, 159, 64, 1)',
												'rgba(255, 159, 64, 1)',
												'rgba(255, 159, 64, 1)',
												'rgba(255, 159, 64, 1)',
												'rgba(255, 159, 64, 1)'
								            ],
								            borderWidth: 1
											
								        }]
								    },
								    options: {
								        scales: {
								            yAxes: [{
								                ticks: {
								                    beginAtZero: true
								                }
								            }]
								        }
								    }
								});
				}
			})
}

function getInitialize(){
		$("#aca-select").children("option").remove();
		$("#sem-select").children("option").remove();
		$("#fal-select").children("option").remove();
		$("#pro-select").children("option").remove();
		$("#mod-select").children("option").remove();
		$("#cla-select").children("option").remove();
		$("#lec-select").children("option").remove();
		
	ayname = ""
	sname = ""
	fname = ""
	pname = ""
	mname = ""
	lname = ""
	cname = ""
	$("#aca-select").append($('<option>').val("").text("aca_year"));
	$("#sem-select").append($('<option>').val("").text("semester"));
	$("#fal-select").append($('<option>').val("").text("faculty"));
	$("#pro-select").append($('<option>').val("").text("program"));
	$("#mod-select").append($('<option>').val("").text("module"));
	$("#cla-select").append($('<option>').val("").text("class"));
	$("#lec-select").append($('<option>').val("").text("lecturer"));

	$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=cname&ayname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].cname);
				console.log(cla);
				$("#cla-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
		$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=ayname&ayname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].ayname);
				console.log(cla);
				$("#aca-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
	$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=sname&ayname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].sname);
				console.log(cla);
				$("#sem-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
	$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=fname&ayname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].fname);
				console.log(cla);
				$("#fal-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
	$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=mname&mname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].mname);
				console.log(cla);
				$("#mod-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
	$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=pname&ayname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].pname);
				console.log(cla);
				$("#pro-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
	$.ajax({
		type: 'GET',
		url: "rest/graph/resources?selector=lname&ayname=" + ayname +"&sname=" + sname + "&fname=" + fname + "&pname=" + pname + "&mname=" + mname + "&lname=" + lname,
		success: function (data, textStatus, jqXHR) {
			var obj = JSON.parse(data);
			for (var i = 0; i < obj.length; i++){
				cla = new String(obj[i].lname);
				console.log(cla);
				$("#lec-select").append('<option value="'+ cla +'">'+ cla +'</option>')
			}
		}
	})
}
