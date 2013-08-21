<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<style type="text/css">
	.main {
		width: 600px;
		margin: 0px auto;
		border: 1px dotted black;
		padding: 20px;
	}
	.editForm{
		margin-left: 50px;
	}
	.field {
		width: 80px;
		float: left;
	}
	.desc {
		width: 300px;
		height: 150px;
	}
</style>
    <body>
    	<div class="main">
    	<h2>Edit task: ${task.name}</h2>     
       	<form class="editForm" method="post" action="/tasklist/tasks/edit">
       		<p><label class="field">Task name: </label><input type="text" name="taskName" value="${task.name}"/></p>
       		<p><label class="field">Description: </label><textArea name="taskDesc" class="desc">${task.description}</textArea></p>
       		
    		<p><label class="field">Priority: </label>
    		<select name="priority">
    			<c:forEach var="priority" items="${task.priorities}">
    				<option value="${priority.code}">${priority}</option>
    			</c:forEach>
			</select></p>
			<p><label class="field">Done: </label><input type="checkbox" name="done" checked="${task.done}" onclick="this.value = this.checked;" /></p>
			<input type="submit" value="Save"/>
		</form>
		<a href="/tasklist/tasks" class="button">cancel</a>
       	</div>
    </body>
</html>