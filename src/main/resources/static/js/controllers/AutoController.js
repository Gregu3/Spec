/**
 * Created by Dominika on 2017-04-07.
 */
angular.module('spec').controller('AutoController', function ($scope, $resource, $http, $rootScope, LoginService, AutoService, UserService) {
        $scope.message = "AutoController";
        $scope.gallery = [];
        $scope.items = [];
        $scope.selected = [];
        var idUser = function () {
            LoginService.getCurrentUser().then(function (response) {
                if (response.status == 200) {
                    $scope.currentUserID = response.data.id;
                    showMe(response.data.id);
                }
            })
        };
        idUser();

        $scope.saveAuto = function () {
            if ($scope.gallery[0].indexOf('data:image/jpeg;base64,') >= 0) {
                $scope.image = $scope.gallery[0].replace('data:image/jpeg;base64,', '');
            }
            if ($scope.gallery[0].indexOf('data:image/png;base64,') >= 0) {
                $scope.image = $scope.gallery[0].replace('data:image/png;base64,', '');
            }

            var autoObject = {
                image: $scope.image,
                marka: $scope.markaAuto,
                model: $scope.modelAuto,
                mocSilnika: $scope.mocAuto,
                numerRejestracyjny: $scope.numerRejAuto,
                numerVIN: $scope.numerVinAuto,
                pojemnoscSilnika: $scope.pojSilnikaAuto,
                rodzajNadwozia: $scope.rodzNadwoziaAuto,
                rodzajPaliwa: $scope.rodzajPaliwAuto,
                rokProdukcji: $scope.rokProAuto
            };

            AutoService.saveCar(autoObject).then(function (response) {
                if (response.status == 200) {
                    $scope.savedCar = response.data;
                    UserService.addUserCar($scope.savedCar).then(function (response2) {
                        if (response2.status == 200) {
                            alert("Auto zostało dodane");
                            showMe($scope.currentUserID);
                        }
                    })
                } else {
                    alert("Dodawanie auta nie powiodło się");
                }
            })
        };

        // wyświetlanie aut
        var loadAllCars = function () {

            var Car = $resource('auto/all', {}, {
                query: {method: 'get', isArray: true, cancellable: true}

            });

            Car.query(function (response) {

                // alert(response);
                $rootScope.car = response; // widoku będziesz używał teraz people
                // console.log(response);
            });
        };
        loadAllCars();

        $scope.deleteCar = function (id) {
            UserService.deleteUserCar(id).then(function (response2) {
                if (response2.status == 200) {
                   var autko = AutoService.deleteCar(id)

                        // .then(function (response) {
                        // if (response.status == 200) {
                        //     alert("Auto zostało usunięte");
                        // } else {
                        //     alert("Usuwanie auta nie powiodło się");
                        // }
                    // })
                }
                showMe($scope.currentUserID);
            });
        };

        $scope.showCar = function (Id) {


            $http({
                method: 'GET',
                url: '/auto/id/' + Id
            }).success(function (data) {

                $scope.idAutoE = data.id;
                $scope.markaE = data.marka;
                $scope.modelE = data.model;
                $scope.numerVinE = data.numerVIN;
                $scope.numerRejE = data.numerRejestracyjny;
                $scope.rokProE = new Date(data.rokProdukcji);
                $scope.rodzNadwoziaE = data.rodzajNadwozia;
                $scope.pojSilnikaE = data.pojemnoscSilnika;
                $scope.mocAutoE = data.mocSilnika;
                $scope.rodzajPaliE = data.rodzajPaliwa;

            })
                .error(function (error) {
                    //Showing error message
                    $scope.status = 'Unable to delete a person: ';
                });

        }

        $scope.editCar = function () {
            var carObj = {
                id: $scope.idAutoE,
                marka: $scope.markaE,
                model: $scope.modelE,
                numerVIN: $scope.numerVinE,
                numerRejestracyjny: $scope.numerRejE,
                rokProdukcji: $scope.rokProE,
                rodzajNadwozia: $scope.rodzNadwoziaE,
                pojemnoscSilnika: $scope.pojSilnikaE,
                mocSilnika: $scope.mocAutoE,
                rodzajPaliwa: $scope.rodzajPaliE
            };
            AutoService.updateCar(carObj).then(function (response) {
                if (response.status == 200) {
                    showMe($scope.currentUserID);
                } else {
                    alert("Nie udało się edytować auta");
                }
            });
        };

        var showMe = function (Id) {
            $http({
                method: 'GET',
                url: '/user/id/' + Id
            }).success(function (data) {
                $scope.me = data; // widoku będziesz używał teraz people
            }).error(function (error) {
                //Showing error message
                $scope.status = 'Unable to delete a person:';
            });
        };

//IMAGE PICKER
        (function ($) {

            $.fn.imagePicker = function (options) {

                // Define plugin options
                var settings = $.extend({
                    // Input name attribute
                    name: "",
                    // Classes for styling the input
                    class: "form-control btn btn-default btn-block",
                    // Icon which displays in center of input
                    icon: "glyphicon glyphicon-plus"
                }, options);

                // Create an input inside each matched element
                return this.each(function () {
                    $(this).html(create_btn(this, settings));
                });

            };

            // Private function for creating the input element
            function create_btn(that, settings) {
                // The input icon element
                var picker_btn_icon = $('<i class="' + settings.icon + '"></i>');

                // The actual file input which stays hidden
                var picker_btn_input = $('<input type="file" name="' + settings.name + '" />');

                // The actual element displayed
                var picker_btn = $('<div class="' + settings.class + ' img-upload-btn"></div>')
                    .append(picker_btn_icon)
                    .append(picker_btn_input);

                // File load listener
                picker_btn_input.change(function () {
                    if ($(this).prop('files')[0]) {
                        // Use FileReader to get file
                        var reader = new FileReader();

                        // Create a preview once image has loaded
                        reader.onload = function (e) {
                            var preview = create_preview(that, e.target.result, settings);
                            $(that).html(preview);
                        }

                        // Load image
                        reader.readAsDataURL(picker_btn_input.prop('files')[0]);
                        //MOJE
                        // alert(reader.readAsDataURL(picker_btn_input.prop('files')[0]));
                    }
                });

                return picker_btn
            };

            // Private function for creating a preview element
            function create_preview(that, src, settings) {

                // The preview image
                var picker_preview_image = $('<img src="' + src + '" class="img-responsive img-rounded" />');
                //MOJE
                // alert(src);
                // $scope.immmg = src;
                $scope.gallery.push(src);
                // The remove image button
                var picker_preview_remove = $('<button class="btn btn-warning"><small>' +
                    '<span class="glyphicon glyphicon glyphicon-trash"></span></small></button>');

                // The preview element
                var picker_preview = $('<div class="text-center"></div>')
                    .append(picker_preview_image)
                    .append(picker_preview_remove);

                // Remove image listener
                picker_preview_remove.click(function () {
                    var btn = create_btn(that, settings);
                    $(that).html(btn);
                    $scope.gallery.splice($scope.gallery.indexOf(src), 1);
                });

                return picker_preview;
            };

        }(jQuery));

        $(document).ready(function () {
            $('.img-picker').imagePicker({name: 'images'});
        })


    }
);
