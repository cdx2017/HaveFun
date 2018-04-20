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
                            /*window.location.href = result.nextgo;*/
                            $scope.post(result.nextgo, {"name":result.name});
                        } else
                            alert("用户名或密码错误！")
                    }
                );
        };

        $scope.post = function (URL, PARAMS) {
            var temp = document.createElement("form");
            temp.action = URL;
            temp.method = "post";
            temp.style.display = "none";
            for (var x in PARAMS) {
                var opt = document.createElement("textarea");
                opt.name = x;
                opt.value = PARAMS[x]; // alert(opt.name)
                temp.appendChild(opt);
            }
            document.body.appendChild(temp);
            temp.submit();
            return temp;
        };
    }
)
;