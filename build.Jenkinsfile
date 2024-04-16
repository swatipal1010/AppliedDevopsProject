pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'swati1010/calculator-app'
        DOCKERFILE_PATH = 'Dockerfile'
        CALCULATOR_DIR = 'Calculator'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Navigate to the project root directory where Dockerfile is located
                    dir("C:\Users\91983\OneDrive\Desktop\Web Dev\AppliedDevopsProject") {
                        // Build Docker image using Dockerfile and current directory as build context
                        bat "docker build -t swati1010/calculator-app -f Dockerfile ."
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        // Login to Docker Hub using credentials
                        bat "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"

                        // Push Docker image to Docker Hub
                        bat "docker push ${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }
    }
}
