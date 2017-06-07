angular.module('spec').controller('AddAdController', function ($scope, AdvertisementService, $routeParams, $rootScope) {
    $scope.message = 'Hello from AddAdController';
    $scope.gallery = [];
    $scope.categoryList = AdvertisementService.getCategories();
    console.log($scope.categoryList);
    $scope.advertisement = {};

    var findOne = function (id) {
        AdvertisementService
            .findOne(id)
            .then(function (response) {
                console.log(response.data);
                if (response.status == 200) {
                    $scope.advertisement = response.data;

                    alert("Powodzenie");
                } else {
                    alert("Wystąpił problem przy dodawaniu ogłoszenia.\nSpróbuj ponownie za chwilę za utrudnienia przepraszamy");
                }
            });
    };

    if ($routeParams.id != undefined) {
        findOne($routeParams.id);
    }

    $scope.addAd = function () {
        // $scope.advertisement.image = $scope.gallery[0].replace('data:image/jpeg;base64,', '');
        console.log("komentarz" + $scope.advertisement);
        $scope.advertisement.category = 'Banan';
        console.log($scope.advertisement);
        AdvertisementService
            .addAd($scope.advertisement)
            .then(function (response) {
                console.log("komentarzpo dodamie" + $scope.advertisement);

                if (response.status == 200) {
                    console.log("komentarz w ifie jeśli status OK" + $scope.advertisement);
                    console.log($scope.advertisement);
                    $scope.advertisement = response.data;
                    //$rootScope.currentUser.advertisement.push(response.data);
                    // UserAdsService.sendAdvertisement(response.data);
                    // UserService
                    //     .editUser($scope.loginUser)
                    //     .then(function (response) {
                    //         if (response.status == 200) {
                    //             $scope.loginUser = response.data;
                    //             AdvertisementService
                    //                 .getUserAd($scope.loginUser.id)
                    //                 .then(function (response) {
                    //                     if (response.status == 200) {
                    //                         $scope.myAd = response.data;
                    //                     }
                    //                 });
                    alert("Powodzenie");
                } else {
                    alert("Wystąpił problem przy dodawaniu ogłoszenia.\nSpróbuj ponownie za chwilę za utrudnienia przepraszamy");
                }
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
});
