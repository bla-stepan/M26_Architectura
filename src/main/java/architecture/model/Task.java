package architecture.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Task {

    private Long id;

    private String author;

    private String assignTo;

    private boolean done;

    /**
     * Alt+insert => equals and HashCode()
     * Важно переопределять данный метод, потому что мы будем использовать его для сравнения объектов.
     * Это так называемое бизнес сравнение объектов, то есть если данный метод возвращает вызываемому коду true,
     * то это значит, что объекты являются идентичными.
     * @param o - объект, который будем сравнивать
     * @return - true - если объекты идентичны, в любом другом случае false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return done == task.done && Objects.equals(id, task.id) && Objects.equals(author, task.author) && Objects.equals(assignTo, task.assignTo);
    }

    /**
     * Этот объект тоже важно переопределить, потому что
     * а) потому что нельзя переопределить только equals
     * б) в случае неправильной реализации этого метода, при хранении данного объекта
     * в хэш структурах быкет может выродиться в связанный список, что негативно скажется на
     * работе приложения
     * @return хэш код объекта
     *  */
    @Override
    public int hashCode() {
        return Objects.hash(id, author, assignTo, done);
    }
}
