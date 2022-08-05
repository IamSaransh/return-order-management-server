#Nevicate to  the current directory
echo "navigating to your directory"
cd "${BASH_SOURCE%/*}" || exit
cd ..
export SONAR_TOKEN=88f8071678c87f1ceb6553bd014e6728b5c82d5e
echo "sonar token is : $SONAR_TOKEN"

echo 'Running jacoco:prepare-agent...'
mvn clean jacoco:prepare-agent install
echo 'Running jacoco:report...'
mvn jacoco:report
echo 'Running sonar:sonar...'
mvn sonar:sonar \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.organization=iamsaransh \
  -Dsonar.projectKey=return-order-api-gateway

