package architecture.repository;

import architecture.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    //В данном приложении для простоты будем использовать List для хранения задачек

    private List<Task> taskList;

    public TaskRepository(){
        this.taskList = new ArrayList<>();
    }
}
