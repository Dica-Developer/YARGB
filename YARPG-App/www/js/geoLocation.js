var positionMarker;
var isTrackingActive = false;
var tracker;
var isGpsActive = false;
var gpsIntervalTime = 10000;
var gpsErrorCount = 0;

function toggleGps(){
    if(isGpsActive){
        //TODO set button to green
        clearInterval(tracker);
        isGpsActive = false;
        window.plugins.toast.show('GPS deactivated', 'short', 'bottom');
        document.getElementById("gpsToggle").className = "inactive";
    }else{
        //TODO set button to blank
        tracker = setInterval( getCurrentPosition, gpsIntervalTime);    
        isGpsActive = true;
        window.plugins.toast.show('GPS activated', 'short', 'bottom');
        document.getElementById("gpsToggle").className = "active";
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

    prefs.fetch(function(gpsTimeOut) {
        navigator.geolocation.getCurrentPosition(onSuccess, onError, {maximumAge: 3000, timeout: gpsTimeOut, enableHighAccuracy: true});
    }, fail, 'gpsTimeOut');
    
}



// onSuccess Geolocation
function onSuccess(position) {
    //todo set button to green
    resetErrorCount();
    var lon = round(position.coords.longitude,6);
    var lat = round(position.coords.latitude,6);

    var popuptext='<font color=\"black\">    Latitude: ' + lat             + '<br />' +
                        'Longitude: '          + lon            + '<br />' +
                        'Altitude: '           + position.coords.altitude              + '<br />' +
                        'Accuracy: '           + position.coords.accuracy              + '<br />' +
                        'Altitude Accuracy: '  + position.coords.altitudeAccuracy      + '<br />' +
                        'Heading: '            + position.coords.heading               + '<br />' +
                        'Speed: '              + position.coords.speed                 + '<br />' +
                        'Timestamp: '          + position.timestamp                    + '<br /></font>';


    console.log('Latitude: ' + lat + 'Longitude: ' + lon );
    yarpgServer.movePlayer(onMovePlayerSuccess,
        function(xmlhttp){
            console.log("error "+ xmlhttp.statusText);
        },
        lat,lon);

    if(undefined === positionMarker || null === positionMarker){
        positionMarker = layer_PositionMarker.addMarkerToLayerWithPopup(lon, lat , popuptext);
    }else {
        positionMarker.setPosition(lon,lat);
        prefs.fetch(function(trackingEnabled) {
            if (trackingEnabled) {
                map.jumpToWithZoom(lon,lat,17);
            }
        }, fail, 'track');
    }
}

function createPointLonLat(lon,lat){
    return new OpenLayers.Geometry.Point( lon, lat ).transform(new OpenLayers.Projection("EPSG:4326"), map.getProjectionObject())
}

function onMovePlayerSuccess(elements){            
    if (mapElements) {
        console.log("remove old elements from layer");
        mapElements.destroyFeatures();
    }

    var iconFeature;
    var iconPath;
    console.log("adding " +elements.entity.length+" elements");
    for (var i = elements.entity.length - 1; i >= 0; i--) {
        var element = elements.entity[i];                
        switch(element.elementType) {
            case "com.yarpg.core.entity.MonsterEncounter":
                var feature = new OpenLayers.Feature.Vector(createPointLonLat(element.position.longitude, element.position.latitude),
                    {
                        description:'Name of the Encounter ' + element.monsterName,
                        name: element.monsterName
                    },
                    {
                        externalGraphic: "img/wildlife.png", 
                        graphicHeight: 25, 
                        graphicWidth: 25, 
                        graphicXOffset:-13, 
                        graphicYOffset:-25
                    }
                );
    
                mapElements.addFeatures(feature);
                //mapElements.refresh();
            break;
            
            default:
                console.log("type not found "+ element.elementType);
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
        resetErrorCount();
        //todo set gps button to yellow
        alert('No GPS-Signal, gps tracking is deactivated\n' + 
          'code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n');    
    }    
}