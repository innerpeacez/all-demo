def call(body){

    pipeline {
        agent {label 'jenkins-slave'}

        stages{

            stage("git source") {
                steps {
                    sh pwd
                    sudo -i ls -a
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