
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">





<style>
body{
     background: linear-gradient(135deg, #71b7e6, #9b59b6);
	color:white
}
body a{
color:white
}

</style>
</head>
<body>
<h1>Read Users</h1>
    <table class="table table-bordered" border="2" width="70%" cellpadding="2">
        <tr>
            <th>Username</th>
             <th>First Name</th>
             <th>Last Name</th>
            <th>Update</th>
            <th>Delete</th> 
        </tr>
        <c:forEach var="user" items="${users1}">
            <tr>
                <td>${user.userName}</td>
                <td>${user.userFirstName}</td>
              	 <td>${user.userLastName}</td>
                <td><a href="/updateuser/${user.userName}">Update</a></td>
               <td><a href="/delete/${user.userName}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
<br/>
<a href="/signup">Create User</a>
<a href="/login">Logout</a>
</body>
</html>