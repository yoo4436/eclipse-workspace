<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.sql" prefix="sql" %>
<c:catch var="err">
	<sql:setDataSource
		driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/iii"
		user="root"
		password="root"
	/>	
	<sql:update var="num">
		update cust
		set tel = '123'
		where id > 4
	</sql:update>
	<sql:update>
		delete from cust
		where id >= 6
	</sql:update>
</c:catch>
Update: ${num1 }<br />
Delete: ${num2 }<br />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		
	</body>
</html>