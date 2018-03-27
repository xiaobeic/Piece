package com.jjg.repository;

import com.jjg.model.JJG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JJGRepository extends JpaRepository<JJG, Integer> {
}
