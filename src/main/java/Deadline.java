import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

  // deadline return book /by Sunday
  private LocalDateTime by;
  public Deadline(String task, String by) {
    super(task);
    this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
  }

  @Override
  public String toFileFormat() {
    return "D | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this.getTask() + " | " + by;
  }
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
  }
}