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
resourceService.addResource(new Resource("R1", "Stage Equipments", "Electronics", 70.0));
resourceService.addResource(new Resource("R2", "Projector", "Equipment", 20.0));
resourceService.addResource(new Resource("R3", "Java Book", "Book", 10.0));
resourceService.addResource(new Resource("R4", "SpringBoot", "Book", 30.0));
resourceService.addResource(new Resource("R5", "HD Conference Microphone", "Microphone", 330.0));
resourceService.addResource(new Resource("R6", "Interactive Whiteboard", "Board", 310.0));
resourceService.addResource(new Resource("R7", "Python Mastery Book", "Book", 80.0));

userService.registerUser("12", "user", "user123", "regular_user");
userService.registerUser("11", "Tinu", "Tinu123", "regular_user");
userService.registerUser("19", "Kostubh", "Kostu123", "regular_user");
userService.registerUser("9", "admin", "admin123", "admin");
userService.registerUser("5", "resource", "resource123", "resource_manager");

}
}