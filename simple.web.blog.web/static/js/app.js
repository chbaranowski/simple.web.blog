var demoApp = angular.module('demoApp', ['ngResource']);

demoApp.factory('productResource', ['$resource', function($resource) {
	return $resource( '/services/products/:productId', { productId: '@productId' }, {
	});
}]);

demoApp.controller('ProductController',['$scope','productResource', function($scope, productResource) {
	
	$scope.products = productResource.query();
	
	$scope.addProduct = function() {
		productResource.save({}, { "name":"New Product via HTML","price":"22,50"}).$promise.then(function() {
			$scope.products = productResource.query()
		})
	};
	
	$scope.deleteProduct = function(product) {
		productResource.delete({productId: product.id}).$promise.then(function() {
			$scope.products = productResource.query()
		})
	}
	
}]);
