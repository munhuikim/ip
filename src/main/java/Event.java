import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  // event project meeting /from Mon 2pm /to 4pm
  private LocalDateTime from;
  private LocalDateTime to;

  public Event(String task, String from, String to) {
    super(task);
    this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a"))
            + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a")) + ")";
  }
}
