var app = angular.module('app', []);
app.controller('MainController', function ($rootScope, $scope, $http) {

        $scope.data = {};

        //登录
        $scope.login = function () {
            if (document.getElementById('name').value == "") {
                alert("用户名不能为空！")
            } else if (document.getElementById('password').value == "") {
                alert("密码不能为空！")
            } else
                $http({
                    url: '/login',
                    method: 'POST',
                    data: $scope.data
                }).success(function (result) {
                        if (result.length != 0 && result.name == $scope.data.name
                            && result.password == $scope.data.password) {
                            self.location = 'register';
                        } else
                            alert("用户名或密码错误！")
                    }
                );
        };

    }
)
;