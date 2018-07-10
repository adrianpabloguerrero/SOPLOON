app.controller("instanceCtrl", function($scope, $http, $location, $uibModal, dataService) {

	$scope.instance = dataService.data.instance;

	if ($scope.instance == undefined) {
		$location.path("");
		$scope.instance = {};
	}
	else {
		$scope.instance.sub_path  = $scope.instance.path.substr($scope.instance.path.lastIndexOf('/')+1,$scope.instance.path.length);
		load();
		window.setInterval(load, 10000);
	}

	$scope.openEditor = function (name, sub_path) {
		$scope.to_edit = { "name": name, "path": "http://localhost:8080/manwe/api/" + $scope.instance.sub_path + sub_path };

		var modalInstance = $uibModal.open({
			templateUrl: './views/modal-edit.html',
			controller: 'editorCtrl',
			windowClass: 'col-xs-12',
			scope: $scope,
		});

		modalInstance.result.catch(function(res) {
			if (!(res === 'cancel' || res === 'escape key press')) {
				throw res;
			}
		});
	}

	$scope.openMap = function (action, sub_path, external_bounds) {

		$scope.to_map = { "action": action, "path": "http://localhost:8080/manwe/api/" + $scope.instance.sub_path + sub_path, "external_bounds": external_bounds };
		
		var modalInstance = $uibModal.open({
			templateUrl: './views/modal-map.html',
			controller: 'mapCtrl',
			windowClass: 'col-xs-12',
			scope: $scope,
		});
		
		modalInstance.result.then(function(){
			load();
		});

		modalInstance.result.catch(function(res) {
			if (!(res === 'cancel' || res === 'escape key press')) {
				throw res;
			}
		});

	}

	function load() {

		$http.get('http://localhost:8080/manwe/api/' + $scope.instance.sub_path).then(function(response) { 
			var aux = $scope.instance.sub_path
			$scope.instance = response.data;
			$scope.instance.sub_path = aux;
		});

	}
});