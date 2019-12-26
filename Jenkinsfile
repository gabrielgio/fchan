pipeline {
    agent { docker 'gabrielgio/clojure' }
    stages {
        stage('Run test') {
            steps {
                sh 'clojure -A:test'
            }
        }

        stage('Generate Uberjar') {
            steps {
               sh 'clojure -A:uberjar --app-version `mvn help:evaluate -Dexpression=project.version -q -DforceStdout` --app-group-id `mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout` --app-artifact-id `mvn help:evaluate -Dexpression=project.artifactId -q -DforceStdout`'
            }
        }

        stage('Deploy clojar') {
            steps {
                sh 'mvn deploy'
            }
        }

    }
}