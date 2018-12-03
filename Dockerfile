FROM airhacks/glassfish
COPY ./target/tqtara.war ${DEPLOYMENT_DIR}
