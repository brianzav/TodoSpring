package br.senac.sp.epictask.repository;

import br.senac.sp.epictask.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
