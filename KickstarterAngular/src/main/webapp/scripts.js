//index.thml scripts
var IndexApp = angular.module('IndexApp', []);

 	IndexApp.controller('QuoteController',
 		    function QuoteController($scope, $http){
 		            $http.get('/rest/quote').
 		             success(function(data) {
 		                $scope.name=data.name;
 		                
 		            });
 		    }
 		);
 		
 	IndexApp.controller('CategoriesController',
 		    function QuoteController($scope, $http){
 		            $http.get('/rest/category').
 		             success(function(data) {
 		                $scope.categories=data;
 		              
 		            });
 		    }
 		);


//category.html scripts
 	var CategoryApp = angular.module('CategoryApp', []);

 	CategoryApp.controller('CategoryController',
 		    function CategoryController($scope, $http){
 		            $http.get("/rest/project/projects/"+getUrlParameter("categoryId")).
 		             success(function(data) {
 		                $scope.projects=data;
 		            });
 		    }
 		);
 

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
		);
		
		ProjectApp.controller('ProjectController',
		    function ProjectController($scope, $http){
		            $http.get("/rest/project/"+projectId).
		             success(function(data) {
		                $scope.data=data;
		            });
		    }
		);
		
		ProjectApp.controller('QuestionsController',
		    function QuestionsController($scope, $http){
		            $http.get("/rest/question/"+projectId).
		             success(function(data) {
		                $scope.questions=data;
		            });
		    }
		);
		
		ProjectApp.controller('VisibleQuestionController', 
			function VisibleQuestionController($scope) {
			$scope.projectId = projectId;
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                $scope.IsVisible = $scope.IsVisible ? false : true;
            };
            
            }
		);
		
		
		ProjectApp.controller('RewardsController', 
			function RewardsController($scope, $http) {
			$scope.projectId = projectId;
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                $scope.IsVisible = $scope.IsVisible ? false : true;
            };
            
            $http.get("/rest/rewards/"+projectId).
            success(function(data) {
               $scope.rewards=data;
           });
            }
		);
		

//reward.html scripts
var RewardApp = angular.module('RewardApp', []);
var rewardId = getUrlParameter("rewardId");
	
RewardApp.controller('BackToProjectController',
	    function BackToProjectController($scope, $http){
	            $http.get("/rest/rewards/project/"+rewardId).
	             success(function(data) {
	                $scope.project=data;
	            });
	    }
	);

	RewardApp.controller('RewardController',
	    function BackToProjectController($scope, $http){
				$scope.rewardId = rewardId;
	            $http.get("/rest/rewards/reward/"+rewardId).
	             success(function(data) {
	                $scope.reward=data;
	            });
	    }
	);
