# Stock alarms project

#### Build project: 
**./gradlew clean build**

#### Start local environmet:
**docker-compose up -d**
<br> Starts datatabase on port 3307
<br> Starts keycloak on port 8081 with default realm
<br> Interface is at: http://localhost:8081/auth
<br> Credentials: admin/admin

#### Get access token call:

curl --location --request POST 'http://localhost:8081/auth/realms/stock-alarms/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--header 'Cookie: JSESSIONID=9CE70CACC5425BB54A2A921D6D33ED55.ad2ec84df8c7; JSESSIONID=9CE70CACC5425BB54A2A921D6D33ED55' \
--data-urlencode 'client_id=stock-alarms-be' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'scope=openid' \
--data-urlencode 'username=username' \
--data-urlencode 'password=password'

#### Run application:
**./gradlew clean bootRun**
