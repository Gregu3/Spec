angular.module('spec').controller('UserController', function ($scope, $resource, $http) {
    $scope.message = 'Hello from UserController';

    $scope.saveUser = function () {
        var email = $scope.emailOfUser;
        var name = $scope.nameOfUser;
        var surname = $scope.surnameOfUser;
        var role = $scope.role;
        var password = $scope.passwordOfUser;
        if (role != true) {
            role = false;
        }
        var user = {
            email: email,
            firstName: name,
            lastName: surname,
            password: password
        };

        $http.post('/user/add/' + role, user).success(function () { //wywloujemy
            alert('Konto zostało utoworzone');
            $scope.emailOfUser = "";
            $scope.passwordOfUser = "";
            $scope.nameOfUser = "";
            $scope.surnameOfUser = "";
            $scope.role = false;

        }).error(function () {
            alert('Konto nie zostało utworzone. Możliwe że podany e-mail jest już używany.Podaj poprawne dane!');
        })
    };


});
