package repository;
import java.util.HashMap;
import java.util.Map;
import entity.Cart;
import entity.User;
public class CartRepository {
    private Map<User, Cart> cartMap;

    public CartRepository() {
        this.cartMap = new HashMap<>();
    }

    // Create operation
    public void addCartRepository(User user, Cart cart) {
        cartMap.put(user, cart);
    }

    // Read operation
    public Cart getCartofParticularUser(User user) {
        return cartMap.get(user);
    }

    // Update operation
    public void updateCartofUser(User user, Cart updatedCart) {
        if (cartMap.containsKey(user)) {
            cartMap.put(user, updatedCart);
        }
    }

    // Delete operation
    public void removeCartofUser(User user) {
        cartMap.remove(user);
    }

    // Display all carts
    public void displayAllCarts() {
        cartMap.forEach((user, cart) -> 
            System.out.println(user.getUsername() + "'s cart: " + cart.getSelections()));
    }
}