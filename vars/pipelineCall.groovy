def call(body){
    pipeline {
        agent {
            kubernetes {
                yaml """
metadata:
  labels:
    jekins-slave: jenkins-slave
spec:
  containers:
  - name: jnlp
    env:
    - name: CONTAINER_ENV_VAR
      value: jnlp
    resources:
      limits:
        cpu: 1
        memory: 1Gi
      requests:
        cpu: 1
        memory: 1Gi
  - name: maven
    image: registry-hz.rubikstack.com/library/maven:3.6.3-jdk8
    command:
    - cat
    tty: true
    env:
    - name: CONTAINER_ENV_VAR
      value: maven
    volumeMounts:
    - name: repo-maven-cache
      mountPath: /root/.m2
  - name: kubectl
    image: cnych/kubectl
    command:
    - cat
    tty: true
    env:
    - name: CONTAINER_ENV_VAR
      value: kubectl
  volumes:
  - name: repo-maven-cache
    persistentVolumeClaim:
      claimName: pvc-jenkins-maven-cache
"""
            }
        }
        stages {
            stage('Run maven') {
                steps {
                    container('maven') {
                        sh 'mvn -version'
                    }
                    container('busybox') {
                        sh '/bin/busybox'
                    }
                }
            }
        }
    }




//    pipeline {
//        agent {label 'jenkins-slave'}
//
//        stages{
//
//            stage('jib build') {
//                steps {
////                    sh mvn compile jib:build
//                    echo "jib build"
//                }
//            }
//        }
//    }
//    apiVersion: v1
//    kind: Pod
//    metadata:
//    labels:
//    app: jenkins-slave
//    spec:
//    securityContext:
//    runAsUser: 0
//    privileged: true
//    containers:
//    - name: jnlp
//    tty: true
//    workingDir: /home/jenkins/agent
//    image: registry.cn-shanghai.aliyuncs.com/mydlq/jnlp-slave:3.35-5-alpine

//    pipeline {
////        environment {
////            IMAGE_TAG = sh (returnStdout: true, script: 'echo "build-${BRANCH_NAME//\\//_}-$BUILD_NUMBER"').trim()
////        }
//        agent {
//            kubernetes {
//                defaultContainer 'jnlp'
//                yamlFile 'KubernetesPod.yaml'
//            }
//        }
//        stages {
//            stage('Run maven') {
//                steps {
//                    container('maven') {
//                        echo "代码编译打包"
////                        sh 'mvn clean install'
//                    }
//                }
//            }
//            stage('Build image') {
//                steps {
//                    container('docker') {
//                        script {
//                            echo "构建Docker镜像"
//                            def image = docker.build("reg.nexus.wmqhealth.com/tools/cicd-test:" + "$IMAGE_TAG", ".")
//                            withDockerRegistry([credentialsId:'docker-registry', url:"https://reg.nexus.wmqhealth.com"]){
//                                image.push()
//                            }
//                        }
//                    }
//                }
//            }

//            stage('Deploy') {
//                steps {
//                    container('kubectl') {
//                        script {
//                            echo "部署项目"
//                            withKubeConfig(clusterName: 'develop', contextName: 'develop', credentialsId: 'kube', namespace: 'cicdtest', serverUrl: 'https://rancher.wmqhealth.com/k8s/clusters/c-xg99q') {
//                                sh 'kubectl set image deployment/cicd-test cicd-test=reg.nexus.wmqhealth.com/tools/cicd-test:$IMAGE_TAG --namespace cicdtest'
//                            }
//                        }
//                    }
//                }
//            }
//
//        }
//    }
}

