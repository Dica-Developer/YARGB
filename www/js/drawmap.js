var map;
var layer_mapnik;
var layer_PositionMarker;

function drawmap() {
    OpenLayers.Lang.setCode('de');
    
    // Position und Zoomstufe der Karte
    //TODO save latest position?
    var lon = 6.641389;
    var lat = 49.756667;
    var zoom = 7;

    map = new OpenLayers.Map('map', {
        projection: new OpenLayers.Projection("EPSG:900913"),
        displayProjection: new OpenLayers.Projection("EPSG:4326"),
        controls: [
            new OpenLayers.Control.Navigation(),
            new OpenLayers.Control.LayerSwitcher(),
            new OpenLayers.Control.PanZoomBar()],
        maxExtent:
            new OpenLayers.Bounds(-20037508.34,-20037508.34,
                                    20037508.34, 20037508.34),
        numZoomLevels: 18,
        maxResolution: 156543,
        units: 'meters'
    });

    layer_mapnik = new OpenLayers.Layer.OSM.Mapnik("Mapnik");
    layer_PositionMarker = new OpenLayers.Layer.Markers("Position", { projection: new OpenLayers.Projection("EPSG:4326"), 
    	                                          visibility: true, displayInLayerSwitcher: false });
    //TODO save map possible? dose it give any improvement?
    map.addLayers([layer_mapnik, layer_PositionMarker]);

    map.jumpToWithZoom(lon, lat, zoom);
}