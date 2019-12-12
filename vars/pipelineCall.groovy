def call(body){

    pipeline {
        agent {label 'jenkins-slave'}

        stages{

            stage('jib build') {
                steps {
                    mvn compile jib:build
                }
            }
        }
    }
}