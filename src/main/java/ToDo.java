public class ToDo extends Task {

  // todo borrow book
  public ToDo (String task) {
    super(task);
  }

  @Override
  public String toFileFormat() {
    return "T | " + (this.getBooleanStatus() ? "1" : "0") + " | " + this.getTask();
  }
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

}