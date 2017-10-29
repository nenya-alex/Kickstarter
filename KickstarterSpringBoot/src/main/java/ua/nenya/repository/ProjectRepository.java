package ua.nenya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Project;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByCategoryId(Long categoryId);
}
