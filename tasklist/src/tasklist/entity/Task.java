package tasklist.entity;

public class Task {

	public enum Priority {
		LOW(1), MEDIUM(2), HIGH(3);
		private int code;

		private Priority(int c) {
			code = c;
		}

		public int getCode() {
			return code;
		}
	}

	private long id;
	private String name;
	private String description;
	private int priority;
	private boolean done;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public String getDoneStatus() {
		if (this.done)
			return "Y";
		else
			return "N";
	}
	public String getPriorityLabel(){
		if (Task.Priority.HIGH.code == this.priority) {
			return "High";
		} else if(Task.Priority.MEDIUM.code == this.priority){
			return "Medium";
		} else return "Low";
	}
	
	public Priority[] getPriorities(){
		return Task.Priority.values();
	}
}
