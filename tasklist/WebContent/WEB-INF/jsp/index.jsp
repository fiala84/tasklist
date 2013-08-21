<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js"></script>
<script>
function deleteTask(){
	$.ajax({
	    url: '/tasks/1',
	    type: 'DELETE',
	    data: 'ID=1&Name=John&Age=10', // or $('#myform').serializeArray()
	    success: function() { alert('DELETE completed'); }
	});	
}
</script>
<style type="text/css">
	body {
		font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	}
	.main {
		width: 600px;
		margin: 0px auto;
		border: 1px dotted black;
		padding: 20px;
	}
	.taskTable {
		border-collapse: collapse;
		margin: 20px auto;
		width: 500px;
	}
	.taskTable td {
		border-top: 1px solid #ddd;
		padding: 3px;
		height: 40px;
	}
	.taskTable th {
		font-size: 11px;
		text-align: left;
	}
	
	.taskTable tr.N {
		font-weight: bold;
	}
	
	.taskTable tr.Y {
		background-color: #99ff55;
	}
	.arrow-right-Y {
		width: 0; 
		height: 0; 
		border-top: 8px solid transparent;
		border-bottom: 8px solid transparent;		
		border-left: 8px solid #66FF33;
	}
	.arrow-right-N {
		width: 0; 
		height: 0; 
		border-top: 8px solid transparent;
		border-bottom: 8px solid transparent;		
		border-left: 8px solid #FF5522;
	}
	.button {
	  display: inline-block;
	  width: 40px;
	  height: 12px;
	  background: #CCDD66;
	  padding: 6px;
	  text-align: center;
	  font-weight: normal;
	  font-size: 11px;
	  border-radius: 5px;
	  color: #000000;
	  font-weight: normal;
	  text-decoration: none;
	  border: 1px solid #CCDD66;
	}
	
	.addButton {
	  width: 100px;
	  height: 40px;
	  line-height: 40px;
	  background: #CCDD66;
	  font-weight: bold;
	}
	
	.button:hover {
	  background: #FFFFFF;
	  border: 1px solid #CCDD66;
	  color: black;
	  cursor: pointer;
	}
</style>
    <body>
    	<div class="main">
    	<h2>Task list</h2>
        <table class="taskTable">
        	<thead>
        		<tr><th></th>
        			<th>TASK</th>
        			<th>DESCRIPTION</th>
        			<th>PRIORITY</th>
        		</tr>
        	</thead>
        	<c:forEach var="task" items="${tasksList}">
        		<tr>
        			<td><div class="arrow-right-${task.doneStatus}"></div></td>
        			<td>${task.name}</td>
        			<td>${task.description}</td>
        			<td>${task.priorityLabel}</td>
        			<td>
        				<a href="tasks/edit/${task.id}" class="button">EDIT</a>
        				<a onclick="deleteTask();" class="button">DELETE</a>
        			</td>
        		</tr>
        	</c:forEach>
        </table>
        <form style="width: 100px; margin: 0px auto;" method="post"><input class="button addButton" type="submit" value="ADD TASK"/></form>
        </div>
    </body>
</html>