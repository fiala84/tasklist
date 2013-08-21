package tasklist.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tasklist.entity.Task;

public class DbUtils {
	private static String dbURL = "jdbc:derby://localhost:1527/myDB;create=true";
	private static String tableName = "tasks";
	// jdbc Connection
	private static Connection conn = null;
	private static Statement stmt = null;

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver")
						.newInstance();
				conn = DriverManager.getConnection(dbURL);
			} catch (Exception except) {
				except.printStackTrace();
			}
		}
		return conn;
	}

	public static void insertTask(Task t) {
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate("insert into " + tableName + " values ("
					+ t.getId() + ",'" + t.getName() + "','"
					+ t.getDescription() + "'," + t.getPriority() + ",'"
					+ t.getDoneStatus() + "')");
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static void updateTask(Task t) {
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate("update " + tableName + " set NAME = '"
					+ t.getName() + "', DESCRIPTION = '" + t.getDescription()
					+ "', PRIORITY = " + t.getPriority() + ", DONE = '"
					+ t.getDoneStatus() + "' WHERE ID = " + t.getId());
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static void deleteTask(long taskId) {
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate("delete from tasks where id=" + taskId);
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
	}

	public static List<Task> loadTasks() {
		List<Task> result = new ArrayList<Task>();
		try {
			stmt = getConnection().createStatement();
			ResultSet results = stmt.executeQuery("select * from " + tableName
					+ " order by priority desc");

			while (results.next()) {
				Task t = new Task();
				t.setId(results.getLong(1));
				t.setName(results.getString(2));
				t.setDescription(results.getString(3));
				t.setPriority(results.getInt(4));
				if ("Y".equals(results.getString(5)))
					t.setDone(true);
				else
					t.setDone(false);
				result.add(t);
			}
			results.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return result;
	}

	public static Task getTask(long tId) {
		Task t = new Task();
		try {
			stmt = getConnection().createStatement();
			ResultSet result = stmt.executeQuery("select * from " + tableName
					+ " where id=" + tId);
			if (result.next()) {
				t.setId(result.getLong(1));
				t.setName(result.getString(2));
				t.setDescription(result.getString(3));
				t.setPriority(result.getInt(4));
				if ("Y".equals(result.getString(5)))
					t.setDone(true);
				else
					t.setDone(false);
			}
			result.close();
			stmt.close();
		} catch (SQLException sqlExcept) {
			sqlExcept.printStackTrace();
		}
		return t;
	}
}
