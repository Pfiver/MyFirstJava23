package net.patrickpfeifer.dynproxies;

import net.patrickpfeifer.dynproxies.model.Building;
import net.patrickpfeifer.dynproxies.model.Floor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AnonImplReader {

    private final Cache cache = new Cache();

    public Collection<Building> getBuildings() {

        Collection<Map<String, String>> buildings = cache.getBuildings();
        Collection<Map<String, String>> floors = cache.getFloors();

        return map(buildings, floors);
    }

    private Collection<Building> map(Collection<Map<String, String>> buildings, Collection<Map<String, String>> floors) {

        return buildings.stream()
                .map(bm -> (Building) new Building() {

                    @Override
                    public String getName() {
                        return bm.get("name");
                    }

                    @Override
                    public Collection<Floor> getFloors() {
                        return floors.stream()
                                .filter(fm -> fm.get("id").split("/")[0].equals(bm.get("id")))
                                .map(fm -> (Floor) new Floor() {
                                    @Override
                                    public String getName() {
                                        return fm.get("name");
                                    }

                                    @Override
                                    public int getNumber() {
                                        return Integer.parseInt(fm.get("number"));
                                    }

                                    @Override
                                    public String toString() {
                                        return fm.toString();
                                    }
                                })
                                .toList();
                    }

                    @Override
                    public String toString() {
                        Map<String, String> stringMap = new HashMap<>(bm);
                        stringMap.put("floors", getFloors().toString());
                        return stringMap.toString();
                    }
                })
                .toList();
    }
}
