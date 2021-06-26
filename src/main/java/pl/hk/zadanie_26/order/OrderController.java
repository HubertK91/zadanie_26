package pl.hk.zadanie_26.order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.hk.zadanie_26.client.Client;
import pl.hk.zadanie_26.part.Part;
import pl.hk.zadanie_26.part.PartService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class OrderController {
    private final OrderService orderService;
    private final PartService partService;

    public OrderController(OrderService orderService, PartService partService) {
        this.orderService = orderService;
        this.partService = partService;
    }

    @GetMapping("/ordersCatalog")
    public String ordersCatalog(Model model) {
        List<Order> orders = orderService.ordersCatalog();
        model.addAttribute("orders", orders);
        return "order/order";
    }

    @PostMapping(value = "/addOrder")
    public String addOrder(final RedirectAttributes redirectAttributes) {
        Order order = new Order();
        List<Part> parts = partService.printListOfProductsInCart();
        order.setParts(parts);
        try {
            Client currentUser = orderService.findCurrentUser();
            order.setClient(currentUser);
            partService.deleteProductsFromCart(parts);
            orderService.addOrder(order);
            redirectAttributes.addFlashAttribute("createOrder", order);
            return "redirect:/cart";
        } catch (NoSuchElementException e) {
            return "redirect:/login";
        }
    }

    @PostMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/ordersCatalog";
    }
}
