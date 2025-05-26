pipeline {
    agent any
    stages {
        stage('Clone Repo') {
            steps {
                checkout scmGit(branches: [[name: '*/develop']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/RaulAlanisP/demoJavaSpringMongo']])
            }
        }
        stage('Build Project') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}