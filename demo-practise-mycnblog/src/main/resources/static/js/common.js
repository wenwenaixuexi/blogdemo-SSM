function logout(){
        if(confirm("确认注销？")){
            jQuery.ajax({
                url: "/user/logout",
                type:"POST",
                data:{},
                success:function(result){
                    if(result != null && result.code == 200){

                        location.href = "/login.html";
                    }

                }


            });

        }

    }

//获取当前url参数得公共方法
function getUrlValue(key){
    //ex:?id=1&v=2
    var params = location.search;
    if(params.length > 1){
        params = location.search.substring(1);
        var paramArr = params.split("&");
        for(var i =0; i < paramArr.length; i++){
            var kv = paramArr[i].split("=");
            if(kv[0] == key){

              return kv[1];
            }

        }

    }
    return "";
}