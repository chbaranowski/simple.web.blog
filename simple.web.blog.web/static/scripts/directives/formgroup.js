'use strict';

/**
 * @ngdoc directive
 * @name blogApp.directive:formGroup
 * @description
 * # formGroup
 */
angular.module('blogApp')
  .directive('formGroup', function () {
    return {
      restrict: 'E',
      scope: {
    	id: 	'@',
        title:  '@',
        type:   '@',
        model:	'='
      },
      template: '<div class="form-group">' +
    	  '  <label for="{{id}}">{{title}}:</label>' +
    	  '  <input type="{{type}}" class="form-control" id="{{id}}" placeholder="{{title}}" ng-model="model">' +
    	  '</div>'
    };
  });

