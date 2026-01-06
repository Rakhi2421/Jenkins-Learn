#!/usr/bin/env grrovy
def call() {
    echo "building the application..."
    sh "mvn package"
}