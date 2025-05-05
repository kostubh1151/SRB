package controller;

import entity.Resource;
import service.ResourceService;
import service.UserService;

public class DataReader {
private ResourceService resourceService;
private UserService userService;

public DataReader(ResourceService resourceService, UserService userService) {
this.resourceService = resourceService;
this.userService = userService;
}

public void populateDummyData() {
resourceService.addResource(new Resource("R1", "Meeting Room A", "Room", 50.0));
resourceService.addResource(new Resource("R2", "Projector", "Equipment", 20.0));
resourceService.addResource(new Resource("R3", "Java Book", "Book", 10.0));

userService.registerUser("12", "user", "user123", "regular_user");
userService.registerUser("9", "admin", "admin123", "admin");
userService.registerUser("5", "resource", "resource", "resource_manager");

}
}