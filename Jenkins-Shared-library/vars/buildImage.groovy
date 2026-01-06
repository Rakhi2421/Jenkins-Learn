#!/usr/bin/env groovy

def call() {
    echo "building the docker image..."
    withCredentials ([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')])
        sh 'docker build -t Rakhi2421/demo-app:jma-1.0 .'
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin  "
        sh 'docker push Rakhi2421/demo-app:jma-1.0 '
}

-----

//Extract Logic Into Groovy classes - above data is extracted to  src/com/example/docker.groovy and input variable value from this file


#!/usr/bin/env groovy

import com.example.Docker

def call(String imageName) {
    return new Docker(this).buildDockerImage(imageName)
}