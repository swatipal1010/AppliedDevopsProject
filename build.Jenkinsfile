pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'your-dockerhub-username/calculator-app'
        DOCKERFILE_PATH = 'Dockerfile'
        CALCULATOR_DIR = 'Calculator'
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image using Dockerfile
                    bat "docker build -t ${DOCKER_IMAGE_NAME} -f ${DOCKERFILE_PATH} ${CALCULATOR_DIR}"
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        // Login to Docker Hub using credentials stored in the jenkins server
                        bat "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"

                        // Pushing Docker image to Docker Hub
                        bat "docker push ${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }
    }
}
