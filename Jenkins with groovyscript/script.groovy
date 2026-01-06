def buildApp() {
    echo 'build the application...'
    echo "build the application with ${NEW_VERSION}"
}

def testApp() {
    echo 'Testing the application...'
}

def deployApp() {
    echo 'deploying the application...'
    echo "deploying the version ${params.VERSION}"
}

return this
