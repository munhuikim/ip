public abstract class Task {
  private String task;
  private boolean isDone;

  public Task(String task) {
    this.task = task;
    this.isDone = false;
  }

  public String getStatus() {
    if (isDone) {
      return "[X]";
    }
    return "[ ]";
  }

  public Boolean getBooleanStatus() {
    return this.isDone;
  }

  public String getTask() {
    return this.task;
  }

  public void changeDoneStatus(boolean status) {
    this.isDone = status;
  }

  public abstract String toFileFormat();

  @Override
  public String toString() {
    return getStatus() + " " + task; // [X] read book
  }
}