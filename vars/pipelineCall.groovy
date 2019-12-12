def call(body){

    pipeline {
        agent {label 'jenkins-slave'}

        stages{

            stage('jib build') {
                steps {
                    checkout scm
                    sh mvn compile jib:build
                }
            }
        }
    }
}