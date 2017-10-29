package ua.nenya.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ua.nenya.domain.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long>{
}
