package com.yarpg.core.usecase.move;

import java.util.List;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;

public interface MapElementsProvider {

    public abstract List<MapElement> getMapChunk(GeoRef position);

    public abstract List<MapElement> getMapChunks(List<GeoRef> position);
}
