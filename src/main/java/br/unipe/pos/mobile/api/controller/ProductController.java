package br.unipe.pos.mobile.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unipe.pos.mobile.api.exception.ProductNotFoundException;
import br.unipe.pos.mobile.api.model.Product;
import br.unipe.pos.mobile.api.repository.ProductRepository;

/**
 */
@RestController
@RequestMapping("/api/v1.0")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public List<Product> getAllPersons() {
		return productRepository.findAll();
	}

	@GetMapping("/products/{id}")
	public Product getProductById(@PathVariable(value = "id") Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product", "id", id));
	}

	@PostMapping("/products")
	public Product createProduct(@Valid @RequestBody Product product) {
		return productRepository.save(product);
	}

	@PutMapping("/products/{id}")
	public Product updatePerson(@PathVariable(value = "id") Long id, @Valid @RequestBody Product productDetails) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product", "id", id));

		product.setProductName(productDetails.getProductName());
		product.setProductDetails(productDetails.getProductDetails());

		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") Long id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product", "id", id));

		productRepository.delete(product);
		return ResponseEntity.ok().build();
	}

}
