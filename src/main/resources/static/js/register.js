var app = angular.module('app', []);
app.controller('MainController', function ($rootScope, $scope, $http) {

        $scope.data = {};

        //发送
        $scope.send = function () {
            var phonenumber = document.getElementById('phonenumber').value;
            if (phonenumber == "") {
                alert("手机号码不能为空！")
            } else if (document.getElementById('phonenumber').value.length != 11) {
                alert("号码位数不对！")
            } else
                $http({
                    url: '/send',
                    method: 'POST',
                    data: phonenumber
                }).success(function (result) {
                        if (result == "204") {
                            alert("发送成功！");
                        } else if (result == "400")
                            alert("发送失败!");
                        else
                            alert("未知错误！")
                    }
                )
        };

        //验证
        $scope.check = function () {
            var phonenumber = document.getElementById('phonenumber').value;
            var checkword = document.getElementById('checkword').value;
            if (phonenumber == "") {
                alert("手机号码不能为空！")
            } else if (document.getElementById('phonenumber').value.length != 11) {
                alert("号码位数不对！")
            } else if (document.getElementById('name').value == "") {
                alert("用户名不能为空！")
            } else if (document.getElementById('password').value == "") {
                alert("密码不能为空！")
            } else if (document.getElementById('confirm_password').value == "") {
                alert("确认密码不能为空！")
            } else if (checkword == "") {
                alert("验证码不能为空！")
            } else
                $http({
                    url: '/check',
                    method: 'POST',
                    data: {"phonenumber": phonenumber, "checkword": checkword}
                }).success(function (result) {
                        if (result == "204") {
                            $scope.register();
                        } else if (result == "400")
                            alert("验证码错误!");
                        else
                            alert("验证发生未知错误");
                    }
                )
        };

        //注册
        $scope.register = function () {

            $http({
                url: '/register',
                method: 'POST',
                data: $scope.data
            }).success(function (result) {
                    if (result.phonenumber == "yijingcunzai") {
                        alert("该号码已经注册！");
                    } else if (result.length != 0 && result.phonenumber == $scope.data.phonenumber) {
                        self.location = 'index';
                    } else
                        alert("注册失败");
                }
            );
        };
    }
)
;