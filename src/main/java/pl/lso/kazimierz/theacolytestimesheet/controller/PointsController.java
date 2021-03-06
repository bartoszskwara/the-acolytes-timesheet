package pl.lso.kazimierz.theacolytestimesheet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.lso.kazimierz.theacolytestimesheet.model.builder.dto.PointsDtoBuilder;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.NewPoints;
import pl.lso.kazimierz.theacolytestimesheet.model.dto.points.PointsDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Points;
import pl.lso.kazimierz.theacolytestimesheet.service.PointsService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/points")
public class PointsController {

    private PointsService pointsService;

    @Autowired
    public PointsController(PointsService pointsService) {
        this.pointsService = pointsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity getPointsByUserId(@PathVariable("userId") Long userId) {
        List<Points> points = pointsService.getPointsByUserId(userId);
        int total = pointsService.getTotalPointsFromList(points);
        List<PointsDto> pointsDto = pointsService.mapPointsListToPointsDtoList(points);

        Map<String, Object> response = new HashMap<>();
        response.put("total", total);
        response.put("points", pointsDto);

        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping({"", "/"})
    public ResponseEntity addPoints(@RequestBody @Validated NewPoints newPoints) {
        PointsDto pointsDto = PointsDtoBuilder.buildFromEntity(pointsService.addNewPoints(newPoints));

        Map<String, Object> response = new HashMap<>();
        response.put("response", "Points have been awarded");
        response.put("points", pointsDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
