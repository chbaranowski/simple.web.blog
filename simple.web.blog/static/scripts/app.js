'use strict';

/**
 * @ngdoc overview
 * @name blogApp
 * @description
 * # blogApp
 *
 * Main module of the application.
 */
angular
  .module('blogApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: '/static/views/main.html',
        controller: 'MainCtrl'
      })
      .when('/post/:postId', {
        templateUrl: '/static/views/post.html',
        controller: 'PostCtrl'
      })
      .when('/post', {
        templateUrl: '/static/views/post.html',
        controller: 'PostCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
  
angular.module('blogApp')
  .factory('$blogResource', ['$resource', function($resource) {
	return $resource( '/rest/blog/:postId', { postId: '@postId' }, { });
}]);