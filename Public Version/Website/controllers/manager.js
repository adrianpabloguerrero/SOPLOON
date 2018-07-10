app.controller("managerCtrl", function($scope, $http, dataService) {
    $scope.name = "";

	$scope.add = function() {
		if ($scope.name != "") {
			var name = $scope.name;
			$http.post('http://localhost:8080/manwe/api/', name).then(function(response) { 
				$scope.instances.push(response.data);
				$scope.name = "";
			});
		}
	};
	
	$scope.remove = function(instance) {
		var name = instance.path.substr(instance.path.lastIndexOf('/')+1,instance.path.length);

		$http({
			method: 'DELETE',
			url: 'http://localhost:8080/manwe/api/',
			data: name,
			headers: {
				'Content-type': 'application/json;charset=utf-8'
			}
		}).then(function(response) {
			$scope.instances.removeElement(instance);
		});

	};

	$scope.edit = function(instance) {
		dataService.data.instance = instance;
	};
		
	$http.get('http://localhost:8080/manwe/api/').then(function(response) { 
		$scope.instances = response.data; 
	});
	
}); 