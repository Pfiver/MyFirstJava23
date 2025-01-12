package net.patrickpfeifer.dynproxies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReaderTest {

    @Test
    void testAnonImplReader() {

        var buildings = new AnonImplReader().getBuildings();
        System.out.println(buildings);

        assertTrue(true);
    }

    @Test
    void testProxyReader() {

        var buildings = new ProxyReader().getBuildings();
        System.out.println(buildings);

        assertTrue(true);
    }
}
