pipeline {
    agent any
    environment {
        DOCKER_USER = 'raulalanis'
        DOCKER_PASS = credentials('docker-hub-pwd')
    }
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
                    // sh 'docker build -t raulalanis/spring-boot-mongo .'
                    def app = docker.build("raulalanis/spring-boot-mongo")
                }
            }
        }
        stage('Push image to Hub'){
            steps{
                script{
//                    withCredentials([string(credentialsId: 'docker-hub-pwd', variable: 'dockerhubpwd')]) {
//                        sh 'docker login -u raulalanis -p ${dockerhubpwd}'
//                    }
//                    sh 'docker push raulalanis/spring-boot-mongo'

                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-pwd') {
                        def app = docker.image('raulalanis/spring-boot-mongo')
                        app.push()
                    }
                }
            }
        }
    }
}