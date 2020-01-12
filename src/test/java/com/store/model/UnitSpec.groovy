package com.store.model

import spock.lang.Specification

class UnitSpec extends Specification {

    def "should return product units"() {
        def units = []
        Unit.values().each {
            units << it.toString()
        }

        expect:
            units -  ["TIN", "BOTTLE", "SINGLE", "LOAF"] == []
    }
}
