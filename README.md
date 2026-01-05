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

## 6️⃣ Build Scripted Pipeline Job

A **Scripted Pipeline** uses Groovy-based scripting to define CI/CD logic.

### Characteristics:
- Written using Groovy
- High flexibility
- Suitable for complex pipelines

### Interview Key Point:
> Scripted pipelines offer more control than freestyle jobs.

---

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

