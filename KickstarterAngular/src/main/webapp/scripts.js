//index.thml scripts
var IndexApp = angular.module('IndexApp', []);

 	IndexApp.controller('QuoteController',
 		    function QuoteController($scope, $http){
 		            $http.get('/rest/quote').
 		             success(function(data) {
 		                $scope.name=data.name;
 		                
 		            });
 		    }
 		)
 		
 	IndexApp.controller('CategoriesController',
 		    function QuoteController($scope, $http){
 		            $http.get('/rest/category').
 		             success(function(data) {
 		                $scope.categories=data;
 		              
 		            });
 		    }
 		)


//category.html scripts
 	var CategoryApp = angular.module('CategoryApp', []);

 	CategoryApp.controller('CategoryController',
 		    function CategoryController($scope, $http){
 		            $http.get("/rest/project/projects/"+getUrlParameter("categoryId")).
 		             success(function(data) {
 		                $scope.projects=data;
 		            });
 		    }
 		)
 

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

//project.html scripts

var ProjectApp = angular.module('ProjectApp', []);
var projectId = getUrlParameter("projectId");

	ProjectApp.controller('BackToCategoryController',
		    function BackToCategoryController($scope, $http){
		            $http.get("/rest/category/project/"+projectId).
		             success(function(data) {
		                $scope.data=data;
		            });
		    }
		)
		
		ProjectApp.controller('ProjectController',
		    function ProjectController($scope, $http){
		            $http.get("/rest/project/"+projectId).
		             success(function(data) {
		                $scope.data=data;
		            });
		    }
		)
		
		ProjectApp.controller('QuestionsController',
		    function QuestionsController($scope, $http){
		            $http.get("/rest/question/"+projectId).
		             success(function(data) {
		                $scope.questions=data;
		            });
		    }
		)
		
		ProjectApp.controller('VisibleQuestionController', 
			function ($scope) {
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                $scope.IsVisible = $scope.IsVisible ? false : true;
            };
            }
		)
		
		ProjectApp.controller('RewardController', 
			function RewardController($scope, $http) {
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                $scope.IsVisible = $scope.IsVisible ? false : true;
            };
            
            $http.get("/rest/rewards/"+projectId).
            success(function(data) {
               $scope.rewards=data;
           });
            }
		)


$(document).ready( function() {
	  $("#questionFormId").attr("action", "rest/question/add/"+getUrlParameter("projectId"));
});

$(document).ready( function() {
    $("#paymentFormId").attr("action", "rest/payment/add/"+getUrlParameter("projectId"));
});

ProjectApp.controller('RewardsController',
	    function RewardsController($scope, $http){
	            $http.get("/rest/rewards/"+projectId).
	             success(function(data) {
	                $scope.rewards=data;
	            });
	    }
	)
	
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