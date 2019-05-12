var map;
var layer_mapnik;
var layer_PositionMarker;
var mapElements;

function drawmap() {
    OpenLayers.Lang.setCode('de');
    
    // Position und Zoomstufe der Karte
    //TODO save latest position?
    var lon = 6.641389;
    var lat = 49.756667;
    var zoom = 7;

    layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
    layer_PositionMarker = new OpenLayers.Layer.Markers("Position", { projection: new OpenLayers.Projection("EPSG:4326"), 
                                                  visibility: true, displayInLayerSwitcher: false });
    mapElements = new OpenLayers.Layer.Vector("MapElements", {visibility: true, displayInLayerSwitcher: true });
    var select = new OpenLayers.Control.SelectFeature(mapElements, { onSelect: createPopup, onUnselect: destroyPopup });

    map = new OpenLayers.Map('map', {
        projection: new OpenLayers.Projection("EPSG:900913"),
        displayProjection: new OpenLayers.Projection("EPSG:4326"),
        controls: [
                    new OpenLayers.Control.Navigation(),
                    new OpenLayers.Control.LayerSwitcher({'ascending':false}),
                    select
                ],
        layers:[layer_mapnik, layer_PositionMarker, mapElements],
        maxExtent:
            new OpenLayers.Bounds(-20037508.34,-20037508.34,
                                    20037508.34, 20037508.34),
        numZoomLevels: 18,
        maxResolution: 156543,
        units: 'meters'
    });
    select.activate();
    

    //TODO save map possible? dose it give any improvement?
    //map.addControl(new OpenLayers.Control.Navigation());
    //map.addControl(new OpenLayers.Control.LayerSwitcher({'ascending':false}));
    //map.addControl(new OpenLayers.Control.SelectFeature(mapElements, { onSelect: createPopup, onUnselect: destroyPopup });    
    //map.addLayers([layer_mapnik, layer_PositionMarker, mapElements]);
    map.jumpToWithZoom(lon, lat, zoom);
}

function createPopup(feature) {
  console.log("createPopup");
  feature.popup = new OpenLayers.Popup.FramedCloud("pop",
      feature.geometry.getBounds().getCenterLonLat(),
      null,
      '<div class="markerContent">'+feature.attributes.description+'</div>',
      null,
      true,
      function() { controls['selector'].unselectAll(); }
  );
  //feature.popup.closeOnMove = true;
  map.addPopup(feature.popup);
}

function destroyPopup(feature) {
  feature.popup.destroy();
  feature.popup = null;
}