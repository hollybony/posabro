<html>
    <head>

    </head>
    <body>
        <table id="usersTable" class="display">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Last name</th>                  
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Pedro</td>
                    <td>Perez</td>
                </tr>
                <tr>
                    <td>Quino</td>
                    <td>Quinonez</td>
                </tr>
                <tr>
                    <td>Raul</td>
                    <td>Ramirez</td>
                </tr>
                <tr>
                    <td>Samuel</td>
                    <td>Soto</td>
                </tr>
            </tbody>
        </table>
        <script>
        </script>
        <script>
            (function($) {
                $('#usersTable').dataTable(/*{
                    'bJQueryUI': true,
                    'bProcessing': true,
                    'bServerSide': true,
                    'bDestroy' : true,
                    'bPaginate': true,
                    'sAjaxSource': '<%=request.getContextPath()%>/userController/' + service,
                    'fnServerParams': function ( aoData ) {
                        aoData.push( argument );
                    },
                    'aoColumns': [
                        { 'mData': 'name'},
                        { 'mData': 'creationDate'},
                    ],
                    'sPaginationType': 'full_numbers'
                });             
            }*/);
                })(jQuery);
        </script>
    </body>
</html>