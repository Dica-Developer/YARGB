package com.yarpg.core.usecase;

import java.util.List;
import java.util.Map;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;

public interface MapElementsProvider {

    public abstract void addMapElements(List<MapElement> newElements);

    public abstract List<MapElement> getMapBetween(GeoRef pStart, GeoRef pEnd);

    public abstract List<MapElement> getMapChunk(GeoRef position);

    public abstract Map<Integer, List<MapElement>> getMapChunks();

    public abstract Map<Integer, List<MapElement>> getMapChunks(List<GeoRef> position);

    public abstract void moveMapElement(Map<MapElement, GeoRef> moveTo);

    public abstract void removeMapElement(List<MapElement> toRemove);

    public abstract void removeMapElementsBetween(GeoRef pStart, GeoRef pEnd);
}
