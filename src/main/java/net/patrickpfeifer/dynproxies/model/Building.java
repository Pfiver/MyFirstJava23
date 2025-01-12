package net.patrickpfeifer.dynproxies.model;

import java.util.Collection;

public interface Building {

    String getName();

    Collection<Floor> getFloors();
}
