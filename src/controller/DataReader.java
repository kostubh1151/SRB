package controller;

import entity.Resource;
import service.ResourceService;

public class DataReader {
private ResourceService resourceService;

public DataReader(ResourceService resourceService) {
this.resourceService = resourceService;
}

public void populateDummyData() {
resourceService.addResource(new Resource("R1", "Meeting Room A", "Room", 50.0));
resourceService.addResource(new Resource("R2", "Projector", "Equipment", 20.0));
resourceService.addResource(new Resource("R3", "Java Book", "Book", 10.0));
}
}