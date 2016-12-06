var prefs = plugins.appPreferences;

function ok (value) {
	alert('Etwas wurde erfolgreich hinzugefügt.');
}
function fail (error) {
	alert(error);
}

settingsAddOption = function(key, value) { 

	// store key => value pair
    prefs.store (ok, fail, key, value);

}

settingsShow = function() {
    
    // show application preferences
    prefs.show (ok, fail);
}