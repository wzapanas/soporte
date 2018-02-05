
        var v=true;
        $("span.help-block").hide();

        function verificar(){

            var v1=0,v2=0,v3=0,v4=0,v5=0,v6=0;
            v1=validacion('mIdIncidencia');
            v2=validacion('mNumIncidencia');
            v3=validacion('mIdTipIng');
            v4=validacion('mIdDependencia');
            v5=validacion('mIdSolicitante');
            v6=validacion('mDetIncidencia');
            v7=validacion('mIdUsuario');
            if (v1===false || v2===false || v3===false || v4===false || v5===false || v6===false || v7===false ) {
                 $("#exito").hide();
                 $("#error").show();
            }else{
                $("#error").hide();
                $("#exito").show();
            }
        } 
        
        function validacion(campo){
            var a=0;




            if (campo==='mIdIncidencia')
            {
                Inci = document.getElementById(campo).value;
                if( Inci == null || Inci.length == 0 || /^\s+$/.test(Inci) ) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;
                   
                }
                else
                {
                    if(isNaN(codigo)) {

                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("No Acepta letras").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");

              
                        return false;
                    }
                    else{

                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
             
                        return true;
                    }
                    
                    
                }
                
            }







            if (campo==='mNumIncidencia')
            {
                num = document.getElementById(campo).value;
                if( num == null || num.length == 0 || /^\s+$/.test(num) ) {
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese algo").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;
                   
                }
                else
                {
                    if(isNaN(codigo)) {

                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                        $('#'+campo).parent().children('span').text("No Acepta letras").show();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");

              
                        return false;
                    }
                    else{

                        $("#glypcn"+campo).remove();
                        $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                        $('#'+campo).parent().children('span').hide();
                        $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
             
                        return true;
                    }
                    
                    
                }
                
            }
           





            
            
            
            
            
            if (campo==='mIdTipIng'){
                tip = document.getElementById(campo).selectedIndex;
                if( tip == null || tip == 0 ) {
                    $('#mIdTipIng').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#mIdTipIng').parent().parent().attr("class", "form-group has-success");
                    return true;

                }
            }
            









            if (campo==='mIdDependencia'){
                dependencia = document.getElementById(campo).selectedIndex;
                if( dependencia == null || dependencia == 0 ) {
                    $('#mIdDependencia').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#mIdDependencia').parent().parent().attr("class", "form-group has-success");
                    return true;

                }
            }
            








            if (campo==='mIdSolicitante'){
                soli = document.getElementById(campo).selectedIndex;
                if( soli == null || soli == 0 ) {
                    $('#mIdSolicitante').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#mIdSolicitante').parent().parent().attr("class", "form-group has-success");
                    return true;

                }
            }
            




             if (campo==='mDetIncidencia'){
                det = document.getElementById(campo).value;
                if( det == null || det.length == 0 || /^\s+$/.test(det) ) {
                    
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-error has-feedback");
                    $('#'+campo).parent().children('span').text("Ingrese Incidencia").show();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-remove form-control-feedback'></span>");
                    return false;
                    
                }
                else{
                    $("#glypcn"+campo).remove();
                    $('#'+campo).parent().parent().attr("class", "form-group has-success has-feedback");
                    $('#'+campo).parent().children('span').hide();
                    $('#'+campo).parent().append("<span id='glypcn"+campo+"' class='glyphicon glyphicon-ok form-control-feedback'></span>");
                    return true;
                    
                } 
            }







            if (campo==='mIdUsuario'){
                usr = document.getElementById(campo).selectedIndex;
                if( usr == null || usr == 0 ) {
                    $('#mIdUsuario').parent().parent().attr("class", "form-group has-error");
                    return false;
                }
                else{
                    $('#mIdUsuario').parent().parent().attr("class", "form-group has-success");
                    return true;

                }
            }
           
        }
