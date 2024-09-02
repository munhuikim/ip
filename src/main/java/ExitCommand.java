/**
 * Command to exit the application.
 */
public class ExitCommand extends Command {

  /**
   * Constructor for ExitCommand.
   */
  public ExitCommand() {
    this.isExit = true;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showGoodbye();
  }
}
