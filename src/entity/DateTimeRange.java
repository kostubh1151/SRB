package entity;
import java.time.LocalDateTime;
public class DateTimeRange {
private LocalDateTime startTime;
private LocalDateTime endTime;

public DateTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
if (startTime.isAfter(endTime)) {
throw new IllegalArgumentException("Start time must be before end time");
}
this.startTime = startTime;
this.endTime = endTime;
}

public boolean overlaps(DateTimeRange other) {
return startTime.isBefore(other.endTime) && endTime.isAfter(other.startTime);
}

public LocalDateTime getStartTime() {
return startTime;
}

public LocalDateTime getEndTime() {
return endTime;
}
}