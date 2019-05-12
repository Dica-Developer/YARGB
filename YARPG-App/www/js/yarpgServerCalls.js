var yarpgServer = {

	movePlayer: function(sucess, error, lat, long){
		var georef = JSON.stringify({"latitude":lat,"longitude":long});
		this.load(sucess,error,"http://10.0.2.2:8080/update/player/position",georef,"POST", "application/json;charset=UTF-8");
	},
	load: function(sucess, error, theURL, data, httpType, mimeType) {
        console.log("loadURL " + theURL);
        var xmlhttp;
        if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari, SeaMonkey
            xmlhttp = new XMLHttpRequest();
        } else { // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onload = function () {
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                sucess(JSON.parse(xmlhttp.responseText));
            }else{
            	error(xmlhttp);
            }
        };
        xmlhttp.open(httpType, theURL, true);
        xmlhttp.setRequestHeader("Content-Type", mimeType);
        xmlhttp.send(data);
    }
};