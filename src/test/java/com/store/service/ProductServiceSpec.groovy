package com.store.service

import com.store.model.Product
import com.store.model.Unit
import com.store.repos.ProductRepository
import spock.lang.Specification

class ProductServiceSpec extends Specification {

    ProductRepository productRepo = Mock()
    ProductService productService = new ProductService(productRepo)

    def "should find product by name"() {
        String apple = "APPLE"
        def appleProduct = new Product(1L, apple, Unit.SINGLE, BigDecimal.valueOf(0.1))

        given:
        productRepo.getProductByName(apple) >> Optional.of(appleProduct)

        when:
        productService.getProductByName(apple)

        then:
        productRepo.getProductByName(apple) == Optional.of(appleProduct)
    }


}
