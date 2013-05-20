<%-- 
    Document   : outBoundBoL
    Created on : 11/05/2013, 01:09:46 PM
    Author     : jaime.hesiquio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title><spring:message code="outBoundBoL" /></title>
        <style>
            td.right{text-align: right}
        </style>
    </head>
    <body>
        <div id="formDiv"  class="ui-accordion ui-widget ui-helper-reset">
            <h3 class="ui-state-active ui-corner-top ui-accordion-content ui-helper-reset"><spring:message code="outBoundBoL"/></h3>
            <div class="ui-state-highlight ui-corner-bottom ui-helper-reset form-frame">
                <table>
                    <tr>
                        <td><spring:message code="outBoundBoL.bolDate" /></td>
                        <td style="width: 350px">
                            <span class="validateTips"></span>
                            <input type="text" id="bolDateInput" style="width: 150px" disabled="true" maxlength="10" class="text ui-widget-content ui-corner-all"/>
                        </td>
                        <td ><spring:message code="outBoundBoL.shipDate" /></td>
                        <td style="width: 350px">
                            <span class="validateTips"></span>
                            <input type="text" id="shipmentDateInput" disabled="true" maxlength="10" style="width: 150px" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.customer" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <select id="customerIdSelect" style="width: 160px" >
                                <option value="select"><spring:message code="defaultSelected" /></option>
                            </select>
                        </td>
                        <td><spring:message code="outBoundBoL.facility" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <select id="facilityIdSelect" style="width: 160px">
                                <option value="select"><spring:message code="defaultSelected" /></option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.carrier" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <select id="carrierIdSelect" style="width: 160px">
                                <option value="select"><spring:message code="defaultSelected" /></option>
                            </select>
                        </td>
                        <td><spring:message code="outBoundBoL.driver" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="driverInput" style="width: 260px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.inboundContId01" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="inboundBolData_inbouundContId1Input" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                        <td><spring:message code="outBoundBoL.inboundBolId01" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="inboundBolData_inbouundBolId1Input" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.inboundContId02" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="inboundBolData_inbouundContId2Input" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                        <td><spring:message code="outBoundBoL.inboundBolId02" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="inboundBolData_inbouundBolId2Input" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <br/>   
                            <fieldset style="height: 80px; width: 230px">
                                <legend><spring:message code="outBoundBoL.containerType" /></legend>

                                <div id="containerTypeRB">
                                    <input type="radio" id="rdBtnISOContainer" value="ISO" name="containerTypeRB" checked="checked"/>
                                    <label for="rdBtnISO"><spring:message code="outBoundBoL.iSOContainer" /></label>
                                    <br/>
                                    <input type="radio" id="rdBtnRailcar" value="RAILCAR" name="containerTypeRB"/>
                                    <label for="rdBtnRailcar"><spring:message code="outBoundBoL.railcar" /></label>
                                </div>                        
                            </fieldset> 
                        </td>
                        <td colspan="2">
                            <br/>
                            <fieldset style="height: 80px; width: 230px">
                                <legend><spring:message code="outBoundBoL.productType" /></legend>
                                <div id="productIdRB">
                                    <input type="radio" id="rdBtnNaCNL" value="NACNL" name="productIdRB" checked="checked"/>
                                    <label for="rdBtnNaCNL"><spring:message code="outBoundBoL.naCNL" /></label>
                                    <br/>
                                    <input type="radio" id="rdBtnNaCNB" value="NACNB" name="productIdRB"/>
                                    <label for="rdBtnNaCNB"><spring:message code="outBoundBoL.naCNB" /></label>
                                    <br/>
                                    <input type="radio" id="rdBtnNaCNH" value="NACNH" name="productIdRB"/>
                                    <label for="rdBtnNaCNH"><spring:message code="outBoundBoL.naCNH" /></label>
                                </div>                        
                            </fieldset> 
                        </td>
                    </tr> 
                    <tr>
                        <td><br/><spring:message code="outBoundBoL.container" /></td>
                        <td>
                            <br/>
                            <span class="validateTips"></span>
                            <input type="text" id="containerIdInput" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>

                        </td>
                        <td><br/><spring:message code="outBoundBoL.nACNPCT" /></td>
                        <td>
                            <br/>
                            <span class="validateTips"></span>
                            <input type="text" id="txtNaCNPCT" disabled="true" style="width: 160px" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.tareWGT" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="txtTareWGT" disabled="true" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                        <td><spring:message code="outBoundBoL.specificGR" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="txtSpecificGR" disabled="true" style="width: 160px" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.netWGT" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="txtNetWGT" disabled="true" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                        <td><spring:message code="outBoundBoL.pH" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="txtPH" disabled="true" style="width: 160px" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td><spring:message code="outBoundBoL.grossWGT" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="txtGrossWGT" disabled="true" style="width: 160px" maxlength="20" class="text ui-widget-content ui-corner-all"/>
                        </td>
                        <td><spring:message code="outBoundBoL.containedLts" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="txtContainedLts" disabled="true" style="width: 160px" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>

                        </td>
                        <td><spring:message code="outBoundBoL.containedKgs" /></td>
                        <td>
                            <span class="validateTips"></span>
                            <input type="text" id="content_containedKgsInput" style="width: 160px" class="text ui-widget-content ui-corner-all"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                    <center>
                        <button id="btnSave"><spring:message code="outBoundBoL.save" /></button>
                    </center>
                    </td>
                    <td colspan="2">
                    <center>
                        <button id="btnPrint"><spring:message code="outBoundBoL.print" /></button>
                    </center>
                    </td>
                    </tr>
                </table>
            </div>
        </div>
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here as they are required by different functions*/
                var bolDate = $('#bolDateInput'),                
                shipDate = $('#shipmentDateInput'),
                customerIdSelect = $('#customerIdSelect'),
                facilityIdSelect = $('#facilityIdSelect'),
                carrierIdSelect = $('#carrierIdSelect'),
                driver = $('#driverInput'),
                inboundContId01 = $('#inboundBolData_inbouundContId1Input'),
                inboundBolId01 = $('#inboundBolData_inbouundBolId1Input'),
                inboundContId02 = $('#inboundBolData_inbouundContId2Input'),
                inboundBolId02 = $('#inboundBolData_inbouundBolId2Input'),
                iSOContainer = $('#rdBtnISOContainer'),
                railcar = $('#rdBtnRailcar'),
                naCNL = $('#rdBtnNaCNL'),
                naCNB = $('#rdBtnNaCNB'),
                naCNH = $('#rdBtnNaCNH'),
                container = $('#containerIdInput'),
                naCNPCT = $('#txtNaCNPCT'),
                tareWGT = $('#txtTareWGT'),
                specificGR = $('#txtSpecificGR'),
                netWGT = $('#txtNetWGT'),
                ph = $('#txtPH'),
                grossWGT = $('#txtGrossWGT'),
                containedLts = $('#txtContainedLts'),
                containedKgs = $('#content_containedKgsInput'),
                btnSave = $('#btnSave'),
                btnPrint = $('#btnPrint');
            
                var allFields = $([]).add(bolDate).add(shipDate).add(customerIdSelect)
                .add(facilityIdSelect).add(carrierIdSelect).add(driver).add(inboundContId01)
                .add(inboundBolId01).add(inboundContId02).add(inboundBolId02)
                .add(iSOContainer).add(railcar).add(naCNL)
                .add(naCNB).add(naCNH).add(container).add(naCNPCT).add(tareWGT).add(specificGR)
                .add(netWGT).add(ph).add(grossWGT).add(containedLts).add(containedKgs);
                
                var inputFields = $([]).add(driver).add(inboundContId01)
                .add(inboundBolId01).add(inboundContId02).add(inboundBolId02)
                .add(container).add(naCNPCT).add(tareWGT).add(specificGR)
                .add(netWGT).add(ph).add(grossWGT).add(containedLts).add(containedKgs);
                
                var selectFields = $([]).add(customerIdSelect).add(facilityIdSelect).add(carrierIdSelect);
                /*
                 */
		
                var customers = null;
                var facilities = null;
                var carriers = null;
                var containers = null;
                var currentUser = null;
                var CrudHandler = {};
                
                CrudHandler.getCustomers = function(){
                    $.post('customerController/findCustomers', function(data){
                        customers = data;
                        customerIdSelect.empty();
                        customerIdSelect.append(new Option('<spring:message code="defaultSelected" />', 'select'));
                        $.each(customers, function(index, value){                             
                            customerIdSelect.append(new Option(value.name, value.customerPK.id));
                        });
                    });
                };                
                
                CrudHandler.getFacilities = function(id){                    
                    var successCallback = function(data){
                        facilities = data;
                        facilityIdSelect.empty();
                        facilityIdSelect.append(new Option('<spring:message code="defaultSelected" />', 'select'));
                        $.each(facilities, function(index, value){                             
                            facilityIdSelect.append(new Option(value.name, value.facilityPK.id));
                        });
                    };
                    var errorCallback = function(xhr){
                        ExceptionHandler.handleAjax(xhr);
                    };
                    $.ajax({
                        type: 'POST',
                        contentType: "application/json",
                        url: 'facilityController/findFacilitiesByCustomer',
                        data: id,
                        success: successCallback,
                        error:errorCallback
                    });                    
                };
                CrudHandler.getCarriers = function(){
                    $.post('carrierController/getAllCarriers', function(data){
                        carriers = data;
                        carrierIdSelect.empty();
                        carrierIdSelect.append(new Option('<spring:message code="defaultSelected" />', 'select'));
                        $.each(carriers, function(index, value){                             
                            carrierIdSelect.append(new Option(value.name, value.id));
                        });
                    });
                };
                
                CrudHandler.findContainerById = function(id){ 
                    if(id != ""){
                        //alert(containers);
                        var successCallback = function(data){                            
                            //alert(jQuery.isEmptyObject({}));                            
                            if(data.id){
                                Validator.cleanErrors(container);
                                containers = data;
                                //calular los valores de la caja de texto
                                tareWGT.val('0');
                                netWGT.val('0');
                                grossWGT.val('0');
                                containedLts.val(containers.ltsFillCapacity);
                                if($("input[name='productIdRB']:checked").val() == 'NACNL'){
                                    containedKgs.attr("disabled",true);
                                    if(data.ltsFillCapacity != ''){
                                        var result = containers.ltsFillCapacity * specificGR.val() * ph.val();
                                        containedKgs.val(result);
                                    }								
                                }else{
                                    containedKgs.attr("disabled",false);
                                    containedKgs.val('');
                                }
                            }else{
                                containers = null;
                                containedLts.val('');
                                Validator.updateError(container,'<spring:message code="outBoundBoL.containerNotExist"/>');
                                
                                
                            }                        
                        };
                        var errorCallback = function(xhr){
                            ExceptionHandler.handleAjax(xhr);
                        };
                        tareWGT.val('');
                        netWGT.val('');
                        grossWGT.val('');
						
                        $.ajax({
                            type: 'POST',
                            contentType: "application/json",
                            url: 'containerController/findContainer',
                            data: id,
                            success: successCallback,
                            error:errorCallback
                        });
                    }
                }
                
                CrudHandler.populateContainerTypeFields = function(){                    
                    if($("input[name='containerTypeRB']:checked").val() == 'ISO'){
                        if(container.val() != ""){
                            CrudHandler.findContainerById(container.val());
                        }                        
                        container.focusout(function() {                                
                            CrudHandler.findContainerById(container.val());
                        });
                    }else{
                        container.unbind('focusout');  
                        containedLts.val('');
                        containedKgs.val('');
                    }
                }
                
                CrudHandler.populateProductTypeFields = function(){
                    var successCallback = function(data){
                        //alert(JSON.stringify(data));
                        naCNPCT.val(data.nacnPct + ' %');
                        specificGR.val(data.specificGravity);
                        ph.val(data.ph);
                        
                        //containedLts.val($("input[name='productIdRB']:checked").val());
                        
                        if($("input[name='productIdRB']:checked").val() == 'NACNL'){
                            containedKgs.attr("disabled",true);
                            
                            if(containers != null){
                                //alert(containers.ltsFillCapacity);
                                if(containers.ltsFillCapacity!= ''){
                                    containedLts.val(containers.ltsFillCapacity);
                                    var result = containers.ltsFillCapacity * data.specificGravity *data.ph;
                                    containedKgs.val(result);
                                }
                            }
							
                        }else{
                            containedKgs.attr("disabled",false);
                            containedKgs.val('');
                            containedLts.val('');
                        }
                    };
                    var errorCallback = function(xhr){
                        ExceptionHandler.handleAjax(xhr);
                    };
                    $.ajax({
                        type: 'POST',
                        contentType: "application/json",
                        url: 'productController/findProductById',
                        data: $("input[name='productIdRB']:checked").val(),
                        success: successCallback,
                        error:errorCallback
                    });
                }
                
                CrudHandler.saveOutBoundBoL = function(){
                    //alert(shipDate.datepicker({ dateFormat: "yy-mm-dd" }).val());
                    if(CrudHandler.validate()){
                        var dataOutBoundBoL = CrudHandler.getOutBoundBoL();
                        var successCallback = function(){
                            alert("Saved");
                            CrudHandler.cleanScreen();
                            //CrudHandler.init();
                            //CrudHandler.clear();
                        };
                        var errorCallback = function(xhr){
                            //alert("aqui");
                            //alert(JSON.stringify(xhr));
                            ExceptionHandler.handleAjax(xhr);
                        };
                        //alert(JSON.stringify(dataOutBoundBoL));
                        $.ajax({type: "POST",
                            url:'billOfLoadingController/storeOutboundBol',
                            data:JSON.stringify(dataOutBoundBoL),
                            contentType: "application/json",
                            success:successCallback,
                            error:errorCallback
                        });
                    }
                };
                CrudHandler.printOutBoundBoL = function(){
                    alert("Print");
                };
                
                CrudHandler.cleanScreen = function(){
                    allFields.removeClass('ui-state-error');
                    Validator.cleanErrors(driver);
                    Validator.cleanErrors(inboundContId01);
                    Validator.cleanErrors(inboundBolId01);
                    Validator.cleanErrors(inboundContId02);
                    Validator.cleanErrors(inboundBolId02);
                    Validator.cleanErrors(container);
                    Validator.cleanErrors(naCNPCT);
                    Validator.cleanErrors(tareWGT);
                    Validator.cleanErrors(specificGR);
                    Validator.cleanErrors(netWGT);
                    Validator.cleanErrors(ph);
                    Validator.cleanErrors(grossWGT);
                    Validator.cleanErrors(containedLts);
                    Validator.cleanErrors(containedKgs);
                    customerIdSelect[0].selectedIndex = 0;
                    facilityIdSelect.empty();
                    facilityIdSelect.append(new Option('<spring:message code="defaultSelected" />', 'select'));
                    carrierIdSelect[0].selectedIndex = 0;
                    
                    bolDate.datepicker('setDate', '+0');
                    shipDate.datepicker('setDate', '+0');
                    $("input[name='containerTypeRB']:checked").val('ISO');
                    $("input[name='productIdRB']:checked").val('NACNL');
                    
                    containers = null;
                    CrudHandler.populateContainerTypeFields();
                    CrudHandler.populateProductTypeFields();
                    
                };
                CrudHandler.validate = function(){
                    var isValid = true;
                    if(customerIdSelect.val() == 'select'){
                        isValid = false;
                        Validator.updateError(customerIdSelect,'<spring:message code="outBoundBoL.customerIdSelect"/>');
                    }else{
                        Validator.cleanErrors(customerIdSelect);
                    }
                    if(facilityIdSelect.val() == 'select'){
                        isValid = false;
                        Validator.updateError(facilityIdSelect,'<spring:message code="outBoundBoL.facilityIdSelect"/>');
                    }else{
                        Validator.cleanErrors(facilityIdSelect);
                    }
                    if(carrierIdSelect.val() == 'select'){
                        isValid = false;
                        Validator.updateError(carrierIdSelect,'<spring:message code="outBoundBoL.carrierIdSelect"/>');
                    }else{
                        Validator.cleanErrors(carrierIdSelect);
                    }
					
                    if($("input[name='containerTypeRB']:checked").val() == 'ISO'){
                        if(driver.val() == ""){
                            isValid = false;
                            Validator.updateError(driver,'<spring:message code="outBoundBoL.driveEmpty"/>');
                        }else{
                            Validator.cleanErrors(driver);
                        }                       
                        
                    }
                    
                    return isValid;
                };
                
                CrudHandler.getOutBoundBoL = function(){                    
					
                    var bolDateTemp = $.datepicker.formatDate('yy-mm-dd', bolDate.datepicker('getDate'));
                    var shipDateTemp = $.datepicker.formatDate('yy-mm-dd', shipDate.datepicker('getDate'));					
                    var data = {
                        "inboundBolData":{
                            "inbouundBolId1":(inboundBolId01.val()=="" ? null : inboundBolId01.val()),
                            "inbouundBolId2":(inboundBolId02.val() == "" ? null : inboundBolId02.val()),
                            "inbouundContId1":(inboundContId01.val()=="" ? null : inboundContId01.val()),
                            "inbouundContId2":(inboundContId02.val()=="" ? null : inboundContId02.val())
                        },
                        "content":{
                            "containedKgs":(containedKgs.val()=="" ? null : containedKgs.val())
                        },
                        "bolDate":bolDateTemp,//bolDate.val(),
                        "carrierId":carrierIdSelect.val(),
                        "containerId":(container.val()=="" ? null : container.val()),
                        "containerType":$("input[name='containerTypeRB']:checked").val(),
                        "customerId":customerIdSelect.val(),
                        "driver":(driver.val()=="" ? null : driver.val()),
                        "facilityId":facilityIdSelect.val(),
                        "productId":$("input[name='productIdRB']:checked").val(),
                        "shipmentDate":shipDateTemp//shipDate.val()
                    };
                    return data;
                };

                CrudHandler.init = function(){
                    containers = null;
                    $("button").button();                   
                    bolDate.datepicker({
                        showOn: "button",
                        buttonImage: "<%=request.getContextPath()%>/resources/images/calendar.gif",
                        buttonImageOnly: true,
                        dateFormat:'<spring:message code="jsDefaultFormatDate" />',
                        defaultDate: new Date(),
                        minDate:new Date()
                    });
                    shipDate.datepicker({
                        showOn: "button",
                        buttonImage: "<%=request.getContextPath()%>/resources/images/calendar.gif",
                        buttonImageOnly: true,
                        dateFormat:'<spring:message code="jsDefaultFormatDate" />',
                        defaultDate: new Date(),
                        minDate:new Date()
                    });
                    bolDate.datepicker('setDate', '+0');
                    shipDate.datepicker('setDate', '+0');
                    naCNPCT.mask('99.99 %');
                    specificGR.mask('9.9999');
                    ph.mask('99.99');
                    containedLts.mask('999,999.99');
                        
                    CrudHandler.getCustomers();
                    CrudHandler.getCarriers();
                    CrudHandler.populateContainerTypeFields();
                    CrudHandler.populateProductTypeFields();

                    $("input[name='containerTypeRB']:radio").bind( "change", function(event, ui) {
                        CrudHandler.populateContainerTypeFields();
                    });
                    
                    $("input[name='productIdRB']:radio").bind( "change", function(event, ui) {
                        CrudHandler.populateProductTypeFields();
                    });
                    customerIdSelect.bind( "change", function(event, ui) {						
                        CrudHandler.getFacilities(customerIdSelect.val());
                    });

                    btnSave.click(function(){
                        CrudHandler.saveOutBoundBoL();
                    });
                    
                    btnPrint.click(function(){
                        CrudHandler.printOutBoundBoL();
                    });
                };
                
                CrudHandler.init();
            });
        </script>

    </body>
</html>
