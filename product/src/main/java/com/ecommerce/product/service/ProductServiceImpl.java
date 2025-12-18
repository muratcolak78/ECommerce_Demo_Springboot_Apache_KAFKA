package com.ecommerce.product.service;

import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.dto.ProductBasisDto;
import com.ecommerce.product.model.dto.ProductDTO;
import com.ecommerce.product.repo.ProductRepository;
import com.ecommerce.product.service.imp.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProductDTO> getProductDTOS() {
        return repository.findAll()
                .stream()
                .map(product -> {
                    ProductDTO dto = new ProductDTO();
                    BeanUtils.copyProperties(product, dto);
                    return dto;
                })
                .toList();
    }
    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public BigDecimal getPriceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"))
                .getPrice();
    }

    @Override
    public String getNameById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"))
                .getName();
    }

    @Override
    public List<ProductBasisDto> getNamesByIds(List<Long> ids) {
        List<Product> productList=repository.findByIdIn(ids);
        return productList.stream().map(product -> {
            ProductBasisDto dto=new ProductBasisDto();
            dto.setProductId(product.getId());
            dto.setProductname(product.getName());
            return dto;
        }).toList();

    }
}
