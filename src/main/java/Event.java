public class Event extends Task {

  // event project meeting /from Mon 2pm /to 4pm
  private String from;
  private String to;
  public Event(String task, String from, String to) {
    super(task);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toFileFormat() {
    return "E | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this.getTask() + " | " + from + " to " + to;
  }
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
  }
}
