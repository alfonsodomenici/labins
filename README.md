# Build
mvn clean package && docker build -t it.arpa.piemonte/tqtara .

# RUN

docker rm -f tqtara || true && docker run -d -p 8080:8080 -p 4848:4848 --name tqtara it.arpa.piemonte/tqtara 