'use strict';

describe('Controller: MainCtrl', function () {

  // load the controller's module
  beforeEach(module('blogApp'));

  var MainCtrl;
  var scope;
  var $httpBackend;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope, $injector) {
    scope = $rootScope.$new();
    $httpBackend = $injector.get('$httpBackend');
    $httpBackend.expect('GET', '/rest/blog').respond([{id: 1}, {id: 42}]);
    MainCtrl = $controller('MainCtrl', {
      $scope: scope
    });
  }));
  
  it('should send a request to delete a blog post', function () {
		$httpBackend.expect('DELETE', '/rest/blog/42').respond(200, 'success');
		$httpBackend.expect('GET',    '/rest/blog').respond([{id: 1}]);
		scope.deletePost({id: 42});
		$httpBackend.flush();
		expect(scope.posts.toString()).toBe([{id: 1}].toString());
  });

});
