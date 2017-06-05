// create the module and name it scotchApp
var spec = angular.module('spec', ['ngRoute', 'ngResource', 'ngStorage', 'ui.bootstrap']);
spec.config(function ($routeProvider) {
    $routeProvider

        .when('/', {
            templateUrl: 'views/home.html',
            controller: 'HomeController'
        })
        .when('/login', {
            templateUrl: 'views/login.html',
            controller: 'LoginController'
        })
        .when('/register', {
            templateUrl: 'views/register.html',
            controller: 'UserController'
        })
        .when('/addAd', {
            templateUrl: 'views/addAd.html',
            controller: 'AddAdController'
        })
        .when('/contact', {
            templateUrl: 'views/contact.html',
            controller: 'ContactController'
        })
        .when('/selectedCategory', {
            templateUrl: 'views/selectedCategory.html',
            controller: 'SelectedCategoryController'
        })
        .when('/ad', {
            templateUrl: 'views/ad.html',
            controller: 'SelectedCategoryController'
        })
        .otherwise({redirectTo: '/'});
});

spec.run(function ($localStorage, $rootScope, LoginService, $location) {
    if ($localStorage.currentUser == undefined) {
        $localStorage.currentUser = null;
    }
});
