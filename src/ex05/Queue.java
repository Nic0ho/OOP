package ex05;

import ex04.Command;

/**
 * Представляє методи для додавання та вилучення задач
 * обробником потоку; шаблон Worker Thread
 * @author Артем Єдалов
 * @version 1.0
 * @see Command
 */
public interface Queue
{
    /**
     * Додає нову задачу до черги; шаблон Worker Thread
     * @param cmd задача, що реалізує {@linkplain Command}
     */
    void put(Command cmd);

    /**
     * Вилучає задачу з черги; шаблон Worker Thread
     * @return вилучена задача
     */
    Command take();
}