angular.module('spec').service('AdvertisementService', function ($http) {

    this.addAd = function (ad) {
        var url = '/ad/add/';
        return $http({
            method: "POST",
            url: url,
            data: ad
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    };

    this.getCategories = function () {
        var url = '/ad/categories/';
        return $http({
            method: "GET",
            url: url
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    };



    this.deleteCar = function (id) {
        return $http({
            method: 'DELETE',
            url: '/auto/delete/id/' + id
        }).then(function successCallback(response) {
            alert(response.status);
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    };

    this.updateCar = function (car) {
        return $http({
            method: "POST",
            url: '/auto/update/',
            data: car
        }).then(function successCallback(response) {
            return response;
        }, function errorCallback(response) {
            return response.status;
        });
    };

});