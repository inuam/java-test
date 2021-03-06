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

     def "should return empty optional when product not found"() {
         when:
         def optionalProduct = productRepository.getProductByName("UNKNOWN")

         then:
         optionalProduct == Optional.empty()
     }

    void givenRepoWithProduct(Product product) {
        productRepository = new SimpleProductRepo(asList(product))
    }
}
