<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" %>
<%@ page import="ro.teamnet.zth.appl.domain.Location" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Locations List</title>
</head>
<body>
<% Location currentLocation = new LocationDao().getLocationById(Integer.parseInt(request.getParameter("id"))); %>
<table border="1">
    <thead>
    <tr>
        <td>Id</td>
        <td>Street address</td>
        <td>Postal code</td>
        <td>City</td>
        <td>State province</td>
    </tr>
    </thead>
    <tbody>
    <%
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    %>
    <tr>
        <!--TODO de completat cu cod pentru a afisa detaliile locatiei cu id-ul trimis din locationlist.jsp in momentul in care se acceseaza link-ul 'View'-->
        <td>
            <%=currentLocation.getId()%>
        </td>
        <td>
            <%=currentLocation.getStreetAddress()%>
        </td>
        <td>
            <%=currentLocation.getPostalCode()%>
        </td>
        <td>
            <%=currentLocation.getCity()%>
        </td>
        <td>
            <%=currentLocation.getStateProvince()%>
        </td>
    </tr>

    </tbody>
</table>
<a href="locationList.jsp">Locations List</a>
</body>
</html>
