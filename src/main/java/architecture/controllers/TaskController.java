package architecture.controllers;

import architecture.service.TaskService;

public class TaskController {

    private TaskService taskService;

    //Мы не будем сразу писать код данных классов, для начала напишем для них тесты согласно TDD
    public TaskController(){
        this.taskService = new TaskService();
    }
}
