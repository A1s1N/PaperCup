package org.service.controllers;

import org.service.models.Operation;
import org.service.models.Product;
import org.service.repo.OperationRepository;
import org.service.repo.ProductRepository;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.StreamSupport;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    private final OperationRepository operationRepository;


    @Autowired
    public ProductController(ProductRepository productRepository, OperationRepository operationRepository) {
        this.productRepository = productRepository;
        this.operationRepository = operationRepository;
    }

    @GetMapping("/products")
    public String products(Model model) {
        Iterable<Product> products = productRepository.findAll();
        if (StreamSupport.stream(products.spliterator(), false).findAny().isEmpty())
            model.addAttribute("message", "На данный момент изделия отсутствуют");
        else
            model.addAttribute("products", products);
        return "products/products";
    }

    @GetMapping("/products/add")
    public String productsAdd(Model model) {
        return "products/products-add";
    }


    @PostMapping("/products/add")
    public String productNewAdd(@RequestParam String name,
                                Model model) {
        Product product = new Product(name);
        productRepository.save(product);
        return "redirect:/products";
    }
    @GetMapping("/products/{id}")
    public String productDetails(@PathVariable(value = "id") Long id, Model model) {
        if(!productRepository.existsById(id))
            return "redirect:/products";

        Optional<Product> product = productRepository.findById(id);
        Iterable <Operation> operations = operationRepository.findByProductId(id);

        ArrayList<Product> productList = new ArrayList<>();
        //ArrayList<Operation> operationList = new ArrayList<>();
//        for (Operation op : operation) {
//            if (Objects.equals(op.getProductId(), id))
//                operationList.add(op);
//        }

        product.ifPresent(productList::add);
        //operation.ifPresent(operationList::add);
        model.addAttribute("product", productList);
        model.addAttribute("operations", operations);
        return "products/products-details";
    }

    @GetMapping("/products/{id}/edit")
    public String productEdit(@PathVariable(value = "id") long id, Model model) {
        if(!productRepository.existsById(id))
            return "redirect:/product";

        Optional<Product> product = productRepository.findById(id);
        ArrayList<Product> res = new ArrayList<>();
        product.ifPresent(res::add);
        model.addAttribute("product", res);
        return "products/products-edit";
    }

    @PostMapping("/products/{id}/edit")
    public String productUpdate(@PathVariable(value = "id") long id,
                               @RequestParam String name,
                               Model model) {

        Product product = productRepository.findById(id).orElseThrow();
        product.setName(name);
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{id}/remove")
    public String productDelete(@PathVariable(value = "id") long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow();
        Iterable <Operation> operation = operationRepository.findAll();

        for (Operation op : operation) {
            if (Objects.equals(op.getProductId(), id))
                operationRepository.delete(op);
        }
        productRepository.delete(product);
        return "redirect:/products";
    }


}
