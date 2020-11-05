#!/bin/sh
mvn clean package && docker build --rm --file Dockerfile.arpa -t dr.ad.arpa.piemonte.it:5000/labins .
docker push dr.ad.arpa.piemonte.it:5000/labins
