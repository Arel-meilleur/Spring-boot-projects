package axxentis.intenship.laboratoireapi.serviceImplementations;

import axxentis.intenship.laboratoireapi.entities.Task;
import axxentis.intenship.laboratoireapi.repositories.TaskRepository;
import axxentis.intenship.laboratoireapi.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new InvalidConfigurationPropertyValueException("Task", id, "Ressouce inexistente"));

        String title = task.getTitle();
        if (!title.isEmpty() || !title.isBlank()){
            task.setTitle(title);
        }
        String description = task.getDescription();
        if (!description.isEmpty() || !description.isBlank()){
            task.setDescription(description);
        }

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
