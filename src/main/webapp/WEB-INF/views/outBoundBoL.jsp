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
        <sec:authentication property="principal" var="userLogged" />
    <center>
        <br/><br/><h4><spring:message code="outBoundBoL" /></h4><br/><br/>
        <table>
            <tr>
                <td><spring:message code="outBoundBoL.bolDate" /></td>
                <td style="width: 220px"><input type="text" id="datepickerBoLDate" disabled="true" maxlength="10" /></td>
                <td><spring:message code="outBoundBoL.shipDate" /></td>
                <td><input type="text" id="datepickerShipDate" disabled="true" maxlength="10"/></td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.customer" /></td>
                <td>
                    <select id="customersSelect" style="width: 160px">
                        <option value="select"><spring:message code="defaultSelected" /></option>
                        <option value="select2"><spring:message code="defaultSelected" /></option>
                    </select>
                </td>
                <td><spring:message code="outBoundBoL.facility" /></td>
                <td>
                    <select id="facilitiesSelect" style="width: 160px">
                        <option value="select"><spring:message code="defaultSelected" /></option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.carrier" /></td>
                <td>
                    <select id="carriersSelect" style="width: 160px">
                        <option value="select"><spring:message code="defaultSelected" /></option>
                    </select>
                </td>
                <td><spring:message code="outBoundBoL.driver" /></td>
                <td>
                    <input type="text" id="txtDriver" style="width: 260px" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.inboundContId01" /></td>
                <td>
                    <input type="text" id="txtInboundContId01" style="width: 160px" maxlength="20"/>
                </td>
                <td><spring:message code="outBoundBoL.inboundBolId01" /></td>
                <td>
                    <input type="text" id="txtInboundBolId01" style="width: 160px" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.inboundContId02" /></td>
                <td>
                    <input type="text" id="txtInboundContId02" style="width: 160px" maxlength="20"/>
                </td>
                <td><spring:message code="outBoundBoL.inboundBolId02" /></td>
                <td>
                    <input type="text" id="txtInboundBolId02" style="width: 160px" maxlength="20"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <br/>
                    <fieldset style="height: 80px; width: 230px">
                        <legend><spring:message code="outBoundBoL.containerType" /></legend>

                        <div id="rContainerType">
                            <input type="radio" id="rdBtnISOContainer" value="ISO" name="rContainerType" checked="checked"/>
                            <label for="rdBtnISO"><spring:message code="outBoundBoL.iSOContainer" /></label>
                            <br/>
                            <input type="radio" id="rdBtnRailcar" value="Railcar" name="rContainerType"/>
                            <label for="rdBtnRailcar"><spring:message code="outBoundBoL.railcar" /></label>
                        </div>                        
                    </fieldset> 
                </td>
                <td colspan="2">
                    <br/>
                    <fieldset style="height: 80px; width: 230px">
                        <legend><spring:message code="outBoundBoL.productType" /></legend>

                        <div id="rProductType">
                            <input type="radio" id="rdBtnNaCNL" value="NACNL" name="rProductType" checked="checked"/>
                            <label for="rdBtnNaCNL"><spring:message code="outBoundBoL.naCNL" /></label>
                            <br/>
                            <input type="radio" id="rdBtnNaCNB" value="NACNB" name="rProductType"/>
                            <label for="rdBtnNaCNB"><spring:message code="outBoundBoL.naCNB" /></label>
                            <br/>
                            <input type="radio" id="rdBtnNaCNH" value="NACNH" name="rProductType"/>
                            <label for="rdBtnNaCNH"><spring:message code="outBoundBoL.naCNH" /></label>
                        </div>                        
                    </fieldset> 
                </td>
            </tr> 
            <tr>
                <td><br/><spring:message code="outBoundBoL.container" /></td>
                <td>
                    <br/><input type="text" id="txtContainer" style="width: 160px" maxlength="20"/>
                </td>
                <td><br/><spring:message code="outBoundBoL.nACNPCT" /></td>
                <td>
                    <br/><input type="text" id="txtNaCNPCT" disabled="true" style="width: 160px"/>
                </td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.tareWGT" /></td>
                <td>
                    <input type="text" id="txtTareWGT" disabled="true" style="width: 160px" maxlength="20"/>
                </td>
                <td><spring:message code="outBoundBoL.specificGR" /></td>
                <td>
                    <input type="text" id="txtSpecificGR" disabled="true" style="width: 160px"/>
                </td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.netWGT" /></td>
                <td>
                    <input type="text" id="txtNetWGT" disabled="true" style="width: 160px" maxlength="20"/>
                </td>
                <td><spring:message code="outBoundBoL.pH" /></td>
                <td>
                    <input type="text" id="txtPH" disabled="true" style="width: 160px"/>
                </td>
            </tr>
            <tr>
                <td><spring:message code="outBoundBoL.grossWGT" /></td>
                <td>
                    <input type="text" id="txtGrossWGT" disabled="true" style="width: 160px" maxlength="20"/>
                </td>
                <td><spring:message code="outBoundBoL.containedLts" /></td>
                <td>
                    <input type="text" id="txtContainedLts" disabled="true" style="width: 160px"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>

                </td>
                <td><spring:message code="outBoundBoL.containedKgs" /></td>
                <td>
                    <input type="text" id="txtContainedKgs" style="width: 160px"/>
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
        <script src="<c:url value="/resources/cosysUtils.js"/>"></script>
        <script>
            $(function() {
                /*fields are declares here as they are required by different functions*/
                var bolDate = $('#datepickerBoLDate'),                
                shipDate = $('#datepickerShipDate'),
                customersSelect = $('#customersSelect'),
                facilitiesSelect = $('#facilitiesSelect'),
                carriersSelect = $('#carriersSelect'),
                driver = $('#txtDriver'),
                inboundContId01 = $('#txtInboundContId01'),
                inboundBolId01 = $('#txtInboundBolId01'),
                inboundContId02 = $('#txtInboundContId02'),
                inboundBolId02 = $('#txtInboundBolId02'),
                iSOContainer = $('#rdBtnISOContainer'),
                railcar = $('#rdBtnRailcar'),
                naCNL = $('#rdBtnNaCNL'),
                naCNB = $('#rdBtnNaCNB'),
                naCNH = $('#rdBtnNaCNH'),
                container = $('#txtContainer'),
                naCNPCT = $('#txtNaCNPCT'),
                tareWGT = $('#txtTareWGT'),
                specificGR = $('#txtSpecificGR'),
                netWGT = $('#txtNetWGT'),
                ph = $('#txtPH'),
                grossWGT = $('#txtGrossWGT'),
                containedLts = $('#txtContainedLts'),
                containedKgs = $('#txtContainedKgs'),
                btnSave = $('#btnSave'),
                btnPrint = $('#btnPrint');
            
                var allFields = $([]).add(bolDate).add(shipDate).add(customersSelect)
                .add(facilitiesSelect).add(carriersSelect).add(driver).add(inboundContId01)
                .add(inboundBolId01).add(inboundContId02).add(inboundBolId02)
                .add(iSOContainer).add(railcar).add(naCNL)
                .add(naCNB).add(naCNH).add(container).add(naCNPCT).add(tareWGT).add(specificGR)
                .add(netWGT).add(ph).add(grossWGT).add(containedLts).add(containedKgs).add(btnSave).add(btnPrint);
		
                var customers = null;
                var facilities = null;
                var carriers = null;
                
                var currentUser = null;
                var CrudHandler = {};
                
                CrudHandler.getCustomers = function(){
                    /*$.get('roleController/getAll',function(data){
                        customers = data;
                        $.each(customers, function(index, value){
                            customersSelect.append($('<option></option>').attr('id',value.custId).text(value.custName));
                        });
                    });*/
                };
                
                CrudHandler.getFacilities = function(id){
                    facilitiesSelect.append($('<option></option>').attr('id',"exampleValue").text("example"));
                    
                    /*$.get('roleController/getAll',function(data){
                        facilities = data;
                        $.each(facilities, function(index, value){
                            facilitiesSelect.append($('<option></option>').attr('id',value.facilityId).text(value.facilityName));
                        });
                    });*/
                };
                CrudHandler.getCarriers = function(){
                    /*$.get('roleController/getAll',function(data){
                        carriers = data;
                        $.each(carriers, function(index, value){
                            carriersSelect.append($('<option></option>').attr('id',value.carrierId).text(value.carrierName));
                        });
                    });*/
                };
                
                CrudHandler.findContainerById = function(id){
                    alert(id);
                    //facilitiesSelect.append($('<option></option>').attr('id',"exampleValue").text("example"));
                    
                }
                
                CrudHandler.populateContainerTypeFields = function(){
                    //alert($("input[name='rContainerType']:checked").val());
                    if($("input[name='rContainerType']:checked").val() == 'ISO'){
                        container.focusout(function() {
                                
                            CrudHandler.findContainerById(container.val());
                        });
                        //calular los valores de la caja de texto
                        tareWGT.val('0');
                        netWGT.val('0');
                        grossWGT.val('0');
                    }else{
                        container.unbind('focusout');
                        tareWGT.val('');
                        netWGT.val('');
                        grossWGT.val('');
                    }
                }
                
                CrudHandler.populateProductTypeFields = function(){
                    //alert($("input[name='rContainerType']:checked").val());
                    if($("input[name='rProductType']:checked").val() == 'NACNL'){
                        naCNPCT.val('30.5');
                        specificGR.val('1.176');
                        ph.val('12.50');
                        containedLts.val('Calcular');
                        containedKgs.val('Liquido');
                    }else  if($("input[name='rProductType']:checked").val() == 'NACNB'){
                        naCNPCT.val('98.0');
                        specificGR.val('');
                        ph.val('');
                        containedLts.val('');
                        containedKgs.val('Briquetas');
                    }else  if($("input[name='rProductType']:checked").val() == 'NACNH'){
                        naCNPCT.val('98.0');
                        specificGR.val('');
                        ph.val('');
                        containedLts.val('');
                        containedKgs.val('Hojuelas');
                    }else{
                        naCNPCT.val('');
                        specificGR.val('');
                        ph.val('');
                        containedLts.val('');
                        containedKgs.val('');
                    }
                }
                
                CrudHandler.saveOutBoundBoL = function(){
                    alert("Save");
                };
                CrudHandler.printOutBoundBoL = function(){
                    alert("Print");
                };


                CrudHandler.init = function(){
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
                    
                    CrudHandler.getCustomers();
                    CrudHandler.getCarriers();
                    CrudHandler.populateContainerTypeFields();
                    CrudHandler.populateProductTypeFields();

                    $("input[name='rContainerType']:radio").bind( "change", function(event, ui) {
                        CrudHandler.populateContainerTypeFields();
                    });
                    
                    $("input[name='rProductType']:radio").bind( "change", function(event, ui) {
                        CrudHandler.populateProductTypeFields();
                    });
                    customersSelect.bind( "change", function(event, ui) {
                        CrudHandler.getFacilities(customersSelect.val());
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
    </center>
</body>
</html>
