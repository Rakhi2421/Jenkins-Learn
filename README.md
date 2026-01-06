# Jenkins-Learn
A beginner-friendly Jenkins learning repository that explains CI/CD and Jenkins concepts step by step. Includes practical examples to help freshers understand automation, pipelines, and real-world DevOps workflows easily.


# Build Automation & CI/CD with Jenkins
1. Introduction to Build Automation
2. Deploy jenkins on AWS Server
3. Install and use build tools in jenkins.
   - Maven, Gradle and npm
4. Create Freestyle Job
   - To conigure Credentials, Plugins and connect to git repository.
5. Integrate docker in Jenkins
   - Build Image and push to private repo
6. Build scripted pipeline Job.
7. Jenkins file in detail.
8. Build Multibranch pipeline
9. Configure automated versioning
10. Jenkins Shared Library.

---
# 🚀 Build Automation & CI/CD with Jenkins

This section covers **end-to-end CI/CD concepts using Jenkins**, starting from build automation fundamentals to advanced pipeline implementations.  
It is designed for **freshers, DevOps learners, and interview preparation**, with real-world use cases and best practices.

---

## 1️⃣ Introduction to Build Automation

**Build Automation** is the process of automatically compiling source code, running tests, packaging applications, and preparing artifacts for deployment.

### Why Build Automation?
- Eliminates manual errors
- Improves build consistency
- Enables faster releases
- Supports CI/CD pipelines

### Interview Key Point:
> Build automation ensures repeatable and reliable builds.

---
## What is Jenkins?
- It is one of the build automation tool.
- It is a software that you install on a dedicated software.
- It has UI for configuration
- install all the tools thay you need (Docker, gradle/maven/npm, etc.)
- Configure the tasks (run tests, build app, deployment, etc..)
- Configure the automatic trigger of the workflow.

## What you can do with Jenkins?
- Run Tests
- Build artifacts
- Publish artifacts
- Deploy artifacts
- Send Notifications

## Install Jenkins as a container.
```bash
docker run -p 8080:8080 -p 50000:50000 -d -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
## 8080 is jenkins port to connect via browser and 50000 is the port where jenkins master communicates with slave or worker nodes.

```
## Ways to login to Jenkins Container
```bash
# Login to container as a jenkins user
docker exec -it container_id bash
# Login to container as a root user use -u 0 in command
docker exec  -u 0 -it container_id bash
```

## Roles for Jenkins APP
1. Jenkins Administrator
   - administers and manages jenkins.
   - setup jenkins cluster
   - install plugins
   - backup jenkins data
2. Jenkins user
   - Creating the actual jobs to run workflows


## 2️⃣ Deploy Jenkins on AWS Server

Jenkins is deployed on an **AWS EC2 instance** to simulate a real production-like CI/CD setup.

### Key Steps:
- Launch EC2 instance
- Install Java (Jenkins dependency)
- Install Jenkins
- Access Jenkins via browser

### Interview Key Point:
> Jenkins requires Java and runs as a service on the server.

---

## 3️⃣ Install and Use Build Tools in Jenkins

There are 2 ways to install and configure build tools in jenkins.

1. Jenkins Plugins
   - Just install plugin (via UI) for your tool
2. Install directly on server, where jenkins is installed.
   - Enter into remote shell and install

Jenkins supports multiple build tools to handle different application types.

### 🔹 Maven
- Used for Java-based applications
- Manages dependencies and builds

### 🔹 Gradle
- Faster and flexible build automation tool
- Supports multi-project builds

### 🔹 npm
- Used for JavaScript and Node.js applications
- Manages frontend dependencies

### Interview Key Point:
> Jenkins integrates with multiple build tools to support different tech stacks.

---

## 4️⃣ Create Freestyle Job

A **Freestyle Job** is a basic Jenkins job used for simple automation tasks.

### Configurations:
- Connect Jenkins to Git repository
- Configure credentials
- Install required plugins
- Add build steps (Maven / Shell / npm)

## Process to create a freestyle project
- Click on new item- enter a job name + Select free style Project --> add build step -> fill details accordingly -> save and click on build job.
- To Configure git --> click on configure --> Source code management git --> fill the detailslike repo url + add credentials domain: global creds , Kind: Username with passwd (enter username & Password, then enter id as your wish (git-credentials)) then click on add --> then select the branch --> save and click on build job.
- 

### Interview Key Point:
> Freestyle jobs are easy to configure but limited compared to pipelines.

---

## 5️⃣ Integrate Docker in Jenkins

Docker integration enables Jenkins to build and package applications as containers.

### Capabilities:
- Build Docker images
- Tag images with versions
- Push images to private repositories

### Interview Key Point:
> Jenkins + Docker enables containerized CI/CD pipelines.

---
# Build a Docker image using Jenkins Free Style Job

## Deploy a Jenkins container by mounting docker socket and docker run time folder to it.
```bash
docker run -p 8080:8080 -p 50000:50000 \
-v Jenkins_home:/var/Jenkins_home   # this is a volume where our previous jenkins jobs plugins are configured in old jenkins container
-v /var/run/docker.sock:/var/run/docker.sock \
-v $(which docker):/usr/bin/docker jenkins/jenkins:lts
```
After executing above command when we run docker pull redis, we may get /var/lib/docker.sock: Permission denied.  
Then login to container using docker exec -u 0 conatiner_id bash and provide executable permission to that folder 
```bash
chmod 666 /var/run/docker.sock
```
Go to a job in that select a build step and select execute shell command
```bash
docker build -t java-maven-app:1.0 .
```

# Push to docker private repository using jenkins freestyle job
Things we are going to do:  
- create docker hub account
  - Create a repo as demo-app
- configure that credentials in jenkins
  - Manage Jenkins --> Security section (Manage Credentials) --> Stores scoped to jenkins(jenkins) --> Global credentials(Add Credentials) --> Select username with password --> Enter dockerhub username and password --> ID as dockerhub-cred(your wish name )

- Now go to Jenkins Job-> Configure Jenkins -> Add Build environment step (Use secret text or file) --> Bindings (Username and password) --> enter variable name as your wish for username(i used USERNAME) and password(i used PASSWORD) variable value to call it on entire job.  
  Now in build step executable shell command.
```bash
 docker build  -t Rakhi2421/demo-app:jma-1.0
 echo $PASSWORD | docker login -u USERNAME --password-stdin
 docker push   Rakhi2421/demo-app:jma-1.0
```

 # Push image to NEXUS private repository using jenkins freestyle job
 ```bash
vim /etc/docker/daemon.json
{
"insecure -registries":["ip:port(mentioned for repo in nexus)"]
}
systemctl restart docker
# Then start all the containers again
and change the permissions of a docker socket(inside jenkins container) mounted on jenkins container.
```
- configure Nexus credentials in jenkins
  - Manage Jenkins --> Security section (Manage Credentials) --> Stores scoped to jenkins(jenkins) --> Global credentials(Add Credentials) --> Select username with password --> Enter Nexus username and password --> ID as Nexus-cred(your wish name )
 - Now go to Jenkins Job-> Configure Jenkins -> Add Build environment step (Use secret text or file) --> Bindings (Username and password) --> enter variable name as your wish for username(i used USERNAME) and password(i used PASSWORD) variable value to call it on entire job.  
  Now in build step executable shell command.
```bash
 docker build  -t IP:port/demo-app:jma-1.0
 echo $PASSWORD | docker login -u USERNAME --password-stdin IP:port
 docker push   IP:port/demo-app:jma-1.0
```
 
## 6️⃣ Build Scripted Pipeline Job

A **Scripted Pipeline** uses Groovy-based scripting to define CI/CD logic.

### Characteristics:
- Written using Groovy(Groovy is a Programming Language Similar to Java)
- High flexibility
- Suitable for complex pipelines

### Interview Key Point:
> Scripted pipelines offer more control than freestyle jobs.

---

Create a new job with pipeline object --> Select Pipeline definition as pipeline script from SCM --> IN SCM Select Git --> Enter repo url and in Script path enter the Jenkins file name and configure git credentials --> and select the branch our code where it exists.  
Then click on build now.  




## 7️⃣ Jenkinsfile in Detail

A **Jenkinsfile** is a file that defines the pipeline as code.

### Benefits:
- Version controlled
- Reusable
- Consistent across environments

### Common Stages:
- Checkout
- Build
- Test
- Package
- Deploy

### Interview Key Point:
> Jenkinsfile enables Pipeline as Code.

## Jenkins File 
```bash
pipeline{                              # Pipeline - Must be on top level
    agent any                          # Agent any - Where to execute
    stages {                           # Stages - Where the work happens
        stage(build){                  # stages consists of stage and steps
            steps {                         
             }
          }
    }
}
```
## Environment Variables
What Variables are available in jenkins..!  
- IP:8080/env-vars.html (This help us to know the list of environment varibles we can use)

## Using credentials in Jenkins file
1) Define credentials in Jenkins GUI
2) "credentials("credentialID")" binds the credentials to your environment variables
3) For that you need "Credentials Binding" Plugin
 ```bash
 agent any
 environment{
        NEW_VERSION = '1.3.0'
        SERVER_CREDENTIALS = credentials('server-credentials') // Here server-credentials is a Credentials id while creating in jenkins i used.
        // It is referred in deploy step
    }
  #inside steps 
  withCredentials([
                    usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "some script ${USER} ${PWD}"
                }
```
## Specifying Tools - Access Build tools for your project
 Only 3 build tools available : gradle,maven and jdk!
```bash
 tools{
        maven 'Maven'
        gradle 'NAME'
        jdk 'NAME'
    }
```

## Parameters in Jenkins File
 # Parameterize your build
Types of your parameter:
- string (name,defaultValue,description)
- choice (name,choices,description)
- booleanParam (name, defaultValue, description)

```bash
agentany
 parameters{
        string (name: 'VERSION',defaultValue: '',description: 'version to deploy on prod')
        choice (name: 'VERSION',choices: ['1.1.0', '1.2.0', '1.3.0'],description: '')
        booleanParam (name: 'executeTests', defaultValue: true, description: '')
    }
 stage("deploy") {
            when {
                expression {
                    params.executeTests
               }
            }
            steps {
                echo 'deploying the application...'
                echo "deploying the version ${params.VERSION}"
                withCredentials([
                    usernamePassword(credentials: 'server-credentials', usernameVariable: USER, passwordVariable: PWD)
                ]) {
                    sh "some script ${USER} ${PWD}"
                }
            }
        }
```

## Input Parameter for user Input
- In some cases, we have to deploy on one environment among three and as a jenkins admin you have to configure one job but you must give option to user to select either dev,test or stage
```bash
stage("deploy") {
            input {
              message "Select environment to deploy to"
              ok "Done"
              parameters {
                  choice (name: 'ENV',choices: ['dev', 'staging', 'prod'],description: '')
              }
            }
            steps {
                script {
                    gv.deployApp ()
                    echo "deploying to ${ENV}"
                }

``` 
---

## 8️⃣ Build Multibranch Pipeline

A **Multibranch Pipeline** automatically creates pipelines for each branch in a repository.

### Benefits:
- Separate pipelines per branch
- Automatic detection of new branches
- Ideal for Git-based workflows

### Interview Key Point:
> Multibranch pipelines enable CI/CD for multiple branches.

---

## 9️⃣ Configure Automated Versioning

Automated versioning ensures each build produces a unique artifact version.

### Methods:
- Build numbers
- Git commit hash
- Semantic versioning

### Interview Key Point:
> Automated versioning helps in traceability and rollback.

---

## 🔟 Jenkins Shared Library

A **Jenkins Shared Library** allows reuse of common pipeline code across multiple projects.

### Benefits:
- Avoids code duplication
- Improves maintainability
- Standardizes pipelines

### Interview Key Point:
> Shared libraries promote DRY (Don’t Repeat Yourself) principle in Jenkins pipelines.

---

## 🎯 Key Takeaways

- Jenkins automates build and deployment
- Supports multiple build tools
- Enables Docker-based CI/CD
- Pipelines are version-controlled
- Shared libraries enhance reusability

---

## 📌 Conclusion

This Jenkins CI/CD setup demonstrates **real-world DevOps practices**, covering build automation, containerization, pipeline-as-code, and scalable Jenkins architectures—making it ideal for **hands-on learning and interview preparation**.

---

