package com.example.stockalarms.service;

import com.example.stockalarms.dto.CreateAlarmDto;
import com.example.stockalarms.model.Alarm;
import com.example.stockalarms.model.User;
import com.example.stockalarms.repository.AlarmRepository;
import com.example.stockalarms.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AlarmServiceImpl {

    private final AlarmRepository alarmRepository;
    private final StockService stockService;
    private final UserServiceImpl userService;
    private final AuthorizationServiceImpl authorizationService;

    @Transactional
    public UUID createAlarm(final CreateAlarmDto alarmDto) {
        final double currentStockPrice = stockService.getCurrentPriceForStock(alarmDto.getStockId());
        final User user = userService.getCurrentUser();
        final Alarm alarm = buildAlarm(alarmDto, currentStockPrice, user);
        final Alarm savedAlarm = alarmRepository.save(alarm);
        return savedAlarm.getId();
    }

    @Transactional
    public void deleteAlarm(final UUID id) {
        alarmRepository.findByIdAndUser_KeycloakId(id, authorizationService.getAuthenticatedUserKeycloakId())
                .ifPresent(alarmRepository::delete);
    }

    private Alarm buildAlarm(final CreateAlarmDto alarmDto, final double currentStockPrice, final User user) {
        return Alarm.builder()
                .currentPrice(currentStockPrice)
                .percentage(alarmDto.getPercentage())
                .user(user)
                .stockId(alarmDto.getStockId())
                .build();
    }

}
