FROM it.alfonsodomenici/wildfly-labins:19
COPY ./target/labins.war ${DEPLOYMENT_DIR}
#COPY ./target/labins-fr.war ${DEPLOYMENT_DIR}
