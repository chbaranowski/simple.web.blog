'use strict';

describe('Controller: PostCtrl', function () {

  // load the controller's module
  beforeEach(module('blogApp'));

  var PostCtrl;
  var scope;
  var $httpBackend;
  var $location;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $injector) {
    scope = $rootScope.$new();
    $httpBackend = $injector.get('$httpBackend');
    $location = $injector.get('$location');
    PostCtrl = $controller('PostCtrl', {
      $scope: scope
    });
  }));
  
  it('should send a request to save a blog post', function () {
	$httpBackend.expect('POST', '/rest/blog').respond(200, 'success');
	scope.savePost({title: 'Title', content: '-'});
	$httpBackend.flush();
	expect($location.path()).toBe('/');
  });
  
});
