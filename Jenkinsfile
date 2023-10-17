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
        stage('Build Docker Image') {
            steps {
                dir('backend'){
                    script {
                        withDockerRegistry(credentialsId: 'dockercredentials') {
                            sh 'docker build -t davidtega/discoverme:v1.1'
                            // docker.build("davidtega/discoverme:v1.1")
                        }
                    }
                }
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