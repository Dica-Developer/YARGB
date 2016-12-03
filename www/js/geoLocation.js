var positionMarker;
var isTrackingActive = false;
var tracker;
var isGpsActive = false;
var gpsIntervalTime = 3000;
var gpsErrorCount = 0;

function toggleGps(){
    if(isGpsActive){
        clearInterval(tracker);
        isGpsActive = false;
        window.plugins.toast.show('GPS deactivated', 'short', 'bottom');
        document.getElementById("gpsToggle").className = "inactive";
    }else{
        tracker = setInterval( getCurrentPosition, gpsIntervalTime);    
        isGpsActive = true;
        window.plugins.toast.show('GPS activated', 'short', 'bottom');
        document.getElementById("gpsToggle").className = "active";
    }
}

function toogleTrackPosition(){
    if(isTrackingActive){
        isTrackingActive = false;  
    }else{
        isTrackingActive = true;
        //TODO activate the following lines than Merc2Lat(lat) is acurate enought
        //var lonLat = getLonLatFromOpenLayerLonLat(positionMarker.lonlat);
        //map.jumpToWithZoom(lonLat.lon,lonLat.lat,18);
    }
}


function getCurrentPosition() {
    /* DEBUG Geolocation
    if (navigator.geolocation == false) {
        alert('Navigator-Location disabled');
    }
    if (app.geolocation == false) {
        alert('App-Geolocation disabled');
    }
    */
    navigator.geolocation.getCurrentPosition(onSuccess, onError, {maximumAge: 0, timeout: gpsIntervalTime, enableHighAccuracy: true});
}

// onSuccess Geolocation
function onSuccess(position) {
    resetErrorCount();
    var popuptext='<font color=\"black\">    Latitude: ' + position.coords.latitude              + '<br />' +
                        'Longitude: '          + position.coords.longitude             + '<br />' +
                        'Altitude: '           + position.coords.altitude              + '<br />' +
                        'Accuracy: '           + position.coords.accuracy              + '<br />' +
                        'Altitude Accuracy: '  + position.coords.altitudeAccuracy      + '<br />' +
                        'Heading: '            + position.coords.heading               + '<br />' +
                        'Speed: '              + position.coords.speed                 + '<br />' +
                        'Timestamp: '          + position.timestamp                    + '<br /></font>';

    var lon = position.coords.longitude;
    var lat = position.coords.latitude;

    if(undefined === positionMarker || null === positionMarker){
        positionMarker = layer_PositionMarker.addMarkerToLayerWithPopup(lon, lat , popuptext);
    }else {
        positionMarker.setPosition(lon,lat);
        if(isTrackingActive){
            map.jumpToWithZoom(lon,lat,18);
        }
        
    }
}

function resetErrorCount(){
    gpsErrorCount = 0;
}

// onError Callback receives a PositionError object
function onError(error) {
    console.log('code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n');
    gpsErrorCount++;
    if(gpsErrorCount >= 3){
        toggleGps();
        resetErrorCount();
        alert('No GPS-Signal, gps tracking is deactivated\n' + 
          'code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n');    
    }
    
}