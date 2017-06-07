angular.module('spec').controller('AdvController', function ($scope, AdvertisementService, $routeParams) {
    function loadAllAdv() {
        AdvertisementService
            .findAll()
            .then(function (response) {
                if (response.status == 200) {
                    $scope.allAdv = response.data;
                }
            });
    }

    loadAllAdv();
    });
