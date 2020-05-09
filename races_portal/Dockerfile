FROM docker.io/anapsix/alpine-java

ADD target/Races-Portal-1.0.0.jar app.jar
COPY launcher.sh /launcher.sh

RUN sh -c 'touch app.jar' && \
        touch ./launcher.sh && \
        chmod +x ./launcher.sh && \
        mkdir -p /logs && \
        mkdir -p /cfg && \
        mkdir -p /history && \
        addgroup -S -g 1000 races && \
        adduser -S -u 1000 races -G races

VOLUME /logs
VOLUME /history

RUN chown -R races /history && chown -R races /logs

# Create the Races user

USER races

EXPOSE 8080

ENV SERVICES="localhost:8081" \
        UPLOAD_PATH="/history" \
        PORT=8080 \
        ROUTELOG="/logs/" \
        LOGS_APP_LEVEL="INFO" \
        LOGS_DB_LEVEL="INFO" \
        LOGS_SERVER_LEVEL="INFO"

ENTRYPOINT ["ash","launcher.sh"]
