#!groovy

@Library('jenkinsfile') _

//func from shareibrary
def deploy = new org.devops.deploy()

//pipeline
pipeline{
    agent any
    
    stages{
        stage("GetCode"){
            steps{
                script{
                    sh 'rm -f *'
                    println("GetCode")
                    git 'https://github.com/me450722457/jenkinsfile.git'
                }
            }
        }
        stage("Deploy"){
            steps{
                script{
                  deploy.Deploy
                  ansiblePlaybook inventory: 'hosts', playbook: 'test.yml'
                }
            }
       }
        stage("QA"){
            steps {
                script{
                  println("QA")
                }
           }
       }
    }
    post {
        always{
            script{
                println("always")
            }
        }
        success{
            script{
                println("success")
            }
        }
        failure{
            script{
                println("failure")
            }
        }
        aborted{
            script{
                println("aborted")
            }
        }
    }
}
