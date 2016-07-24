//index.thml scripts
$(document).ready(function () {
 $.ajax({
  type: "GET",
  dataType: "json",
  url: "/rest/quote",
  success: function (data) {
   $("#quoteName").text(data.name)
  }
 });
});

$(document).ready(function () {
 $.ajax({
  type: "GET",
  dataType: "json",
  url: "/rest/category",
  success: function (data) {
	    $.each(data, function (index, value) {
	        $("#categoryName").append("<a style = \"text-decoration: none\" href=\"/category.html?categoryId="
	        		+value.id+"\"><div id=\"AHrefIndex\" ><p>"+value.name+"</p></div></a>");
	        
	    });
	    $("div[id=AHrefIndex]").mouseenter(function(){
	        $(this).fadeTo("fast", 1);
	        });
	    $("div[id=AHrefIndex]").mouseleave(function(){
	        $(this).fadeTo("fast", 0.5);
	        });
	}
 });
}
);

//category.html scripts
$(document).ready(function () {
	 
    $("a[id=home]").mouseenter(function(){
        $(this).fadeTo("fast", 1);
        });
    $("a[id=home]").mouseleave(function(){
        $(this).fadeTo("fast", 0.5);
        });
});

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/project/projects/"+getUrlParameter("categoryId"),
	  success: function (data) {
	      $.each(data, function (index, value) {
	          $("#projectName").append("<a style = \"text-decoration: none; color: blue\" href=\"/project.html?projectId="+value.id+"\"><div id=\"AHrefCategory\" ><p>"+value.name+"</p></div></a>"
	        		  +"<p> Project description: "+value.description+"</p>"
	        		  +"<p> Needed amount: "+value.neededAmount+"</p>"
	        		  +"<p> Available amount: "+value.availableAmount+"</p>"
	        		  +"<p> Remaining days: "+value.remainingDays+"</p><hr/>");
	      });
	      $("div[id=AHrefCategory]").mouseenter(function(){
	          $(this).fadeTo("fast", 1);
	          });
	      $("div[id=AHrefCategory]").mouseleave(function(){
	          $(this).fadeTo("fast", 0.5);
	          });
	  }
	 });
});

//project.html scripts
$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/category/project/"+getUrlParameter("projectId"),
	  success: function (data) {
	   $("#backToCategory").append("<a id=\"home\" style = \"text-decoration: none; color: blue\" href=\"/category.html?categoryId="+data.id+"\">Back</a>");
	   
	   $("a[id=home]").mouseenter(function(){
	          $(this).fadeTo("fast", 1);
	          });
	   $("a[id=home]").mouseleave(function(){
	          $(this).fadeTo("fast", 0.5);
	          });
	  }
	 });
});

var toggleVisibility = function(element) {
    if(element.style.visibility=='visible'){
        element.style.visibility='hidden';
    } else {
        element.style.visibility='visible';
    }
};

$(document).ready( function() {
	  $("#questionFormId").attr("action", "rest/question/add/"+getUrlParameter("projectId"));
});

$(document).ready( function() {
    $("#paymentFormId").attr("action", "rest/payment/add/"+getUrlParameter("projectId"));
});

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/question/"+getUrlParameter("projectId"),
	  success: function (data) {
		  
		  $.each(data, function (index, value) {
	              $("#projectQuestions").append("<p> "+(index+1)+": "+value.name+"</p>");
	          });
	  
	  }
	 });
});

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/project/"+getUrlParameter("projectId"),
	  success: function (data) {
	     
	          $("#wholeProject").append("<p> Project name: "+data.name+"</p>"
	        		  +"<p> Project description: "+data.description+"</p>"
	        		  +"<p> Needed amount: "+data.neededAmount+"</p>"
	        		  +"<p> Available amount: "+data.availableAmount+"</p>"
	        		  +"<p> Remaining days: "+data.remainingDays+"</p>"
	        		  +"<p> History: "+data.history+"</p>"
	        		  +"<p> Video: "+data.video+"</p>");
	          
	  }
	 });
});

$(document).ready(function () {
	 
    $("h3[id=Button]").mouseenter(function(){
        $(this).fadeTo("fast", 1);
        });
    $("h3[id=Button]").mouseleave(function(){
        $(this).fadeTo("fast", 0.5);
        });
});

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/rewards/"+getUrlParameter("projectId"),
	  success: function (data) {
		    $.each(data, function (index, value) {
		        $("#rewards").append("<a style = \"text-decoration: none\" href=\"/reward.html?rewardId="
		        		+value.id+"\"><div id=\"RewardsButton\" ><p>"+value.name+"</p></div></a>"+" "+value.description);
		        
		    });
		    $("div[id=RewardsButton]").mouseenter(function(){
		        $(this).fadeTo("fast", 1);
		        });
		    $("div[id=RewardsButton]").mouseleave(function(){
		        $(this).fadeTo("fast", 0.5);
		        });
		}
	 });
});

//reward.html scripts
$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/rewards/project/"+getUrlParameter("rewardId"),
	  success: function (data) {
	   $("#backToProject").append("<a id=\"home\" style = \"text-decoration: none; color: blue\" href=\"/project.html?projectId="+data.id+"\">Back</a>");
	   
	   $("a[id=home]").mouseenter(function(){
	        $(this).fadeTo("fast", 1);
	        });
	    $("a[id=home]").mouseleave(function(){
	        $(this).fadeTo("fast", 0.5);
	        });
	  }
	 });
});

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/rewards/reward/"+getUrlParameter("rewardId"),
	  success: function (data) {
		  $("#reward").append("<b style=\"color:yellow\"> \""+data.name+" \"</b>");
		}
	 });
});

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/rewards/reward/"+getUrlParameter("rewardId"),
	  success: function (data) {
		  $("#amountRewardId").attr("value", data.amount);
		}
	 });
});

$(document).ready(function () {
	 $.ajax({
	  type: "GET",
	  dataType: "json",
	  url: "/rest/rewards/project/"+getUrlParameter("rewardId"),
	  success: function (data) {
		  $("#rewardProject").append("<b style=\"color:yellow\"> \""+data.name+"\"</b>");
		}
	 });
});

$(document).ready( function() {
    $("#rewardFormId").attr("action", "rest/payment/add/reward/"+getUrlParameter("rewardId"));
});