FROM openjdk:11-jre-slim-buster
COPY target/telegram-bot-0.0.1-SNAPSHOT.jar /app.jar
COPY ./run.sh /
RUN chmod +x /run.sh
ENTRYPOINT ["sh", "/run.sh"]