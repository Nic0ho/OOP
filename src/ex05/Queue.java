package ex05;

import ex04.Command;

public interface Queue
{
    void put(Command cmd);
    Command take();
}