backendVersion = '0.0.0'
pipeline{
	agent any
	environment{
		CONTAINER_API_LABEL = "${params.CONTAINER_API_LABEL}"
	}
	stages{
		stage('Prepare api version'){
			agent{
				docker {image 'maven:3.6.3-jdk-11-slim'}
			}
			steps{
				echo "Getting api version with maven"
				echo "Before ${backendVersion}"
				sh 'mvn help:evaluate -Dexpression=project.version -q DforceStdout > backend.txt'
				script {
					backendVersion = "${CONTAINER_API_LABEL}:" + readFile('backend.txt').trim() + "-" + env.BUILD_NUMBER
				}
				echo "After ${backendVersion}"
			}
		}
	}
}


















