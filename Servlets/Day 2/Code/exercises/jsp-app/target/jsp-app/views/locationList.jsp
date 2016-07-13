<%@ page import="ro.teamnet.zth.appl.dao.LocationDao" %>
<%@ page import="ro.teamnet.zth.appl.domain.Location" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Employee List</title>
</head>
<body>
<%! List<Location> locationList = new LocationDao().getAllLocations(); %>
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
        for (Location location : locationList) {
    %>
    <tr>
        <td>
            <%=location.getId()%>
        </td>
        <td>
            <%=location.getStreetAddress()%>
        </td>
        <td>
            <%=location.getPostalCode()%>
        </td>
        <td>
            <%=location.getCity()%>
        </td>
        <td>
            <%=location.getStateProvince()%>
        </td>
        <td>
            <a href="locationView.jsp?id=<%=location.getId()%>">View</a>
        </td>
    </tr>
    <%
        }%>
    </tbody>
</table>

</body>
</html>
