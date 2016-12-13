var prefs;
var buttonPressed = false;
//var debugSwitch = false;
var trackingOn = false;

function storeOk (value) {
	if (value != null)
		alert(value + ' wurde den Settings hinzugefügt.');
}

function showOk() {

}

function fail (error) {
	alert(error);
}

function settingsAddOption(key, value) { 

	// store key => value pair
    prefs.store (ok, fail, key, value);

}

function settingsShow() {
    
    // show application preferences
    prefs.show (showOk, fail);
}

function settingsPressButton() {
	prefs = plugins.appPreferences;
	document.getElementById("settings").className = "active";
	
}

function settingsReleaseButton() {
	document.getElementById("settings").className = "inactive";
	settingsShow();	
}

/* function getDebugSwitch() {
	prefs.fetch(getDebugSwitch2, fail, 'debug');
}

function getDebugSwitch2(value) {
	debugSwitch = value;
	alert(debugSwitch);
} */

function getTrackingSwitch(){
	prefs.fetch(getTrackingStatus, fail, 'track');
}

function getTrackingStatus(trackingOn){
	if(trackingOn == true){
		isTrackingActive = true;

	}else {
		isTrackingActive = false;
		 
	}
}