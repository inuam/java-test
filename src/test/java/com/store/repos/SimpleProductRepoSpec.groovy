package com.store.repos

import com.store.model.Product
import spock.lang.Specification

import static com.store.model.Unit.TIN
import static java.math.BigDecimal.valueOf
import static java.util.Arrays.asList

class SimpleProductRepoSpec extends Specification {

    def expectedSoup = new Product(1l, "SOUP", TIN, valueOf(0.65))
    def productRepository = new SimpleProductRepo(asList(expectedSoup))

    def "should find product by name"() {
        when:
        def optionalProduct = productRepository.getProductByName("SOUP")

        then:
        optionalProduct.get() == expectedSoup
    }

    def "should find product by id"() {
        when:
        def optionalProduct = productRepository.getProduct(1L)

        then:
        optionalProduct.get() == expectedSoup
    }

    def "should return all products from repo"() {
        when:
        def products = productRepository.findAll()

        then:
        products == [expectedSoup]
    }

    void givenRepoWithProduct(Product product) {
        productRepository = new SimpleProductRepo(asList(product))
    }
}
