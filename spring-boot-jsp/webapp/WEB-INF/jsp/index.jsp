<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Static content -->
    <title>Spring Boot</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <script type="text/javascript" src="/resources/js/app.js"></script>
    <link rel='stylesheet' href='webjars/bootstrap/${bootstrap.version}/css/bootstrap.min.css'>
    <script type='text/javascript' src='webjars/bootstrap/${bootstrap.version}/js/bootstrap.min.js'></script>
    </head>

    <body>
        <div class="form">
            <form action="hello" method="post" onsubmit="return validate()">
                <table>
                    <tr>
                        <td>Enter your name</td>
                        <td><input id="name" name="name"></td>
                        <td><input type="submit" value="Submit"></td>
                    </tr>
                </table>
                </form>
        </div>

</body>
</html>