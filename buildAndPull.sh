#!/bin/sh
mvn clean package && docker build --rm -t dr.ad.arpa.piemonte.it:5000/labins .
docker push dr.ad.arpa.piemonte.it:5000/labins
