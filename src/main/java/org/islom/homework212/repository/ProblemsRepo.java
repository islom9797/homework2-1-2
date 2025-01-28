package org.islom.homework212.repository;

import org.islom.homework212.entity.Problems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemsRepo extends JpaRepository<Problems,Integer> {
}
