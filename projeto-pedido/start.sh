#/bin/sh
DOMAIN=$(echo "$CI_BUILD_URL" | awk -F/ '{print $3}')
export SERVICE_HOSTNAME=${DOMAIN}

echo $SERVICE_HOSTNAME > /tmp/start.log
echo $1 > /tmp/teste.log

#JVM_OPTS="-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 "
JVM_OPTS=" -Xmx200M "

if [ "$1" = "Deploy:production" ]; then
    echo "perfil=producao" > /config/perfil.properties
#    SPRING_PROFILES="prod"
    SPRING_PROFILES="producao"
elif [ "$1" = "Deploy:staging" ]; then
    echo "perfil=homologacao" > /config/perfil.properties
    SPRING_PROFILES="homologacao"
#    echo $SPRING_PROFILES > /tmp/springprofile.log
elif [ "$1" = "Deploy:review-staging" ]; then
    echo "perfil=homologacao" > /config/perfil.properties
#    SPRING_PROFILES="hom"
    SPRING_PROFILES="homologacao"
elif [ "$1" = "Deploy:preprod" ]; then
    echo "perfil=preproducao" > /config/perfil.properties
#    SPRING_PROFILES="prod"
    SPRING_PROFILES="producao"
else
    echo "perfil=desenvolvimento" > /config/perfil.properties
#    SPRING_PROFILES="dev"
    SPRING_PROFILES="homologacao"
fi

export CI_ENVIRONMENT=$1
#export SPRING_CLOUD_CONFIG_LABEL=gitlab

java $JVM_OPTS -Dserver.port=80 -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8 -Dspring.profiles.active=$SPRING_PROFILES -Deureka.instance.hostname=${DOMAIN} -jar /tmp/*.jar

