CODE_CHANGES = getGitChanges()
pipeline {
    agentany
    environment{
        NEW_VERSION = '1.3.0'
        SERVER_CREDENTIALS = credentials('server-credentials') // Here server-credentials is a Credentials id while creating in jenkins i used.
        // It is referred in deploy step
    }
    tools{
        maven 'Maven'
        gradle
        jdk
    }
    parameters{
        string (name: 'VERSION',defaultValue: '',description: 'version to deploy on prod')
        choice (name: 'VERSION',choices: ['1.1.0', '1.2.0', '1.3.0'],description: '')
        booleanParam (name: 'executeTests', defaultValue: true, description: '')
    }

    stages {
        stage("build") {
            when {
                expression {
                    BRANCH_NAME == 'dev' && CODE_CHANGES == true  
                     // this means this test executes when branch is dev &  changes done in dev.
                }
            }
            steps {
                echo 'build the application...'
                echo "build the application with ${NEW_VERSION}"
            }
        }
        stage("test") {
            //we can define conditionals for each stage
            when {
                expression {
                    BRANCH_NAME == 'dev' || BRANCH_NAME == 'test'  
                     // this means this test executes when branch is either test or dev, unless it will skipped.
                }
            }
            steps {
                echo 'Testing the application...'
            }
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
    }
    // Post is used to Executes some logic after all stages executed.
    // In Post we have to use some conditions like always, success, failure,....
    // It is mostly used to notify us when a build is succeeded or failed
    post {                    
        always {
            //
        }
        success {

        }
    }
}