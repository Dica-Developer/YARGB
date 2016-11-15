function bGetPositionClick() {
	showPosition();		
}

function bTrackPositionClick() {
	trackPosition();
}

function bTrackPositionStopClick() {
	trackPositionStop();
}

function cbTrackingChanged(cb) {
	if (cb.checked) {
        bTrackPositionClick();
    }
    else { 
        bTrackPositionStopClick(); 
    }
}