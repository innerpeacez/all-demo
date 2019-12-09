def call(body){

    pipeline {
        agent any

        stages{

            stage("git source") {
                echo hello
            }

            stage('docker deploy') {
                echo "docker deploy"
            }
        }
    }
}