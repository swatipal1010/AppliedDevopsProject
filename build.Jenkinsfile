pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'swati1010/calculator-app'
        DOCKERFILE_PATH = 'Dockerfile'
        DOCKERHUB_CREDENTIALS_ID = 'dockerhub-credentials'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image using Dockerfile in the current directory
                    bat "docker build -t ${DOCKER_IMAGE_NAME} -f ${DOCKERFILE_PATH} ."
                }
            }
        }

        stage('Run Snyk Security Test') {
            steps {
                script {
                    // Run Snyk security test on the Docker image
                    def snykCommand = "snyk test --docker ${DOCKER_IMAGE_NAME}"
                    def snykOutput = bat(returnStdout: true, script: snykCommand).trim()

                    // Print Snyk output to console
                    echo "Snyk Security Test Output:"
                    echo "${snykOutput}"
                    
                    // Check Snyk output for vulnerabilities
                    if (snykOutput.contains('found 0 issues')) {
                        echo "No vulnerabilities found in the Docker image."
                    } else {
                        error "Vulnerabilities found in the Docker image. Aborting further steps."
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: DOCKERHUB_CREDENTIALS_ID, usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        // Login to Docker Hub using credentials
                        bat "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"

                        // Push Docker image to DockerHub
                        bat "docker push ${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }
    }

    post {
        always {
            // Cleanup: logout from Docker Hub
            script {
                bat 'docker logout'
            }
        }
    }
}
