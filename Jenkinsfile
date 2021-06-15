env.finalTag = '';

pipeline { 
    agent {
      label 'maven'
    }

    environment {
        BUILD_NAME = 'dms-misura-service-build'
        DEPLOYMENT_NAME = 'dms-misura-service'
        JAR_NAME = 'dms-misura-service-0.0.1-SNAPSHOT.jar'
    }

    stages { 
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

        stage('Build') { 
            steps {
                echo 'Service build' 
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Image') { 
            steps {
                echo 'Service Image build' 
                script {
                    openshift.withCluster("ocp-noprod") {
                        openshift.withCredentials("jenkins-ci-cd-noprod") {
                            openshift.withProject('1499-ci-cd') {

                                /* Controllo esistenza imagestream */
                                if (!openshift.selector('imagestream', "${DEPLOYMENT_NAME}").exists()) {
                                    println('Imagestream non trovato. Lo creo.');
                                    openshift.raw("create imagestream ${DEPLOYMENT_NAME}");
                                }

                                /* Aggiornamento buildconfig */
                                println('Aggiornamento BuildConfig.');
                                if (openshift.selector('bc', "${BUILD_NAME}").exists()) {
                                    openshift.raw("delete bc ${BUILD_NAME}");
                                }
                                openshift.raw("apply -f build.yml");

                                /* Prende l'ultimo tag */
                                def tagFromSh = sh(returnStdout: true, script: 'git tag --points-at HEAD').trim();

                                /* Se l'ultimo tag esiste, allora lo setto come tag dell'immagine */
                                if (tagFromSh != null && tagFromSh != '')
                                    env.finalTag = tagFromSh;
                                /* Se non esiste, vuol dire che è una commit standard, metto in latest per sviluppo */
                                else
                                    env.finalTag = 'latest';


                                /* ~~~~~~~~~~~~~~~~~~~~~ Aggiornamento versione BuildConfig ~~~~~~~~~~~~~~~~~~~~~ */
                                /* Serve actions[0].out perché l'output del comando lo piazza lì */
                                def bc = openshift.raw("get bc ${BUILD_NAME} -o json").actions[0].out;

                                /* Non posso usare writeJSON perché "dc" è una stringa e non un JSONObject */
                                writeFile(file: 'tempBc.json', text: bc)
                                def bc_file = readJSON(file: 'tempBc.json');

                                /* Aggiornamento versione presa da tag del repository*/
                                def bc_initVersion = bc_file.spec.output.to.name;
                                def bc_finalVersion = bc_initVersion.split(':')[0] + ':' + env.finalTag;
                                println('Nuovo tag in BC:' + bc_finalVersion);

                                /* Setto nel json la nuova versione */
                                bc_file.spec.output.to.name = bc_finalVersion;
                                sh 'rm -r tempBc.json'
                                writeJSON(file: 'tempBc.json', json: bc_file);

                                /* Replace della configurazione */
                                openshift.raw("replace -f tempBc.json");

                                /* ~~~~~~~~~~~~~~~~~~~~~ Start build ~~~~~~~~~~~~~~~~~~~~~ */
                                def bld = openshift.startBuild("${BUILD_NAME}","--from-file=target/${JAR_NAME}")

                                bld.untilEach {
                                  return it.object().status.phase == "Running"
                                }

                                bld.logs('-f')
                            }
                        }
                    }
                }
            }
        }

        stage('Update DeploymentConfig') { 
            steps {
                script {
                    openshift.withCluster("ocp-noprod") {
                        openshift.withCredentials("jenkins-ci-cd-noprod") {
                            openshift.withProject('1499-d-summer') {

                                /* Serve actions[0].out perché l'output del comando lo piazza lì */
                                def dc = openshift.raw("get dc ${DEPLOYMENT_NAME} -o json").actions[0].out;

                                /* Non posso usare writeJSON perché "dc" è una stringa e non un JSONObject */
                                writeFile(file: 'tempDc.json', text: dc)
                                def dc_file = readJSON(file: 'tempDc.json');

                                /* Aggiornamento versione presa da tag del repository*/
                                def dc_initVersion = dc_file.spec.triggers[0].imageChangeParams.from.name;
                                def dc_finalVersion = dc_initVersion.split(':')[0] + ':' + env.finalTag;
                                println('Nuovo tag in DC:' + dc_finalVersion);

                                /* Setto nel json la nuova versione */
                                dc_file.spec.triggers[0].imageChangeParams.from.name = dc_finalVersion;
                                dc_file.spec.triggers[0].imageChangeParams.from.namespace = '1499-ci-cd';
                                sh 'rm -r tempDc.json'
                                writeJSON(file: 'tempDc.json', json: dc_file);

                                /* Replace della configurazione */
                                openshift.raw("replace -f tempDc.json");
                            }
                        }
                    }
                }
            }
        }
    }

    post {

        failure {
            mail to: 'Claudio.Bevilacqua@eng.it,Massimiliano.Picca@eng.it,Ciro.DAlessandro@eng.it,Francesco.Cuomo@eng.it,Mirko.Flaminio@eng.it',
            from: 'jenkins@snam.com',
            subject: "Status of pipeline:  ${env.JOB_NAME} ${currentBuild.fullDisplayName}",
            body: "${env.BUILD_URL}  has result ${currentBuild.result}"
        }

        unstable {
            mail to: 'Claudio.Bevilacqua@eng.it,Massimiliano.Picca@eng.it,Ciro.DAlessandro@eng.it,Francesco.Cuomo@eng.it,Mirko.Flaminio@eng.it',
            from: 'jenkins@snam.com',
            subject: "Status of pipeline:  ${env.JOB_NAME} ${currentBuild.fullDisplayName}",
            body: "${env.BUILD_URL}  has result ${currentBuild.result}"
        }

        changed {
           mail to: 'Claudio.Bevilacqua@eng.it,Massimiliano.Picca@eng.it,Ciro.DAlessandro@eng.it,Francesco.Cuomo@eng.it,Mirko.Flaminio@eng.it',
            from: 'jenkins@snam.com',
            subject: "Status of pipeline:  ${env.JOB_NAME} ${currentBuild.fullDisplayName}",
            body: "${env.BUILD_URL}  has result ${currentBuild.result}"
        }
    }
}
