app.controller("rulesCtrl", function($scope, $http, $location, $uibModal, dataService) {

	$scope.rules = [];

	$http.get('./data/rules.xml').then(function(response) { 
		var dom = parseXml(response.data);
		var json = xmlToJson(dom);
		$scope.rules = json["rules-set"].rules["simple-rule"];
	});

});