package architecture.repository;

import architecture.model.Task;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TaskRepositoryTest {

    //Начнем с уровня доступа к базе данных. Основные операции - это добавление/удаление/редактирование и получение задачек.

    @Test
    public void whenAddTaskShouldCheckThatTaskWasAdded(){
        //название метода - при Добавлении Задачи Следует Проверить, Что Задача Была Добавлена
        TaskRepository taskRepository = new TaskRepository();
        Task task = new Task();
        task.setDone(false);
        task.setAssignTo("SkillFactory Student");
        task.setAuthor("Blandinskiy Stepan");
        boolean added = taskRepository.add(task);//требует реализации в классе таскрепозиторий
        //мы добавили задачку, теперь надо проверить что она действительно сохранилась
        //для этого нам нужно получить только, что добавленную задачку по ID

        Task findByIdTask = taskRepository.getTaskById(1L);//требуется реализация в классе таксрепозиторий
        //TDD гласит, что в тестах должно быть 3А - Assing, action, assert (назначение, действие, утверждение)
        //Самое время для assert (утверждения)
        assertTrue(added);
        assertNotNull(findByIdTask);
        assertNotNull(findByIdTask.getId());
        assertThat("Не верное назначение для", findByIdTask.getAssignTo(), is("SkillFactory Student"));
        assertThat("Не верный автор", findByIdTask.getAuthor(), is("Blandinskiy Stepan"));
        assertThat("Не верная отметка о выполнении", findByIdTask.isDone(), is(false));
    }
    //тест прошел, но необходимо добавить еще функции - такие как редактирование/удаление и получение всего списка задач
    @Test
    public void whenEditTaskShouldCheckThatChangesSaved(){
        //название метода - при редактировании задачи следует проверить что изменения сохранены
        TaskRepository taskRepository = new TaskRepository();
        Task task = new Task();
        task.setDone(true);
        task.setAuthor("Blandinskiy Stepan");
        task.setAssignTo("SkillFactory Student");
        boolean added = taskRepository.add(task); //да первый тест нужно немного отредактировать

        Task findByIdTask = taskRepository.getTaskById(1L);

        //Сейчас нужно скопировать проверки из первого теста
        //в копи-пасте нет ничего страшного если она осмысленная
        //в данном случае лишняя проверка не помешает
        assertTrue(added);
        assertNotNull(findByIdTask);
        assertNotNull(findByIdTask.getId());
        assertThat("Не верное назначение для", findByIdTask.getAssignTo(), is("SkillFactory Student"));
        assertThat("Не верный автор", findByIdTask.getAuthor(), is("Blandinskiy Stepan"));
        assertThat("Не верная отметка о выполнении", findByIdTask.isDone(), is(false));

        //К этому моменту мы уже точно знаем, что задачка хранится в условной БД
        //ОТредатируем бд
        findByIdTask.setAssignTo("Blandinskiy Stepan");
        taskRepository.editTask(findByIdTask);

        //теперь проверим, что изменения действительно сохранились
        findByIdTask = taskRepository.getTaskById(1L);

        assertNotNull(findByIdTask);
        assertThat("Неверное назначение для", findByIdTask.getAssignTo(), is("Blandinskiy Stepan"));

    }
}