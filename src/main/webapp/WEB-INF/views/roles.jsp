<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
    <head>
        <title><spring:message code="roles" /></title>
        <style>
        </style>
    </head>
    <body>
        <button id="newButton"><spring:message code="new" /></button>
        <script>
            $(function() {
                var CrudHandler = {};
                CrudHandler.init = function(){
                    /*to turn into jquery buttons al the button tags */            
                    $("button").button().click(function(){
                        $.ajax({
                            type:'POST',
                            url:'roleController/store',
                            data:"{\"name\":\"aa\", \"description\":\"baasss\"}",
                            contentType: "application/json",
                            success:function(xhr){
                                if(xhr instanceof Array){
                                    
                                }
                            },                                
                            error:function(xhr){
                                if(xhr.status===500){//bussiness exceptions
                                    var errors = $.parseJSON(xhr.responseText);
                                    $.each(errors,function(){
                                        alert('message : ' + this.defaultMessage);
                                    });
                                }else if (xhr.status===400){//validation errors
                                    var errors = $.parseJSON(xhr.responseText);
                                    $.each(errors, function(){
                                        alert('error in field : ' + this.field + ' from object : ' + this.objectName + ' message : ' + this.defaultMessage);
                                    });
                                }else{
                                    alert('unknown error');
                                }
                            }
                        });
                    });
                };
                CrudHandler.init();
            });
        </script>
    </body>
</html>