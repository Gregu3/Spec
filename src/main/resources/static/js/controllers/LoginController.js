angular.module('spec').controller('LoginController', function ($http, $rootScope, $scope, LoginService, $location, $localStorage, $resource) {
    $rootScope.slider = false;
    $scope.message = "LoginController";
    $scope.errorMsg = '';
    $scope.rememberMe = true;
    $scope.username = '';
    $scope.password = '';

    $scope.login = function () {

        var userLoginAndPassword = {
            "username": $scope.username,
            "password": $scope.password
        };

        // console.log(userLoginAndPassword.password)
        if (userLoginAndPassword.password == "" || userLoginAndPassword.username == "") {
        } else {
            LoginService
                .login(userLoginAndPassword)
                .then(function (response) {
                        if (response.status == 200) {
                            LoginService
                                .getCurrentUser().then(function (response) {

                                $rootScope.currentUser = response.data;
                                $localStorage.currentUser = response.data;
                                $localStorage.showNavbar = true;
                                $localStorage.showTopMenu = true;
                                $rootScope.showNavbar = true;
                                $rootScope.showTopMenu = true;
                                $location.path('/');
                                console.log("login controller" + $rootScope.currentUser.username);
                            })
                        } else {
                            alert("Nie można poprawnie dokonać autoryzacji \nPrawdopodobną przyczyną jest zły email lub/i hasło");
                            $scope.errorMsg = 'Please check your credentials and try again.';
                        }
                    }
                )
        }
    }
})
;