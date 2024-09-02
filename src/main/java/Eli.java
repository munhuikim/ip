import java.util.Scanner;
public class Eli {
  private TaskList taskList;
  private Scanner scanner;

  public Eli() {
    this.taskList = new TaskList();
    this.scanner = new Scanner(System.in);
  }

  public void start() {
    String logo = "  _____   _       _____ \n"
            + " | ____| | |     |__ __|\n"
            + " | |___  | |       |_|  \n"
            + " |  ___| | |       |_|  \n"
            + " | |___| | |___    |_|  \n"
            + " |_____| |_____| |_____|\n";
    System.out.println("Hello from\n" + logo);

    // Greeting
    System.out.println("____________________________________________________________");
    System.out.println(" Hello! I'm Eli");
    System.out.println(" What is your tasks today?");
    System.out.println("____________________________________________________________");

    while (true) {

      String input = scanner.nextLine().trim();
      String[] inputParts = input.split(" ", 2); // mark 2 // unmark 2
      String instruction = inputParts[0];

      if (instruction.equalsIgnoreCase("bye")) {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Come back soon!");
        System.out.println("____________________________________________________________");
        break;
      }

      try {
        if (instruction.equalsIgnoreCase("list")) {
          taskList.list();
        } else if (instruction.equalsIgnoreCase("mark")) {
          if (inputParts.length < 2) {
            throw new EliException("Please specify the task number to mark.");
          }
          String taskIdxStr = inputParts[1];
          int taskIdx = Integer.valueOf(taskIdxStr);
          taskList.mark(taskIdx);
        } else if (instruction.equalsIgnoreCase("unmark")) {
          if (inputParts.length < 2) {
            throw new EliException("Please specify the task number to unmark.");
          }
          String taskIdxStr = inputParts[1];
          int taskIdx = Integer.valueOf(taskIdxStr);
          taskList.unmark(taskIdx);
        } else if (instruction.equalsIgnoreCase("delete")) {
          if (inputParts.length < 2) {
            throw new EliException("Please specify the task number to delete.");
          }
          String taskIdxStr = inputParts[1];
          int taskIdx = Integer.valueOf(taskIdxStr);
          taskList.delete(taskIdx);
        } else {
          taskList.addTask(input);
          //throw new UnknownCommandException();
        }
      } catch (UnknownCommandException e) {
        System.out.println("____________________________________________________________");
        // System.out.println(" Sorry I cannot understand :(");
        System.out.println(" " + e.getMessage());
        System.out.println("____________________________________________________________");
      } catch (EliException e) {
        System.out.println("____________________________________________________________");
        // System.out.println(" Sorry I cannot understand :(");
        System.out.println(" " + e.getMessage());
        System.out.println("____________________________________________________________");
      }
    }

    scanner.close();
  }
  public static void main(String[] args) {
    Eli eli = new Eli();
    eli.start();
  }
}
