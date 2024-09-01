public class Deadline extends Task {

  // deadline return book /by Sunday
  private String by;
  public Deadline(String task, String by) {
    super(task);
    this.by = by;
  }

  @Override
  public String toFileFormat() {
    return "D | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this.getTask() + " | " + by;
  }
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}