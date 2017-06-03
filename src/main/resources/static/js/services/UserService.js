angular.module('spec').service('UserService', function ($http, $localStorage) {

    this.getUserById = function (id) {
        var url = '/user/id/' + id;
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            alert(response.status);
            return response.status;
        });
    };

    this.addUserCar = function (car) {
        var url = '/user/putRelation/' + $localStorage.currentUser.id;
        return $http({
            method: "POST",
            url: url,
            data: car
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    };

    this.deleteUserCar = function (carId) {
        var url = '/user/deleteCar/id/' + $localStorage.currentUser.id + "/" + carId;
        return $http({
            method: "DELETE",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    };

});