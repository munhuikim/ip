public abstract class Command {
  protected boolean isExit;

  public Command() {
    this.isExit = false;
  }

  public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws EliException;

  public boolean isExit() {
    return isExit;
  }
}
