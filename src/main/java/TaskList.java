import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskList {

  private ArrayList<Task>  tasks;
  //private int taskCount;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

  public TaskList() {
    this.tasks = new ArrayList<>();
    //this.taskCount = 0;
  }

  public void addTask(String task) throws EliException{
    // todo borrow book
    // deadline return book /by Sunday
    // deadline return book /by 2/12/2019 1800
    // event project meeting /from Mon 2pm /to 4pm

    if (tasks.size() < 100) {
      Task newTask = createTask(task);
      tasks.add(newTask);
      System.out.println("____________________________________________________________");
      System.out.println(" added: " + newTask);
      System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
      System.out.println("____________________________________________________________");

    } else {
      System.out.println("Task list is full!!!");
    }
  }

  private Task createTask(String task) throws EliException {
    String[] parts = task.split(" ", 2);
    String type = parts[0];
    String details = parts.length > 1 ? parts[1].trim() : "";

    if (details.isEmpty()) {
      throw new EliException(type);
    }

    switch (type.toLowerCase()) {
      case "todo":
        return new ToDo(details);
      case "deadline":
        String[] deadlineParts = details.split(" /by ", 2);
        if (deadlineParts.length < 2) {
          throw new EliException("Please provide a deadline in the format: 'deadline [description] /by [yyyy-MM-dd hhmm]'.");
        }
        return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
      case "event":
        String[] eventParts = details.split(" /from | /to ");
        if (eventParts.length < 3) {
          throw new EliException("Please provide an event in the format: 'event [description] /from [yyyy-MM-dd hhmm] /to [yyyy-MM-dd hhmm]'.");
        }
        return new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
      default:
        throw new UnknownCommandException();
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
}
