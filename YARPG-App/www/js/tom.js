getOpenLayersLonLat = function (lon, lat){
  return new OpenLayers.LonLat(Lon2Merc(lon), Lat2Merc(lat));
}

getLonLatFromOpenLayerLonLat = function (lonlat){
  return {lon:Merc2Lon(lonlat.lon),lat:Merc2Lat(lonlat.lat)};
}

OpenLayers.Map.prototype.jumpToWithZoom = function (lon, lat, zoom) {
  this.setCenter(getOpenLayersLonLat(lon,lat), zoom);
}

OpenLayers.Map.prototype.jumpTo = function (lon, lat) {
  this.setCenter(getOpenLayersLonLat(lon,lat), map.zoom);
}

Lon2Merc = function (lon) {
  return 20037508.34 * lon / 180; 
}

Lat2Merc = function (lat) {
  var PI = 3.14159265358979323846;
  lat = Math.log(Math.tan( (90 + lat) * PI / 360)) / (PI / 180);
  return 20037508.34 * lat / 180;
}

Merc2Lon = function (mercLon){
  return lon * 180 / 20037508.34;
}

Merc2Lat = function (mercLat){
  var PI = 3.14159265358979323846;
  var RAD2DEG = 180 / PI;
  var PI_4 = PI / 4;
  lat = lat * 180 / 20037508.34;
  lat = (Math.atan(Math.exp(lat / RAD2DEG)) / PI_4 - 1) * 90;
  return round(lat,11);
}

function round(float, precision){
  var roundingHelper = Math.pow(10,precision);
  return Math.round(float*roundingHelper)/roundingHelper;
}

OpenLayers.Layer.prototype.addMarkerToLayer = function (lon, lat){
  var ll = getOpenLayersLonLat(lon,lat);
  var marker = new OpenLayers.Marker(ll)
  this.addMarker(marker);
  return marker;
}

OpenLayers.Marker.prototype.setPosition = function(lon, lat){
  var newLonLat = getOpenLayersLonLat(lon,lat);
  var newPx = map.getLayerPxFromLonLat(newLonLat);
  this.moveTo(newPx);
};

OpenLayers.Layer.prototype.addMarkerToLayerWithPopup = function ( lon, lat, popupContentHTML) {
  var ll = getOpenLayersLonLat(lon,lat);
  var feature = new OpenLayers.Feature(this, ll); 
  feature.closeBox = true;
  feature.popupClass = OpenLayers.Class(OpenLayers.Popup.FramedCloud, {minSize: new OpenLayers.Size(300, 180) } );
  feature.data.popupContentHTML = popupContentHTML;
  feature.data.overflow = "hidden";
          
  var marker = new OpenLayers.Marker(ll);
  marker.feature = feature;

  var markerClick = function(evt) {
      if (this.popup == null) {
          this.popup = this.createPopup(this.closeBox);
          map.addPopup(this.popup);
          this.popup.show();
      } else {
          this.popup.toggle();
      }
      OpenLayers.Event.stop(evt);
  };
  marker.events.register("mousedown", feature, markerClick);

  this.addMarker(marker);
  map.addPopup(feature.createPopup(feature.closeBox));
  return marker;
}

OpenLayers.Marker.prototype.removeMarker = function (marker){
  marker.clear();
}

function getCycleTileURL(bounds) {
  var res = this.map.getResolution();
  var x = Math.round((bounds.left - this.maxExtent.left) / (res * this.tileSize.w));
  var y = Math.round((this.maxExtent.top - bounds.top) / (res * this.tileSize.h));
  var z = this.map.getZoom();
  var limit = Math.pow(2, z);

  if (y < 0 || y >= limit)
  {
   return null;
  }
  else
  {
   x = ((x % limit) + limit) % limit;

   return this.url + z + "/" + x + "/" + y + "." + this.type;
  }
}