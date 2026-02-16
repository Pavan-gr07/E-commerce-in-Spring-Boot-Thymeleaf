package com.example.ecommerce.service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    private final ProductService productService;
    private final HttpSession session;

    public CartServiceImpl(ProductService productService, HttpSession session) {
        this.productService = productService;
        this.session = session;
    }

    @Override
    public void addToCart(Long productId) {
        Map<Long, CartItem> cart = getCartItems();

        if (cart.containsKey(productId)) {
            cart.get(productId).setQuantity(cart.get(productId).getQuantity() + 1);
        } else {
            Product product = productService.getProductById(productId);
            cart.put(productId, new CartItem(product, 1));
        }
        session.setAttribute("cart", cart);
    }

    @Override
    public void removeFromCart(Long productId) {
        Map<Long, CartItem> cart = getCartItems();
        cart.remove(productId);
        session.setAttribute("cart", cart);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<Long, CartItem> getCartItems() {
        Map<Long, CartItem> cart = (Map<Long, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @Override
    public double getTotalAmount() {
        return getCartItems().values()
                .stream().mapToDouble(CartItem::getSubtotal).sum();
    }

}
