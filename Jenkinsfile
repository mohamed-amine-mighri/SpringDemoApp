pipeline {
    agent any // Add this line to specify the agent

    tools {
        maven 'Maven'
    }
    environment {
        KUBECONFIG = credentials('finleKuberConfig')
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
                        sh 'docker build -t aminemighri/demo-java-ops:2.0 .'
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                     echo 'deploying docker image...'
                     withCredentials([usernamePassword(credentialsId: 'dokerhub-credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                         sh "echo $PASS | docker login -u $USER --password-stdin"
                         sh 'docker push aminemighri/demo-java-ops:2.0'
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
                    withKubeConfig([credentialsId: 'finleKuberConfig']) {
                        // Deploy the Java Spring app
                        sh """
                            minikube kubectl apply -f deployment.yaml -n $NAMESPACE
                            minikube kubectl apply -f service.yaml -n $NAMESPACE
                        """
                    }
                }
            }
    
        }
    }
}
