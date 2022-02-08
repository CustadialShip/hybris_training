<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>CookPage</title>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
</head>
<table>
    <tr>
        <td>
            <h2>Name of cook</h2>
        </td>
        <td>
            <h2>Dishes</h2>
        </td>
    </tr>
    <c:forEach items="${cookList}" var="cook">
        <tr>
            <td>${cook.name}</td>
            <td>
                <c:forEach items="${cook.dish}" var="dish">
                    ${dish.name}
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
</html>