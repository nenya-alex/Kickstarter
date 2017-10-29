package ua.nenya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
