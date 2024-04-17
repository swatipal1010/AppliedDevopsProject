pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'swati1010/calculator-app'
        DOCKERFILE_PATH = 'Dockerfile'
    }

    stages {
        stage('Build and Push Docker Image') {
            steps {
                script {
                    // Build Docker image
                    sh "docker build -t ${DOCKER_IMAGE_NAME} -f ${DOCKERFILE_PATH} ."

                    // Push Docker image to Docker Hub
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKERHUB_USERNAME', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
                        sh "docker login -u ${DOCKERHUB_USERNAME} -p ${DOCKERHUB_PASSWORD}"
                        sh "docker push ${DOCKER_IMAGE_NAME}"
                        sh "docker logout"
                    }
                }
            }
        }

        stage('Run Snyk Security Test') {
            steps {
                script {
                    // Install Snyk CLI (if not already installed)
                    //sh 'npm install -g snyk'

                    // Authenticate with Snyk (if needed)
                    // sh 'snyk auth <your-snyk-api-token>'

                    // Run Snyk security test on the pushed Docker image
                    sh "snyk test --docker ${DOCKER_IMAGE_NAME}"
                }
            }
        }
    }

    post {
        always {
            // Cleanup: Logout from Docker Hub
            sh 'docker logout'
        }
    }
}
