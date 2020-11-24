pipeline {
    agent { label 'laptop2'}    
    stages {
        stage('Code and Dependencies'){
            parallel{
            stage('Checkout Code'){
                steps{
                    git 'https://github.com/pawanpadma/parallelExecution.git'
                }
            }
            stage('Install Dependencies'){
                steps{
					sh 'docker pull elgalu/selenium'
					
                    
                }
            }
		    stage('Install Dependenc'){
                steps{
					
					sh'docker pull dosel/zalenium'
                    
                }
            }
            }
        }
            stage ('Start Zalenium'){
                steps{
                    sh 'docker run --rm -ti --name zalenium -d -p 4444:4444 -e PULL_SELENIUM_IMAGE=true -v /var/run/docker.sock:/var/run/docker.sock -v /tmp/videos:/home/seluser/videos --privileged dosel/zalenium start'
                }
            }
            stage ('Run Tests'){
                steps{
                    sh 'mvn clean test'
                }
            }
            stage ('Stop Zalenium'){
                steps{
                    sh 'docker stop zalenium'
                }
            }
    }
}
