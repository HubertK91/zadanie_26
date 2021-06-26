package pl.hk.zadanie_26.order;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.hk.zadanie_26.client.Client;
import pl.hk.zadanie_26.client.ClientRepository;
import pl.hk.zadanie_26.part.Part;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public OrderService(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    public List<Order> ordersCatalog() {
        return orderRepository.findAll();
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteOrder(Long id) {
        Order order = findOrderById(id);
        List<Part> parts = order.getParts();
        parts.clear();
        orderRepository.delete(order);
    }

    public Client findCurrentUser() throws NoSuchElementException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return clientRepository.findByUsername(username).orElseThrow();
    }
}
