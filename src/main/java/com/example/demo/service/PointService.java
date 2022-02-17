package com.example.demo.service;

import com.example.demo.entity.Point;
import com.example.demo.repository.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointService {

    @Autowired
    PointRepository pointRepository;

    public void save(Point point){
        pointRepository.save(point);
    }

    public List<Point> getAllByUserId(int id){
        return pointRepository.findAllByUserId(id);
    }
}
