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
                    dir('C:/Users/91983/OneDrive/Desktop/Web Dev/AppliedDevopsProject') {
                        // Build Docker image using Dockerfile and current directory 
                        bat "docker build -t ${DOCKER_IMAGE_NAME} -f ${DOCKERFILE_PATH} ."
                    }
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        echo "Pushing Docker image: ${DOCKER_IMAGE_NAME}"
                        // Login to Docker Hub using credentials
                        bat "echo ${DOCKERHUB_PASSWORD} | docker login -u ${DOCKERHUB_USERNAME} --password-stdin"

                        // Push Docker image to Docker Hub
                        bat "docker push ${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }
    }
}
