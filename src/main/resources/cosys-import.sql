insert into COUNTRIES(COUNTRY_ID, COUNTRY_NAME) values('MEX','Mexico');
insert into COUNTRIES(COUNTRY_ID, COUNTRY_NAME) values('USA','United States');
insert into COUNTRIES(COUNTRY_ID, COUNTRY_NAME) values('CAN','Canada');

insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'DIF', 'Distrito Federal');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'AGU', 'Aguascalientes');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'BCN', 'Baja California');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'BCS', 'Baja California Sur');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'CAM', 'Campeche');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'COA', 'Coahuila');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'COL', 'Colima');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'CHP', 'Chiapas');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'CHH', 'Chihuahua');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'DUR', 'Durango');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'GUA', 'Guanajuato');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'GRO', 'Guerrero');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'HID', 'Hidalgo');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'JAL', 'Jalisco');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'MEX', 'México');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'MIC', 'Michoacán');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'MOR', 'Morelos');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'NAY', 'Nayarit');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'NLE', 'Nuevo León');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'OAX', 'Oaxaca');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'PUE', 'Puebla');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'QUE', 'Querétaro');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'ROO', 'Quintana Roo');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'SLP', 'San Luis Potosí');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'SIN', 'Sinaloa');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'SON', 'Sonora');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'TAB', 'Tabasco');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'TAM', 'Tamaulipas');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'TLA', 'Tlaxcala');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'VER', 'Veracruz');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'YUC', 'Yucatán');
insert into STATES(COUNTRY_ID, STATE_ID, STATE_NAME) values('MEX', 'ZAC', 'Zacatecas');

insert into PRODUCTS(PRODUCT_ID, BOL_DESC, PRODUCT_DESC) values('NACNL', 'NACNL BOL DESC', 'NACNL DESC');
insert into PRODUCTS(PRODUCT_ID, BOL_DESC, PRODUCT_DESC) values('NACNB', 'NACNB BOL DESC', 'NACNB DESC');
insert into PRODUCTS(PRODUCT_ID, BOL_DESC, PRODUCT_DESC) values('NACNH', 'NACNH BOL DESC', 'NACNH DESC');

insert into COMPANIES(COMPANY_ID, COMPANY_NAME) values('POSABRO','POSABRO S.A. de C.V.');

insert into BRANCHES(COMPANY_ID, BRANCH_ID) values('POSABRO', 'COAHUILA BRANCH');

insert into CUSTOMERS(COMPANY_ID, CUST_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, CUST_NAME, TAX_ID, COUNTRY_ID, STATE_ID) values('POSABRO', 'CUST_1','Monclova', 'address line 1', null, 76121, 'Posabro Monclova', 'TX1', 'MEX', 'COA');
insert into CUSTOMERS(COMPANY_ID, CUST_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, CUST_NAME, TAX_ID, COUNTRY_ID, STATE_ID) values('POSABRO', 'CUST_2','Torreon', 'address line 1', null, 19212, 'Posabro Torreon', 'TX1', 'MEX', 'COA');

insert into CUSTOMER_FACILITIES(COMPANY_ID, CUST_ID, FACILITY_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, FACILITY_NAME, COUNTRY_ID, STATE_ID) values('POSABRO', 'CUST_1','FAC1', 'Ramos Arizpe', 'address line 1', null, 34121, 'Almacen', 'MEX', 'COA');
insert into CUSTOMER_FACILITIES(COMPANY_ID, CUST_ID, FACILITY_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, FACILITY_NAME, COUNTRY_ID, STATE_ID) values('POSABRO', 'CUST_1','FAC2', 'San Pedro', 'address line 1', null, 43212, 'Recepcion', 'MEX', 'COA');
insert into CUSTOMER_FACILITIES(COMPANY_ID, CUST_ID, FACILITY_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, FACILITY_NAME, COUNTRY_ID, STATE_ID) values('POSABRO', 'CUST_2','FAC1', 'San Isidro', 'address line 1', null, 43212, 'Salida', 'MEX', 'COA');
insert into CUSTOMER_FACILITIES(COMPANY_ID, CUST_ID, FACILITY_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, FACILITY_NAME, COUNTRY_ID, STATE_ID) values('POSABRO', 'CUST_2','FAC2', 'San Cristobal', 'address line 1', null, 43212, 'Comedor', 'MEX', 'COA');

insert into CARRIERS(CARRIER_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, CARRIER_NAME, TAX_ID, COUNTRY_ID, STATE_ID) values('CARR1', 'Hermosillo', 'address line 1', null, 32121, 'Transportes el calor', 'TX1', 'MEX', 'SON');
insert into CARRIERS(CARRIER_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, CARRIER_NAME, TAX_ID, COUNTRY_ID, STATE_ID) values('CARR2', 'Cd Obregon', 'address line 1', null, 32121, 'Transportes calurozo', 'TX1', 'MEX', 'SON');
insert into CARRIERS(CARRIER_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, CARRIER_NAME, TAX_ID, COUNTRY_ID, STATE_ID) values('CARR3', 'San Luis Rio Colorado', 'address line 1', null, 32121, 'Transportes el Giro', 'TX1', 'MEX', 'SON');
insert into CARRIERS(CARRIER_ID, CITY_NAME, ADDRESS_LINE_01, ADDRESS_LINE_02, ZIP_CODE, CARRIER_NAME, TAX_ID, COUNTRY_ID, STATE_ID) values('CARR4', 'Navojoa', 'address line 1', null, 32121, 'Transportes el Navojoense', 'TX1', 'MEX', 'SON');

insert into CONTAINERS(CONTAINER_ID, LTS_FULL_CAPACITY, LTS_FILL_CAPACITY, TARE_WGT, CONTAINER_TYPE) values('CONT1', 500.00, 510.00, 20.00, 'ISO');
insert into CONTAINERS(CONTAINER_ID, LTS_FULL_CAPACITY, LTS_FILL_CAPACITY, TARE_WGT, CONTAINER_TYPE) values('CONT2', 600.00, 620.00, 30.00, 'ISO');
insert into CONTAINERS(CONTAINER_ID, LTS_FULL_CAPACITY, LTS_FILL_CAPACITY, TARE_WGT, CONTAINER_TYPE) values('CONT3', 700.00, 710.00, 40.00, 'ISO');
insert into CONTAINERS(CONTAINER_ID, LTS_FULL_CAPACITY, LTS_FILL_CAPACITY, TARE_WGT, CONTAINER_TYPE) values('CONT4', 800.00, 810.00, 50.00, 'ISO');
insert into CONTAINERS(CONTAINER_ID, LTS_FULL_CAPACITY, LTS_FILL_CAPACITY, TARE_WGT, CONTAINER_TYPE) values('CONT5', 900.00, 910.00, 60.00, 'ISO');