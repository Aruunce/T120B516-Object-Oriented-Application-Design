package clientSide.Commands;

public interface Command {
    void execute();
    void undo();
}