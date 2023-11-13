pipeline {
    agent any // Add this line to specify the agent

    tools {
        maven 'Maven'
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
                kubernetesDeploy {
                    // Provide the Kubernetes service account credentials
                    serviceAccountCredentialsId 'finleKuberConfig	'

                    // Specify the Kubernetes pod template
                    podTemplate {
                        apiVersion 'v1'
                        kind 'Pod'
                        metadata {
                            name 'my-app'
                            labels {
                                app 'my-app'
                            }
                        }
                        spec {
                            containers {
                                container {
                                    name 'my-app-container'
                                    image 'aminemighri/demo-java-ops:2.0'
                                    ports {
                                        containerPort 8080
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
