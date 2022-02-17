package com.example.demo.control;

import com.example.demo.classes.PointDTO;
import com.example.demo.classes.UserDTO;
import com.example.demo.components.PointChecker;
import com.example.demo.converters.PointConverter;
import com.example.demo.entity.Point;
import com.example.demo.entity.User;
import com.example.demo.jwt.JwtProvider;
import com.example.demo.service.PointService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/api/point")
public class PointsController {
    @Autowired
    PointChecker pointChecker;

    @Autowired
    PointConverter pointConverter;

    @Autowired
    UserService userService;

    @Autowired
    PointService pointService;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/get")
    public ResponseEntity<?> getPoints(@RequestHeader("Authorization") String token){
        token = token.substring(7);

        String login = jwtProvider.getLoginFromToken(token);
        User user = userService.findUserByLogin(login);

        List<Point> pointList = pointService.getAllByUserId(user.getId());

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("points", pointList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/reg")
    public ResponseEntity<?> registerPoint(@RequestHeader("Authorization") String token,
                                           @RequestBody PointDTO pointDTO){
        token = token.substring(7);

        long start = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        String login = jwtProvider.getLoginFromToken(token);
        Point point = pointConverter.convertToPoint(pointDTO);

        User user = userService.findUserByLogin(login);

        point.setUser(user);
        point.setResult(pointChecker.check(point));

        long subTime = System.currentTimeMillis() - start;

        point.setNow(format.format(start));
        point.setWorkTime(subTime);


        pointService.save(point);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("point", point);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
