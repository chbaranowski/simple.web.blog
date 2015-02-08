'use strict';

/**
 * @ngdoc function
 * @name blogApp.controller:PostCtrl
 * @description
 * # PostCtrl
 * Controller of the blogApp
 */
angular.module('blogApp')
  .controller('PostCtrl', ['$scope', '$routeParams', '$blogResource', '$location', function($scope, $routeParams, $blogResource, $location) {
      
	  if($routeParams.postId) {
		 $scope.post = $blogResource.get({postId: $routeParams.postId});
	  } else {
		 $scope.post = {title: '', content: ''};
	  }
	  
	  $scope.savePost = function() {
		  $blogResource.save({}, $scope.post).$promise.then(function() {
			  $location.path('/');
		  });
	  };
	  
  }]);