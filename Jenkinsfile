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
        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t raulalanis/demoSpringBootMongoDB'
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'docker-hub-pwd', variable: 'docker-hub-pwd')]) {
                       sh 'docker login -u raulalanis -p ${docker-hub-pwd}'
                   }
                   sh 'docker push raulalanis/demoSpringBootMongoDB'
                }
            }
        }
    }
}