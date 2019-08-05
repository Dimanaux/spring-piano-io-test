<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Questions</title>

    <style>
        .green {
            color: white;
            background-color: #3fff76;
        }

        .grey {
            border-width: 1px;
            color: darkgrey;
        }
    </style>
</head>
<body>
<form method="get">
    <label>
        Search in title:
        <input type="text" name="query">
        <input type="submit" value="Search!">
    </label>
</form>

<table border="1">
    <thead>
    <tr>
        <td>title</td>
        <td>author</td>
        <td>date</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${questions}" var="q">
        <tr class="${q['isAnswered'] ? 'green' : 'grey'}">
            <td><a href="${q['link']}">${q['title']}</a></td>
            <td> ${q['author']} </td>
            <td><a href="${q['link']}">${q['date']}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>