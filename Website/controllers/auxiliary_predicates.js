app.controller("auxiliaryPredicatesCtrl", function($scope, $http, $location, $uibModal, dataService) {

	$scope.auxiliary_predicates = [];

	$http.get('./data/predicates.xml').then(function(response) { 
		var dom = parseXml(response.data);
		var json = xmlToJson(dom);
		$scope.auxiliary_predicates = json["predicates-set"].predicates["simple-predicate"];
	});

});