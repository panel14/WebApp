package com.example.demo.components;

import com.example.demo.entity.Point;
import org.springframework.stereotype.Component;

@Component
public class PointChecker {
    private int getQuarter(double x, double y){
        if (x > 0 && y > 0)
            return 1;
        if (x < 0 && y > 0)
            return 2;
        if(x < 0 && y < 0)
            return 3;
        if (x > 0 && y < 0)
            return 4;
        return 0;
    }
    public boolean check(Point point){
        double x = point.getX();
        double y = point.getY();
        double r = point.getR();
        int quarter = getQuarter(x, y);
        switch (quarter){
            case 0: return true;
            case 1:
                if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2, 2))
                    return true;
                break;
            case 2:
                if (Math.abs(x) <= r && Math.abs(y) <= (r / 2))
                    return true;
                break;
            case 3:
                return false;
            case 4:
                if (y >= 2 * x - r)
                    return true;
                break;
        }
        return false;
    }
}
