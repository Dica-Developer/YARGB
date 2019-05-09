package com.yarpg.core.usecase.storyteller;

import java.util.List;

import org.assertj.core.util.Lists;
import org.assertj.core.util.VisibleForTesting;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.entity.MonsterEncounter;
import com.yarpg.core.usecase.MapElementsProvider;
import com.yarpg.core.util.Maths;

public class StorytellerUseCase {
    private MapElementsProvider _mapElementsProvider;

    public StorytellerUseCase(MapElementsProvider mapElements) {
        _mapElementsProvider = mapElements;
    }

    public void randomizeEcounterBetween(GeoRef pStart, GeoRef pEnd) {
        List<MapElement> newElements = createEncounterForArea(pStart, pEnd);

        _mapElementsProvider.removeMapElementsBetween(pStart, pEnd);
        _mapElementsProvider.addMapElements(newElements);
    }

    @VisibleForTesting
    List<MapElement> createEncounterForArea(GeoRef pStart, GeoRef pEnd) {
        List<MapElement> newElements = Lists.newArrayList();
        double squareSizeInMeter = 100;
        double distanceInLat = GeoRef.distanceInLat(squareSizeInMeter);
        GeoRef rowCornerPoint = pStart;

        // Row
        while (rowCornerPoint.getLatitude() > pEnd.getLatitude()) {
            GeoRef columnCornerPoint = rowCornerPoint;
            double distanceInLon = GeoRef.distanceInLon(columnCornerPoint, squareSizeInMeter);

            // Column
            while (columnCornerPoint.getLongitude() < pEnd.getLongitude()) {

                double latitude = getRandomLatitudeInDistanceToCornerPoint(columnCornerPoint, -distanceInLat);
                double longitude = getRandomLongitudeInDistanceToCornerPoint(columnCornerPoint, distanceInLon);

                GeoRef randomPosition = new GeoRef(latitude, longitude);
                MonsterEncounter encounter = new MonsterEncounter(getRandomMonsterName(), randomPosition);
                newElements.add(encounter);

                columnCornerPoint = createNewCornerPointInSameRow(columnCornerPoint, distanceInLon);
            }
            rowCornerPoint = createNewCornerPointInSameColumn(rowCornerPoint, -distanceInLat);
        }
        return newElements;
    }

    private GeoRef createNewCornerPointInSameColumn(GeoRef cornerPoint, double distanceInLat) {
        return new GeoRef(cornerPoint.getLatitude() + distanceInLat, cornerPoint.getLongitude());
    }

    private GeoRef createNewCornerPointInSameRow(GeoRef cornerPoint, double distanceInLon) {
        return new GeoRef(cornerPoint.getLatitude(), cornerPoint.getLongitude() + distanceInLon);
    }

    private double getRandomLongitudeInDistanceToCornerPoint(GeoRef cornerPoint, double distanceInLon) {
        // TODO > 360 oder 180?? entsprechend umrechnen
        return Maths.randomFloat(cornerPoint.getLongitude(), cornerPoint.getLongitude() + distanceInLon);
    }

    private double getRandomLatitudeInDistanceToCornerPoint(GeoRef cornerPoint, double distanceInLat) {
        // TODO <0 entsprechend umrechnen
        if (distanceInLat > 0) {
            return Maths.randomFloat(cornerPoint.getLatitude(), cornerPoint.getLatitude() + distanceInLat);
        } else {
            return Maths.randomFloat(cornerPoint.getLatitude() + distanceInLat, cornerPoint.getLatitude());
        }
    }

    private String getRandomMonsterName() {
        List<String> monsterName = Lists.newArrayList("Drache", "Goblin", "Fee", "Ork", "Spinne", "Untote", "Räuber");
        return monsterName.get((int) Maths.randomFloat(0, monsterName.size() - 1));
    }
}
