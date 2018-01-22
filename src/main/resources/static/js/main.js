var mainApp = angular.module("mainApp", ['ngRoute']);

mainApp.config(function($routeProvider) {
	$routeProvider
		.when('/home', {
			templateUrl: 'home.html'
		})
		.when('/employee', {
			templateUrl: 'employee.html',
			controller: 'EmployeesCtrl'
		})
		.when('/employee-add', {
        	templateUrl: 'addEmployee.html',
        	controller: 'EmployeesCtrl'
        })
		.otherwise({
        	redirectTo: '/home'
        });
});

mainApp.controller('EmployeesCtrl', ['$scope','EmployeesService',
  function ($scope,EmployeesService) {

      $scope.sortType     = 'name';
      $scope.sortReverse  = false;

      $scope.getEmployees = function () {
          EmployeesService.getEmployees()
            .then(function success(response) {
                $scope.itemsPerPage = 10;
                $scope.currentPage = 0;
                $scope.employees = response.data;

                $scope.range = function() {
                    var rangeSize = 4;
                    var ps = [];
                    var start;

                    start = $scope.currentPage;
                    if ( start > $scope.pageCount()-rangeSize ) {
                      start = $scope.pageCount()-rangeSize+1;
                    }

                    for (var i=start; i<start+rangeSize; i++) {
                      if(i>=0)
                              ps.push(i);
                    }
                    return ps;
                  };

                  $scope.prevPage = function() {
                    if ($scope.currentPage > 0) {
                      $scope.currentPage--;
                    }
                  };

                  $scope.DisablePrevPage = function() {
                    return $scope.currentPage === 0 ? "disabled" : "";
                  };

                  $scope.pageCount = function() {
                    return Math.ceil($scope.employees.length/$scope.itemsPerPage)-1;
                  };

                  $scope.nextPage = function() {
                    if ($scope.currentPage < $scope.pageCount()) {
                      $scope.currentPage++;
                    }
                  };

                  $scope.DisableNextPage = function() {
                    return $scope.currentPage === $scope.pageCount() ? "disabled" : "";
                  };

                  $scope.setPage = function(n) {
                    $scope.currentPage = n;
                  };

                }
            )
      }

      $scope.addEmployee = function () {
          if ($scope.employee != null && $scope.employee.name) {
              EmployeesService.addEmployee($scope.employee.name, $scope.employee.lastName, $scope.employee.pesel,
              $scope.employee.address, $scope.employee.phone, $scope.employee.typeOfContract, $scope.employee.employedSince)
                .then (function success(response){
                    $scope.message = 'Employee added!';
                    $scope.errorMessage = '';
                },
                function error(response){
                    $scope.errorMessage = 'Error adding employee!';
                    $scope.message = '';
              });
          }
          else {
              $scope.errorMessage = 'Please enter a name!';
              $scope.message = '';
          }
      }

}]);

mainApp.service('EmployeesService', [ '$http', function($http) {

    this.getEmployees = function getEmployees() {
        return $http({
            method : 'GET',
            url : '/employees'
        });
    }

      this.addEmployee = function addEmployee(name, lastName, pesel, address, phone, typeOfContract, employedSince) {
          return $http({
              method : 'POST',
              url : 'employees',
              data : {
                  name : name,
                  lastName : lastName,
                  pesel : pesel,
                  address : address,
                  phone : phone,
                  typeOfContract : typeOfContract,
                  employedSince : employedSince
              }
          });
      }

} ]);

angular.module('mainApp').filter('pagination', function()
{
  return function(input, start) {
    if (!input || !input.length) { return; }
    start = parseInt(start, 10);
    return input.slice(start);
  };
});