FROM openjdk:11
#Change timezone
#RUN apk add --no-cache tzdata
ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV CI_BUILD_URL=$CI_BUILD_URL
EXPOSE 80
COPY target/*.jar /tmp/
RUN mkdir -p /config
COPY start.sh /tmp/
RUN chmod +x /tmp/start.sh
CMD /tmp/start.sh ${CI_BUILD_NAME}
