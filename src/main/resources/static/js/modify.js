var app = angular.module('app', []);
app.controller('MainController', function ($rootScope, $scope, $http) {

        $scope.data = {};

        //注册
        $scope.register = function () {
            if (document.getElementById('phonenumber').value == "") {
                alert("手机号码不能为空！")
            } else if (document.getElementById('phonenumber').value.length != 11) {
                alert("号码位数不对！")
            }else if (document.getElementById('password').value == "") {
                alert("密码不能为空！")
            } else if (document.getElementById('confirm_password').value == "") {
                alert("确认密码不能为空！")
            }  else
                $http({
                    url: '/modify',
                    method: 'POST',
                    data: $scope.data
                }).success(function (result) {
                        if (result.phonenumber == "gaihaomaweizhuce") {
                            alert("该号码未注册！");
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