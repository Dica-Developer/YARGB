function cbTrackingChanged(cb) {
	if (cb.checked) {
		if(!isTrackingActive){
			toogleTrackPosition();
		}		
    }else { 
    	if(isTrackingActive){
			toogleTrackPosition();
		}
    }
}