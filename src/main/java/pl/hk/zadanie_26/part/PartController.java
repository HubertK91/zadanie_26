package pl.hk.zadanie_26.part;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.hk.zadanie_26.Category;
import pl.hk.zadanie_26.order.Order;

import java.util.List;

@Controller
public class PartController {
    private final PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @GetMapping("/partsCatalog")
    public String productCatalog(Model model) {
        List<Part> parts = partService.productCatalog();
        model.addAttribute("parts", parts);
        return "part/catalog";
    }

    @GetMapping("/addPart")
    public String addPart(Model model) {
        model.addAttribute("partToAdd", new Part());
        model.addAttribute("categories", Category.values());
        return "part/addPart";
    }

    @PostMapping("/addPart")
    public String addPart(Part part) {
        partService.addPart(part);
        return "redirect:/partsCatalog";
    }

    @GetMapping("/editPart")
    public String editPart(@RequestParam Long id, Model model) {
        Part part = partService.findByIdPart(id);
        model.addAttribute("part", part);
        model.addAttribute("categories", Category.values());
        return "part/editPart";
    }

    @PostMapping("/editPart")
    public String editPart(Part part) {
        partService.editPart(part);
        return "redirect:/partsCatalog";
    }

    @GetMapping("/cart")
    public String printListOfProductsInCart(Model model, @ModelAttribute(name = "createOrder") Order order) {
        List<Part> parts = partService.printListOfProductsInCart();
        model.addAttribute("createOrder", order);
        model.addAttribute("parts", parts);
        return "cart";
    }

    @PostMapping("/cart")
    public String addProductToCart(@RequestParam Long id) {
        partService.addProductToCart(id);
        return "redirect:/partsCatalog";
    }

    @PostMapping("/partsCatalog")
    public String deleteProductFromCart(@RequestParam Long id) {
        partService.deleteProductFromCart(id);
        return "redirect:/cart";
    }
}
