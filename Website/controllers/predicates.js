app.controller("predicatesCtrl", function($scope, $http, $location, $uibModal, dataService) {

	$scope.predicates = [];

	$http.get('./data/predicate_names.xml').then(function(response) { 
		var dom = parseXml(response.data);
		var json = xmlToJson(dom);
		console.log(json);
		$scope.predicates = json["predicate-names"].predicates.predicate;
	});

});