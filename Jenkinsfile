pipeline {
    agent { label 'windows' }
    options {
        disableConcurrentBuilds()
        skipDefaultCheckout()
        buildDiscarder(logRotator(numToKeepStr: '40'))
        timeout(time: 11, unit: 'HOURS')
    }

    stages {
    
		stage("Init"){
            steps {
        		bat """
            		mvn clean
            		"""
            }
        }
    
		stage("Tests"){
	        parallel {
                stage('api-tests') {
                    steps {
                    	lock('windows') {
		            		bat """
			            		mvn test -Dtest=APITest
			            		"""
		            	}
                    }
                }
                stage('ui-web-tests') {
                    steps {
                    	lock('windows') {
		            		bat """
			            		mvn test -Dtest=UIWebTest
			            		"""
		            	}
                    }
                }
                stage('ui-desktop-tests') {
                    steps {
                    	lock('windows') {
		            		bat """
			            		mvn test -Dtest=UIDesktopTest
			            		"""
		            	}
                    }
                }
	        }
        }

    }

    post {
        always {
	        archiveArtifacts '**/target/report/html/*.html,**/target/report/html/img/*'
	        junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
	        livingDocs featuresDir: 'ui-web-tests/target', format: 'ALL'
	        cucumber fileIncludePattern: '**/cucumber.json', sortingMethod: 'ALPHABETICAL'
        }
    }

}