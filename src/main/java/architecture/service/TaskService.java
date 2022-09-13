package architecture.service;

import architecture.repository.TaskRepository;

public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(){
        this.taskRepository = new TaskRepository();

    }
}
