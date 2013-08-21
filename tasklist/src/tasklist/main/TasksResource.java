package tasklist.main;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import tasklist.db.DbUtils;
import tasklist.entity.Task;

import com.sun.jersey.api.view.Viewable;

@Path("tasks")
public class TasksResource {

	@GET
	public Viewable index(@Context HttpServletRequest request) {
		request.setAttribute("tasksList", DbUtils.loadTasks());
		return new Viewable("/index.jsp", null);
	}

	@GET
	@Path("/edit/{id}")
	public Viewable editTask(@Context HttpServletRequest request,
			@PathParam("id") long tId) {
		request.setAttribute("task", DbUtils.getTask(tId));
		return new Viewable("/edit.jsp", null);
	}

	@POST
	public Viewable newTask(@Context HttpServletRequest request) {
		request.setAttribute("task", new Task());
		return new Viewable("/edit.jsp", null);
	}

	@DELETE
	@Path("/{id}")
	public Viewable deleteTask(@Context HttpServletRequest request,
			@PathParam("id") long tId) {
		DbUtils.deleteTask(tId);
		return new Viewable("/index.jsp", null);
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/edit")
	public Viewable saveTask(@FormParam("taskName") String name,
			@FormParam("taskDesc") String taskDesc,
			@FormParam("priority") String priority,
			@FormParam("done") boolean done, @Context HttpServletRequest request) {
		Task t = new Task();
		t.setName(name);
		t.setDescription(taskDesc);
		t.setPriority(Integer.parseInt(priority));
		t.setDone(done);
		DbUtils.insertTask(t);
		return new Viewable("/index.jsp", null);
		//return Response.created(URI.create("/taskList/")).build();
	}
}