package axxentis.intenship.laboratoireapi.services;

import axxentis.intenship.laboratoireapi.entities.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    // Get all tasks
    List<Task> getTasks();

    // Get task by id
    Optional<Task> getTaskById(Long id);

    //Create task
    Task saveTask(Task task);

    // Update task
    Task updateTask(Long id);

    // Delete Task by id
    void deleteTask(Long id);
}
