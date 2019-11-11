pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn clean package -DskipTest && java -jar target/jenkins-java-demo.jar'
            }
        }
    }
}