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
      $scope.getEmployee = function () {
              var id = $scope.employee.id;
              EmployeesService.getEmployee($scope.employee.id)
                .then(function success(response){
                    $scope.employee = response.data;
                    $scope.employee.id = id;
                    $scope.message='';
                    $scope.errorMessage = '';
                },
                function error (response ){
                    $scope.message = '';
                    if (response.status === 404){
                        $scope.errorMessage = 'Employee not found!';
                    }
                    else {
                        $scope.errorMessage = "Error getting Employee!";
                    }
                });
          }
}]);

mainApp.service('EmployeesService', [ '$http', function($http) {

    this.getEmployees = function getEmployees() {
        return $http({
            method : 'GET',
            url : '/employees'
        });
    }

    this.getEmployee = function getEmployee(employeeId){
            return $http({
              method: 'GET',
              url: 'employees/'+employeeId
            });
    	}

} ]);