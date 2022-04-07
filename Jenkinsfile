pipeline {
  agent any

  stages {
      stage('Gradle Build') {
          steps {
              echo 'Building...'
              echo "Running ${env.BUILD_ID} ${env.BUILD_DISPLAY_NAME} on ${env.NODE_NAME} and JOB ${env.JOB_NAME}"
              sh './gradlew test jacocoTestReport'
          }
      }
      stage('代码审查') {
          steps {
              script {
                  scannerHome = tool 'SONAR_SCANNER_HOME'
              }
              withSonarQubeEnv('sonar-8.4.0') {
                  sh "${scannerHome}/bin/sonar-scanner"
              }
          }
      }
      stage('Sanity check') {
          steps {
              input "Does the test environment look ok?"
          }
      }
      stage('Test') {
          steps {
              echo 'Testing...'
              sh './gradlew test'
          }
      }
      stage('Deploy') {
          steps {
              echo 'Deploying...'
          }
      }
  }
  post {
      success {
          mail to: '1124782639@qq.com',
               subject: "Succeed Pipeline: ${currentBuild.fullDisplayName}",
               body: "Succeed with ${env.BUILD_URL}"
      }
  }

}