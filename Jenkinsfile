pipeline {
    agent any
    tools {
        maven 'Maven'
        jdk 'jdk'
    }
    triggers {
        pollSCM('H * * * *')
    }
    stages {
        stage('SCM') {
            steps {
                git branch: 'main', url: 'https://github.com/vincitegx/DiscoverMeApp.git'
            }
        }
        stage('Build') {
            steps {
                dir('backend') {
                  sh '''
                    env | grep -e PATH -e JAVA_HOME
                    which java
                    java -version
                    unset JAVA_HOME && mvn clean install
                  '''
                }
                
            }
            // post {
            //     always{
            //         cleanWs()
            //     }
            // }
        }
        stage('SonarQube Analysis') {
            steps {
                dir('backend'){
                    withSonarQubeEnv('SonarQube') {
                        sh 'mvn clean package sonar:sonar'
                    }    
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("your-docker-image-name:latest")
                }
            }
        }

        stage('Push to DockerHub') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                        dockerImage.push()
                    }
                }
            }
        }

        stage('Deploy to AWS') {
            steps {
                // Add your AWS deployment steps here
            }
        }
        stage('Email Notification') {
            steps {
                emailext subject: 'Pipeline Completed Successfully',
                    body: 'The Jenkins pipeline has completed successfully.',
                    to: 'davidogbodu3056@gmail.com'
            }
        }
    }
    post {
        failure {
            emailext subject: 'Pipeline Failed',
                body: 'The Jenkins pipeline has failed. Please check the build logs for more details.',
                to: 'davidogbodu3056@gmail.com'
        }
    }
}