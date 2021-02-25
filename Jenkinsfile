pipeline {
    agent { docker { image 'maven:3.3.3' } }
    stages {
        stage('build') {
            steps {
                echo 'Start build...'
                sh 'mvn --version'
                sh 'mvn clean install'
                sh 'docker-compose build'
                sh 'docker-compose up'
            }
        }

        stage('test') {
             steps {
               echo 'Start testing...'
           }
        }

        stage('deploy') {
            steps {
               echo 'Start deploy...'
               echo 'Successfully deployed...'
            }
        }
    }

     post {
          always {
              sh "docker-compose down || true"
          }

          success {
              bitbucketStatusNotify buildState: "SUCCESSFUL"
          }

          failure {
              bitbucketStatusNotify buildState: "FAILED"
          }
     }
}