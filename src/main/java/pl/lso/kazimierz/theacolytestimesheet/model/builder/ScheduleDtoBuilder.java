package pl.lso.kazimierz.theacolytestimesheet.model.builder;

import pl.lso.kazimierz.theacolytestimesheet.model.dto.ScheduleDto;
import pl.lso.kazimierz.theacolytestimesheet.model.entity.Schedule;

public class ScheduleDtoBuilder {

    private Long id;
    private Long userId;
    private Long eventId;

    public ScheduleDtoBuilder() {
    }

    public static ScheduleDtoBuilder getInstance() {
        return new ScheduleDtoBuilder();
    }

    public ScheduleDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    public ScheduleDtoBuilder withUserId(Long userId) {
        this.userId = userId;
        return this;
    }
    public ScheduleDtoBuilder withEventId(Long eventId) {
        this.eventId = eventId;
        return this;
    }

    public static ScheduleDto buildFromEntity(Schedule schedule) {
        return ScheduleDtoBuilder.getInstance()
                .withId(schedule.getId())
                .withUserId(schedule.getUserId())
                .withEventId(schedule.getEventId())
                .build();
    }

    public ScheduleDto build() {
        ScheduleDto scheduleDto = new ScheduleDto(
                this.id,
                this.userId,
                this.eventId);
        return scheduleDto;
    }
}
