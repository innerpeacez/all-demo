def call(body){

    pipeline {
        agent {label 'jenkins-slave'}

        stages{

            stage('jib build') {
                steps {
                    sh cd ./workspace/java-test
                    sh mvn compile jib:build
                }
            }
        }
    }
}