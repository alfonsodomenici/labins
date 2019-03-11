#!/bin/sh
mvn clean package && docker build --rm -t dr.ad.arpa.piemonte.it:5000/labins .
#docker rm -f labins || true && docker run -d --network arpa -p 8080:8080 -p 4848:4848 --name labins dr.ad.arpa.piemonte.it:5000/labins  