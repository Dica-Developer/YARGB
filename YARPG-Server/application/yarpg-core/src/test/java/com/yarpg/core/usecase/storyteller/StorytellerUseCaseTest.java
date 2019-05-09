package com.yarpg.core.usecase.storyteller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.yarpg.core.entity.GeoRef;
import com.yarpg.core.entity.MapElement;
import com.yarpg.core.usecase.MapElementsProvider;

public class StorytellerUseCaseTest {
    private MapElementsProvider _mapElementsProvider = mock(MapElementsProvider.class);

    private StorytellerUseCase _storytellerUseCase = new StorytellerUseCase(_mapElementsProvider);

    @Test
    public void createEncounterForAreaTest() {
        GeoRef pStart = new GeoRef(51.52, 11.8);
        GeoRef pEnd = new GeoRef(51.3, 12.1);
        List<MapElement> createEncounterForArea = _storytellerUseCase.createEncounterForArea(pStart, pEnd);

        GeoRef lowerRightCorner = GeoRef.createLowerRightCorner(pStart, 500);
        List<MapElement> area = createEncounterForArea.stream()
                .filter(ele -> lowerRightCorner.getLatitude() <= ele.getPosition()
                        .getLatitude())
                .filter(ele -> lowerRightCorner.getLongitude() >= ele.getPosition()
                        .getLongitude())
                .collect(Collectors.toList());

        area.forEach(System.out::println);
        System.out.println(area.size());
    }

    @Test
    public void createEncounterForAreaTest_oneEncounter() {
        // setup
        GeoRef pStart = new GeoRef(50, 11);
        GeoRef pEnd = GeoRef.createLowerRightCorner(pStart, 100);

        // test a 1 by 1 area, should include just 1
        List<MapElement> createEncounterForAreaWithOne = _storytellerUseCase.createEncounterForArea(pStart, pEnd);

        // verify
        assertThat(createEncounterForAreaWithOne.size()).isEqualTo(1);

        // test a 5 by 5 area, should include 5x5+(5-1) encounter
        // +(5-1) because in the calculation right know every row below the first will fit at least on area more
        pEnd = GeoRef.createLowerRightCorner(pStart, 500);
        List<MapElement> createEncounterForAreaWith5By5 = _storytellerUseCase.createEncounterForArea(pStart, pEnd);

        // verify
        assertThat(createEncounterForAreaWith5By5.size()).isEqualTo(29);
    }
}
