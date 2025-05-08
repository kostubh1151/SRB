package entity;

import java.time.LocalDateTime;

public class ResourceSelection {
	private Resource resource;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	public ResourceSelection(Resource resource, LocalDateTime startTime, LocalDateTime endTime) {
		this.resource = resource;
		this.startTime = startTime;
		this.endTime = endTime;
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
}
