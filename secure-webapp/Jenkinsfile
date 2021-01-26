pipeline {

    environment {
        registry = "dermacon/jenkins-test"
        registryCredentials = 'dermacon'
        DB_USER = 'user'
        DB_PASSWORD = 'password'
    }
    

    agent any

    tools {
        maven 'Maven 5.4.0-42-generic'
        jdk 'JDK 11.0.8'
    }

    stages {

        stage ('Set Environment variables') {
            steps {
                dir('workshop-organizer') {
                    sh 'chmod +x export-env.sh'
                    sh '. ./export-env.sh'
                }
            }
        }

        stage ('Maven Build') {
            steps {
                dir('workshop-organizer') {
                    dir('spring-project') {
                        sh 'pwd'
                        sh 'ls -la'
                        sh 'mvn clean package'
                    }
                }
            }
        }

        stage ('Build Docker Image') {
            steps {
                dir('workshop-organizer') {
                    sh 'docker-compose build'
                }
            }
        }

        stage ('Run Docker Containers') {
            steps {
                dir('workshop-organizer') {
                    sh 'docker-compose down'
                    sh 'docker-compose up -d'
                }
            }
        }
       
    }
}
