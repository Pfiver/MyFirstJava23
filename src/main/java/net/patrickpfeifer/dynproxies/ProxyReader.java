package net.patrickpfeifer.dynproxies;

import net.patrickpfeifer.dynproxies.model.Building;

import java.util.Collection;
import java.util.Map;

public class ProxyReader {

    private final Cache cache = new Cache();

    public Collection<Building> getBuildings() {

        Collection<Map<String, String>> buildings = cache.getBuildings();
        Collection<Map<String, String>> floors = cache.getFloors();

        return map(buildings, floors);
    }

    private Collection<Building> map(Collection<Map<String, String>> buildings, Collection<Map<String, String>> floors) {

        return buildings.stream()
                .map(bm -> {

                    var bmFloors = floors.stream()
                            .filter(fm -> fm.get("id").split("/")[0].equals(bm.get("id")))
                            .toList();

                    return ProxyObject.create(Building.class, bm, Map.of("floors", bmFloors));

                })
                .toList();
    }
}
