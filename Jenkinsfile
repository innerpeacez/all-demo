pipeline {
    agent { docker 'maven:3.3.3' }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean package -DskipTest && java -jar target/jenkins-java-demo.jar'
            }
        }
    }
}