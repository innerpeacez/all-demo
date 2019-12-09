def call(body){

    pipeline {
        agent any

        stages{

            stage("git source") {
                steps {
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