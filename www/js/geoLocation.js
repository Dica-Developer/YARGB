var positionMarker;
var isTrackingActiv = true;

function showPosition(){
    setInterval(function() {
        getCurrentPosition()
    }, 3000);
}

function getCurrentPosition() {
    navigator.geolocation.getCurrentPosition(onSuccess, onError);
}

// onSuccess Geolocation
function onSuccess(position) {
    //var element = document.getElementById('geolocation');
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
        if(isTrackingActiv){
            map.jumpToWithZoom(lon,lat,12);
        }
        
    }
}

// onError Callback receives a PositionError object
function onError(error) {
    console.log('code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n');
}