pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'swati1010/calculator-app'
        DOCKERFILE_PATH = 'Dockerfile'
        CALCULATOR_DIR = 'Calculator'
    }

    stages {

        stage('Build Docker Image using Docker') {
            steps {
               
                        // Build Docker image using Dockerfile and current directory 
                        sh "docker build -t ${DOCKER_IMAGE_NAME} -f ${DOCKERFILE_PATH} ."
                    }
                }
        

        stage('Push Docker Image to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                    script {
                        echo "Pushing Docker image: ${DOCKER_IMAGE_NAME}"
                        // Login to Docker Hub using credentials
                        sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"

                        // Push Docker image to DockerHub using Jenkins Pipeline
                        sh "docker push ${DOCKER_IMAGE_NAME}"
                    }
                }
            }
        }
    }
}

