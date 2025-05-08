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

    public void addCartRepository(User user, Cart cart) {
        cartMap.put(user, cart);
    }

    public Cart getCartofParticularUser(User user) {
        return cartMap.get(user);
    }

    public void updateCartofUser(User user, Cart updatedCart) {
        if (cartMap.containsKey(user)) {
            cartMap.put(user, updatedCart);
        }
    }

    public void removeCartofUser(User user) {
        cartMap.remove(user);
    }

    public void displayAllCarts() {
        cartMap.forEach((user, cart) -> 
            System.out.println(user.getUsername() + "'s cart: " + cart.getSelections()));
    }
}