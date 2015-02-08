'use strict';

/**
 * @ngdoc function
 * @name blogApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the blogApp
 */
angular.module('blogApp')
  .controller('MainCtrl', ['$scope','$blogResource', function($scope, $blogResource) {
    
    $scope.posts = $blogResource.query();
    
    $scope.deletePost = function(post) {
    	$blogResource.delete({postId: post.id}).$promise.then(function() {
			$scope.posts = $blogResource.query();
		});
	};
    
 }]);
