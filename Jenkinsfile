pipeline {
    agent any
    stages {
        stage('Build test code') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Run selenium grid') {
            steps {
                sh 'docker-compose up -d'
            }
        }
        stage('Execute test') {
            steps {
                sh 'mvn test'
                sh 'docker-compose down'
            }
        }
    }
    post {
        always {
            script {
                allure([
                        includeProperties: false,
                        jdk              : '',
                        properties       : [],
                        reportBuildPolicy: 'ALWAYS',
                        results          : [[path: 'target/allure-results']]
                ])
            }
            }
        }
    }