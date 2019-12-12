def call(body){

    pipeline {
        agent {label 'jenkins-slave'}

        stages{

            stage("git source") {
                steps {
                    sleep 1000
                    sh pwd
                    echo "git clone"
                }
            }

            stage('docker deploy') {
//                echo "docker deploy"
//                mvn dockerfile:build
                steps {
                    echo "docker deploy"
                }
            }
        }
    }
}