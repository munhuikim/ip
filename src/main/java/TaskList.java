import java.io.*;
import java.util.ArrayList;

public class TaskList {

  private ArrayList<Task>  tasks;
  //private int taskCount;
  private static final String FILE_PATH = "./data/eli.txt";

  public TaskList() {
    this.tasks = new ArrayList<>();
    //this.taskCount = 0;
    loadTasksFromFile();
  }

  public void addTask(String task) throws EmptyDescriptionException, UnknownCommandException{
    // todo borrow book
    // deadline return book /by Sunday
    // event project meeting /from Mon 2pm /to 4pm

    String[] parts = task.split(" ", 2);
    String type = parts[0];
    String details = parts[1];

    if (details.isEmpty()) {
      throw new EmptyDescriptionException(type);
    }

    Task newTask;
    if (type.equalsIgnoreCase("todo")) {
      newTask = new ToDo(details);
    } else if (type.equalsIgnoreCase("deadline")) {
      String[] deadlineParts = details.split(" /by ");
      newTask = new Deadline(deadlineParts[0], deadlineParts[1]);
    } else if (type.equalsIgnoreCase("event")) {
      String[] eventParts = details.split(" /from | /to ");
      newTask = new Event(eventParts[0], eventParts[1], eventParts[2]);
    } else {
      // newTask = new Task(task);
      throw new UnknownCommandException();
    }
    if (tasks.size() < 100) {
      tasks.add(newTask);
      System.out.println("____________________________________________________________");
      System.out.println(" added: " + newTask);
      System.out.println("____________________________________________________________");

    } else {
      System.out.println("Task list is full!!!");
    }
  }

  public void list() {
    System.out.println("____________________________________________________________");
    System.out.println(" Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
    System.out.println("____________________________________________________________");
  }


  public void mark(int taskIdx) {
    System.out.println("____________________________________________________________");
    System.out.println(" Great job!");
    System.out.println("   " + tasks.get(taskIdx - 1));
    System.out.println("____________________________________________________________");
  }


  public void unmark(int taskIdx) {
    tasks.get(taskIdx - 1).changeDoneStatus(false);
    System.out.println("____________________________________________________________");
    System.out.println(" OK, I've marked this task as not done yet:");
    System.out.println("   " + tasks.get(taskIdx - 1));
    System.out.println("____________________________________________________________");
  }

  public void delete(int taskIdx) {
    Task removedTask = tasks.remove(taskIdx - 1);
    System.out.println("____________________________________________________________");
    System.out.println(" OK. I've removed this task:");
    System.out.println("   " + removedTask);
    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    System.out.println("____________________________________________________________");
  }
  private void saveTasksToFile() {
    try {
      File file = new File(FILE_PATH);
      file.getParentFile().mkdirs(); // Create parent directories if not exist
      FileWriter writer = new FileWriter(file);
      PrintWriter printWriter = new PrintWriter(writer);

      for (Task task : tasks) {
        printWriter.println(task.toFileFormat());
      }

      printWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred while saving tasks to file.");
    }
  }
  private void loadTasksFromFile() {

    try {
      File file = new File(FILE_PATH);
      if (!file.exists()) {
        return; // No file to load from
      }

      BufferedReader reader = new BufferedReader(new FileReader(file));
      String line;

      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        switch (type) {
          case "T":
            task = new ToDo(description);
            break;
          case "D":
            String by = parts[3];
            task = new Deadline(description, by);
            break;
          case "E":
            String fromTo = parts[3];
            String[] eventDetails = fromTo.split(" to ");
            task = new Event(description, eventDetails[0], eventDetails[1]);
            break;
          default:
            throw new IOException("Invalid task type in file.");
        }

        if (isDone) {
          task.changeDoneStatus(true);
        }

        tasks.add(task);
      }

      reader.close();
    } catch (IOException e) {
      System.out.println("An error occurred while loading tasks from file.");
    }
  }
}
