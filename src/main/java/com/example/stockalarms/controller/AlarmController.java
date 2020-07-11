package com.example.stockalarms.controller;

import com.example.stockalarms.dto.CreateAlarmDto;
import com.example.stockalarms.service.AlarmServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/alarms")
public class AlarmController {

    private final AlarmServiceImpl alarmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID createAlarm(@RequestBody @NotNull @Valid final CreateAlarmDto alarmDto) {
        return alarmService.createAlarm(alarmDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createAlarm(@PathVariable final UUID id) {
        alarmService.deleteAlarm(id);
    }

}
