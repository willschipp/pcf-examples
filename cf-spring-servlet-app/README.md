To use the example;

mvn clean package
cf login -a [your api endpoint]
cf push [your app name] -p [location of the jar] -n [endpoint name] -m 512M --no-start
cf create-service [database] [plan] [name for the database]
cf bind-service [your app name] [name of the database]
cf start [your app name]
