package service;

import java.util.Map;

import entity.Resource;
import repository.ResourceRepository;

public class ResourceService {
	private ResourceRepository resourceRepository;
	private InputValidator validator;

	public ResourceService(ResourceRepository resourceRepository, InputValidator validator) {
		this.resourceRepository = resourceRepository;
		this.validator = validator;
	}

	public void addResource(Resource resource) {
		if (!validator.validateResource(resource)) {
			throw new IllegalArgumentException("Invalid resource data");
		}
		resourceRepository.save(resource);
	}

	public Resource getResource(String id) {
		return resourceRepository.findById(id);
	}

	public Map<String, Resource> getAllResources() {
		return resourceRepository.findAll();
	}

	public void updateResource(String id, String name, String type, double costPerHour) {
		Resource resource = resourceRepository.findById(id);
		if (resource == null) {
			throw new IllegalArgumentException("Resource not found");
		}
		resource.setName(name);
		resource.setType(type);
		resource.setCostPerHour(costPerHour);
		if (!validator.validateResource(resource)) {
			throw new IllegalArgumentException("Invalid resource data");
		}
		resourceRepository.save(resource);
	}

	public void deleteResource(String id) {
		if (resourceRepository.findById(id) == null) {
			throw new IllegalArgumentException("Resource not found");
		}
		resourceRepository.delete(id);
	}
}