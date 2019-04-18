package com.yarpg.dataprovider.jdbc;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.usecase.move.MapElementsProvider;

public class MapElementsProviderImpl implements MapElementsProvider {

    private JdbcTemplate _jdbcTemplate;

    public MapElementsProviderImpl(JdbcTemplate jdbcTemplate) {
        _jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MapElement> getMapChunk(GeoRef position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<MapElement> getMapChunks(List<GeoRef> position) {
        // TODO Auto-generated method stub
        return null;
    }

}
