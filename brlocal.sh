#!/bin/sh
mvn clean package && docker build --rm --file Dockerfile.local -t it.alfonsodomenici/labins .
docker rm -f labins || true && docker run -d -p 8080:8080 -p 4848:4848 --name labins it.alfonsodomenici/labins  && docker logs -f labins
