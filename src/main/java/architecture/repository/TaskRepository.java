package architecture.repository;

import architecture.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TaskRepository {
    //Данная переменная едина для всех экземпляров данного класса
    //это не повод трогать код приложения, тут лучше поправить тесты
    private static AtomicLong ID_COUNTER = new AtomicLong();
    //В данном приложении для простоты будем использовать List для хранения задачек

    private List<Task> taskList;

    public TaskRepository() {
        this.taskList = new ArrayList<>();
    }

    public boolean add(Task task) {
        //наверное загрузка в лист объекта таск
        //у задачек должны быть IDишники. добавим счетчик, который будет считать IDшки
        task.setId(ID_COUNTER.incrementAndGet());
        return taskList.add(task);
    }

    public Task getTaskById(Long id) {
        if (id == null) {
            throw new RuntimeException("Не был передан ID");//не лучшая практика так делать (следует сделать свое исключение, например TaskNotFoundException)
        }

        return taskList.stream()
                .filter(saveTask -> saveTask.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Указанная задача не найдена"));
        //пора запустить наш первый тест!
    }

    public void editTask(Task task) {
        if(task==null){
            throw  new RuntimeException("Не удалось найти такую задачу");
        }

        Task savedTask = taskList.stream()
                .filter(editTask -> editTask.equals(task))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Не удалось найти задачу"));
        //Задачу мы нашли теперь нужно обновить ее все поля, кроме ID

        savedTask.setAssignTo(task.getAssignTo());
        savedTask.setAuthor(task.getAuthor());
        savedTask.setDone(task.isDone());
    }

    public void removeTask(Task task) {
        //сейчас тоже скопируем немного кода, и уже существующего здесь
        //=====в целом данные строчки можно вынести в отдельный метод
        if(task==null){
            throw  new RuntimeException("Не удалось найти такую задачу");
        }

        Task removeTask = taskList.stream()
                .filter(editTask -> editTask.equals(task))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Не удалось найти задачу"));
        //=====коды выше можно выделить в отдельный метод
        this.taskList.remove(task);
    }
}
