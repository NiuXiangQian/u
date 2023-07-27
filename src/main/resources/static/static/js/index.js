layui.config().use(['form','jquery','layer'], function(){

   var $ = layui.$;
   var form = layui.form;


    form.on("submit(generate)",function(){
        var  url = $('#url_textarea').val()
        if (url.length<1) {
            layer.alert("请输入url")
        }else {
            index  =null
            $.ajax({//异步请求返回给后台
                url:'/url/generate',
                type:'POST',
                data: {
                    "url":url
                },
                dataType:'json',
                beforeSend: function(re){
                    index = top.layer.msg('生成中',{icon: 16,time:false});
                },
                success:function(d){
                    var r=d.result;
                    console.log(r);
                    if(d.success){
                        top.layer.msg("生成成功！");
                        $('#result_a').text(d.data.shortUrl).attr("href",d.data.shortUrl)
                        $('#result_div').css("display","block")
                    }else {
                        top.layer.msg("生成失败！");
                    }
                    layer.close(index)

                }
            })


        }
        return false;
    })

});
