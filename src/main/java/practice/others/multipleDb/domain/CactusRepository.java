package practice.others.multipleDb.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CactusRepository extends JpaRepository<Cactus, Long> { }
