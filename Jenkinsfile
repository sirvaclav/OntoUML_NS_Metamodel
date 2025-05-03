pipeline {
  options {
    // Only keep the 50 most recent builds in Jenkins
    buildDiscarder(logRotator(numToKeepStr: "50"))
  }
  agent any
  tools {
    maven 'maven'
    jdk 'jdk21'
  }
  stages {
    stage('expand') {
      steps {
        sh "mvn clean expanders:expand -Dexpansion.clean=true"
      }
    }
    stage("build") {
      steps {
        dir('expansions/methamodelMetamodel') {
          sh "mvn clean package"
        }
      }
    }
    stage('deploy') {
      steps {
        // Deploy metamodel resource
        sh 'mvn deploy'

        // Deploy metamodel stack
        dir('expansions/methamodelMetamodel') {
          sh "mvn deploy -DskipTests"
        }
    }
  }
  post {
    always {
      buildReport(slackChannel: '#build-status')
      junit '**/target/surefire-reports/**/*.xml'
    }
  }
}
