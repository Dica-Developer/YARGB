# YARPG

## Getting Started

Download and install the latest version of phonegap. Download the SDKs you will work with, e.g. android sdk manager.

## Usage

### First use
You have to add the Platforms you want to build. Therefor you will need the SDKs or dependencies on your machine.

	> phonegap platform add <platforms>

To this date it is only tested with 'android'.

#### Create Android apk

    > phonegap build <platforms>
    
You will find the file in '<Projectpath>/platforms/<platforms>/build/outputs/apk'
