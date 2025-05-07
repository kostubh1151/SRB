package entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<ResourceSelection> selections;

	public Cart() {
		this.selections = new ArrayList<>();
	}

	public void addSelection(ResourceSelection selection) {
		selections.add(selection);
	}

	public List<ResourceSelection> getSelections() {
		return selections;
	}

	public void clear() {
		selections.clear();
	}
}
//package entity;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class Cart {
//    private Map<String, ResourceSelection> selections;
//
//    public Cart() {
//        this.selections = new HashMap<>();
//    }
//
//    public void addSelection(ResourceSelection selection) {
//        selections.put(selection.getResource().getId(), selection);
//        System.out.println("Added: " + selection.getResource().getName());
//        viewCart();
//    }
//
//    public void removeSelection(String resourceId) {
//        if (selections.containsKey(resourceId)) {
//            selections.remove(resourceId);
//            System.out.println("Removed: " + resourceId);
//        } else {
//            System.out.println("Item not found.");
//        }
//        viewCart();
//    }
//
//    public void viewCart() {
//        if (selections.isEmpty()) {
//            System.out.println("Your cart is empty.");
//            return;
//        }
//        System.out.println("Current Cart:");
//        selections.values().forEach(selection ->
//            System.out.println("Resource: " + selection.getResource().getName() +
//                               ", Start Time: " + selection.getStartTime() +
//                               ", End Time: " + selection.getEndTime()));
//    }
//
//    public void clear() {
//        selections.clear();
//        System.out.println("Cart cleared.");
//    }
//}