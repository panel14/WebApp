package com.example.demo.repository;

import com.example.demo.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointRepository extends JpaRepository<Point, Integer> {
    List<Point> findAllByUserId(int id);
}
