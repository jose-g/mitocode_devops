apiVersion = '0.0.0'
pipeline{
	agent any
	environment{
		CONTAINER_API_LABEL = "${params.CONTAINER_API_LABEL}"
		MI_INTERNAL_IP = "${params.MI_INTERNAL_IP}"
	}
	stages{
		stage('Prepare api version'){
			agent{
				docker {image 'maven:3.6.3-jdk-11-slim'}
			}
			steps{
				echo "Getting api version with maven"
				echo "Before ${apiVersion}"
				sh 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout > api.txt'
				script {
					apiVersion = "${CONTAINER_API_LABEL}:" + readFile('api.txt').trim() + "-" + env.BUILD_NUMBER
				}
				echo "After ${apiVersion}"
			}
		}
		stage('Setup compose environment'){
			steps{
				echo "Building api image ${apiVersion}"
				sh "docker build -t ${apiVersion} ."
				echo "Generate docker-compose file"
				sh "sed -i 's@{{API_DOCKER_IMAGE}}@${apiVersion}@g' docker-compose.dist"
				sh "cat docker-compose.dist"
				sh "docker-compose -f docker-compose.dist up -d"
				sh "sleep 20"
				sh "docker-compose -f docker-compose.dist ps"
			}
		}
		stage('Setup postman compose environment'){
			steps{
				sh "sed -i 's@{{internalIp}}@${MI_INTERNAL_IP}@g' ./postman/calculator-api_Collection.postman_collection.json"
			    echo "executing docker postman compose"
				sh "docker-compose -f postman-compose.dist up"
				sh "sleep 5"
				sh "docker-compose -f postman-compose.dist ps"
			}
		}
	}
}


















