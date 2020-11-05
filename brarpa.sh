#!/bin/sh
mvn  clean package && docker build --rm --file Dockerfile.arpa  -t dr.ad.arpa.piemonte.it:5000/labins .
docker rm -f labins || true && docker run -d -p 8080:8080 -p 4848:4848 --name labins dr.ad.arpa.piemonte.it:5000/labins  && docker logs -f labins
