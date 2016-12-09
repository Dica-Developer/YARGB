var prefs;
var buttonPressed = false;

function ok (value) {
	alert('Etwas wurde erfolgreich hinzugefügt.');
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
    prefs.show (ok, fail);
}

function settingsPressButton() {
	prefs = plugins.appPreferences;
	document.getElementById("settingsButton").className = "active";
	
}

function settingsReleaseButton() {
	document.getElementById("settingsButton").className = "inactive";
	settingsAddOption('Wert', 'Key');
	settingsShow();
	
}