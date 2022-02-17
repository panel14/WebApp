package com.example.demo.converters;

import com.example.demo.classes.PointDTO;
import com.example.demo.entity.Point;
import org.springframework.stereotype.Component;

@Component
public class PointConverter {
    public Point convertToPoint(PointDTO pointDTO){
        Point newPoint = new Point();
        newPoint.setX(pointDTO.getX());
        newPoint.setY(pointDTO.getY());
        newPoint.setR(pointDTO.getR());
        return newPoint;
    }
}
