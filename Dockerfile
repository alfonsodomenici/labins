FROM dr.ad.arpa.piemonte.it:5000/wildfly-labins
COPY ./target/labins.war ${DEPLOYMENT_DIR}
COPY ./target/labins-fr.war ${DEPLOYMENT_DIR}
