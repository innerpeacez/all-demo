def call(body){

    pipeline {
        agent {label 'jenkins-slave'}

        stages{

            stage('jib build') {
                steps {
                    sh mvn compile jib:build
                }
            }
        }
    }
}