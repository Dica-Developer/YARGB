var prefs;
var buttonPressed = false;

function storeOk (value) {
	if (value != null)
		alert(value + ' wurde in den Settings gespeichert.');
}

function showOk() {

}

function fail (error) {
	console.log('code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n');
    alert('Error while trying to get \'track\'-settingsvalue.\n' + 
          'code: '    + error.code    + '\n' +
          'message: ' + error.message + '\n'); 
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

