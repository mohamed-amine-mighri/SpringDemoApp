pipeline {
    agent any // Add this line to specify the agent

    tools {
        maven 'Maven'
    }
    environment {
        KUBECONFIG = credentials('KuberConf')
        APP_NAME = 'springapp'
        IMAGE_NAME = 'aminemighri/demo-java-ops:2.0'
        NAMESPACE = 'deploymentservice.yml'
    }

    stages {
        stage("build jar") {
            steps {
                script {
                    echo 'building the app...'
                    sh 'mvn package'
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo 'building docker image...'
                        sh 'sudo docker build -t aminemighri/demo-java-ops:2.2 .'
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                     echo 'deploying docker image...'
                     withCredentials([usernamePassword(credentialsId: 'dockerHubCred', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                         sh 'docker build -t aminemighri/demo-java-ops:2.2 .'
                         sh 'docker push aminemighri/demo-java-ops:2.2'
                  }
                }
            }
        }

        stage('SCM') {
            steps {
                checkout scm
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def mvn = tool 'Maven';
                    withSonarQubeEnv() {
                        sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=amine-app-scan -Dsonar.projectName='amine-app-scan'"
                    }
                }
            }
        }


        stage('Deploy to Kubernetes') {
            steps {
                script {
                    // Configure kubectl with the provided kubeconfig
                    withKubeConfig([credentialsId: 'KuberConf']) {
                        // Deploy the Java Spring app
                        sh """
                            minikube kubectl -- apply -f deploymentservice.yml
                        """
                    }
                }
            }
    
        }
    }
}
