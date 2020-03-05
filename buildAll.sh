#!/bin/sh

#genero war del server
mvn clean package

#muovo in webapp
cd ./../build-client/src/main/webapp

echo -----------ripulisco $(pwd)---------------
rm -R *

echo ----------copio la cartella WEB/INF-------
cp -R ./../../../WEB-INF/ .

echo ----------copio le cartelle src/ e node_modules/ dal client
cp -R ./../../../../client/src/* .
cp -R ./../../../../client/node_modules ./

cd ./../../../
echo -----------torno in $(pwd)---------------

#genero war del client
mvn clean package
cp ./target/labins-fr.war ./../server/target/

#muovo nel server
cd ./../server/
echo -----------torno in $(pwd)---------------

docker build --rm -t dr.ad.arpa.piemonte.it:5000/labins .
#docker rm -f labins || true && docker run -d --network arpa -p 8080:8080 -p 4848:4848 --name labins dr.ad.arpa.piemonte.it:5000/labins  
