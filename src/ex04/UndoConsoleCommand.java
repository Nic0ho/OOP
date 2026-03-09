package ex04;

public class UndoConsoleCommand implements ConsoleCommand
{
    private ChangeConsoleCommand change;

    public UndoConsoleCommand(ChangeConsoleCommand change)
    { this.change = change; }

    @Override
    public char getKey()
    { return 'u'; }

    @Override
    public String toString()
    { return "'u'ndo"; }

    @Override
    public void execute()
    {
        change.undo();
    }
}
