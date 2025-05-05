package entity;
import java.time.LocalDateTime;
public class Booking {
private String id;
private User user;
private Resource resource;
private LocalDateTime startTime;
private LocalDateTime endTime;
private double cost;

public Booking(String id, User user, Resource resource, LocalDateTime startTime, LocalDateTime endTime, double cost) {
this.id = id;
this.user = user;
this.resource = resource;
this.startTime = startTime;
this.endTime = endTime;
this.cost = cost;
}

public String getId() {
return id;
}

public User getUser() {
return user;
}

public Resource getResource() {
return resource;
}

public LocalDateTime getStartTime() {
return startTime;
}

public LocalDateTime getEndTime() {
return endTime;
}

public double getCost() {
return cost;
}
}
