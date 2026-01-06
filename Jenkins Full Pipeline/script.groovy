def buildApp() {
    echo "building the application..."
    sh "mvn package"
}

def buildImage() {
    echo "building the docker image..."
    withCredentials ([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')])
        sh 'docker build -t Rakhi2421/demo-app:jma-1.0 .'
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin  "
        sh 'docker push Rakhi2421/demo-app:jma-1.0 '
}

return this
