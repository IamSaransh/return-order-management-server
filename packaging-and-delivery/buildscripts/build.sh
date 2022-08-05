#Nevicate to  the current directory
echo "navigating to your directory"
cd "${BASH_SOURCE%/*}" || exit
cd ..
export SONAR_TOKEN=72be5950a78aac5e6964fcd5b2383c9db87bf426
echo "sonar token is : $SONAR_TOKEN"

echo 'Running jacoco:prepare-agent...'
mvn clean jacoco:prepare-agent install
echo 'Running jacoco:report...'
mvn jacoco:report
echo 'Running sonar:sonar...'
mvn sonar:sonar \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.organization=iamsaransh \
  -Dsonar.projectKey=packaging-and-return-order-delivery