Array.prototype.removeElement = function(elem) {
	var index = this.indexOf(elem);
	if (index >= 0) {
		this.splice(index,1);
		return true;
	}
	return false;
}

var app = angular.module("myApp", ["ngRoute", "ui.bootstrap"]);
	
app.service('dataService', function() {

  // private variable
  var _data = {};

  // public API
  this.data = _data;

})

app.config(function($routeProvider,$locationProvider) {
	$locationProvider.hashPrefix('');
	$routeProvider
	.when("/", {
		templateUrl : "./views/intro.htm"
	})
	.when("/install", {
		templateUrl : "./views/install.htm",
	})
	.when("/detect", {
		templateUrl : "./views/detect.htm",
	})
	.when("/customize", {
		templateUrl : "./views/customize.htm",
	})
	.when("/design", {
		templateUrl : "./views/design.htm",
	})
	.when("/info/predicates", {
		templateUrl : "./views/predicates.htm",
		controller : "predicatesCtrl"
	})
	.when("/info/rules", {
		templateUrl : "./views/rules.htm",
		controller : "rulesCtrl"
	})
	.when("/info/auxiliary_predicates", {
		templateUrl : "./views/auxiliary_predicates.htm",
		controller : "auxiliaryPredicatesCtrl"
	});
});