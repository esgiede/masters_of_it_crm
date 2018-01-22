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
                $scope.employees = response.data;
            });
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