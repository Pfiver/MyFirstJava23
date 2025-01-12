package net.patrickpfeifer.dynproxies;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Cache {

    public Collection<Map<String, String>> getBuildings() {
        return List.of(
                Map.of("id", "1", "name", "Building 1")
        );
    }

    public Collection<Map<String, String>> getFloors() {
        return List.of(
                Map.of("id", "1/1", "name", "Floor 1", "number", "1"),
                Map.of("id", "1/2", "name", "Floor 2", "number", "2"),
                Map.of("id", "2/1", "name", "Floor 1", "number", "1")
        );
    }
}
