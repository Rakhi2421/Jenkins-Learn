# What is Jenkins Shared Library? Why we need it?

# Create the Shared Library
- Create Repository
- Write the Groovy code
- Make the shared library available globally or for the project
- Use the shared library in jenkinsfile to extend the pipeline

# Structure of Shared Library
- **vars folder**
 - functions that we call from Jenkinsfile
 - Each function/execution step is its own Groovyfile. Functions like build jar file, build docker image, push docker image
- **src folder**
 - helper code
- **resource folder**
 - Use external Libraries
 - non groovy files 

---
 #### Make Shared library globally available

 Manage Jenkins --> configure system --> Global pipeline libraries --> Name:Jenkins-shared-Library --> default version - master --> select the box (Allow default version to be overridden & Include @Library changes in recent job changes) --> Retrival method-modern scm --> SCM(git)--> enter jenkins shared library repo url & enter credentials --> save & Apply.

 ### Use shared Library in jenkins file

 Import library in jenkins file
```bash
 @Library('jenkins-shared-library')
 def gv

pipeline {
    agentany
    tools {
        maven 'MAVEN'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy" 
                }
            }
        }
        stage("build jar") {
            steps {
                script {
                    buildJar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    buildImage()
                }
            }
        }
    }

}
```
## Using Parameters in shared Library

```bash
#!/usr/bin/env groovy

def call(String imageName) {
    echo "building the docker image..."
    withCredentials ([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')])
        sh "docker build -t ${imageName} ."
        sh "echo ${PASS} | docker login -u ${USER} --password-stdin  "
        sh "docker push ${imageName} "
}

In Jenkins file

stage("build image") {
            steps {
                script {
                    buildImage 'Rakhi2421/demo-app:jma-1.0'
                }
            }
        }

```

## Extract Logic Into Groovy classes
In buildImage.groovy

```bash

#!/usr/bin/env groovy

import com.example.Docker

def call(String imageName) {
    return new Docker(this).buildDockerImage(imageName)
}

```

## After Split Build docker Image into Several Steps

In jenkins file you need to update the below.

```bash
stage("build image") {
            steps {
                script {
                    buildImage 'Rakhi2421/demo-app:jma-1.0'
                    dockerLogin()
                    dockerPush 'Rakhi2421/demo-app:jma-1.0'
                }
            }
        }
```

## Project Scoped Shared Library

Jenkins file

```bash
#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'repo-url',
     credentialsId: 'git-credentials'
    ]
)

def gv

pipeline{
agentany
}
```
