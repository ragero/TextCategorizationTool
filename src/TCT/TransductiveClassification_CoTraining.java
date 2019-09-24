//*********************************************************************************
// Author: Rafael Geraldeli Rossi
// E-mail: rgr.rossi at gmail com
// Last-Modified: January 29, 2015
// Description: 
// Description: Class to perform Inctive Transductive Learning and Evaluation. Co-
//              training (Zhu & Goldberg, 2009) is used to perform transductive
//              learning. Two views are "generated by splitting" the feature set.
// References: - X. Zhu, A. B. Goldberg, Introduction to Semi-Supervised Learning, 
//               Morgan and Claypool Publishers, 2009.
//*********************************************************************************

package TCT;

import TCTConfigurations.TransductiveLearning.TransductiveConfiguration_CoTraining;
import TCTIO.ListFiles;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TransductiveClassification_CoTraining {
    
    /*Function to read a document-term matrix and set parameters and algorithms to induce a classification model through Self-Training */
    public static  void learning(TransductiveConfiguration_CoTraining configuration){
        
        ExecutorService threads = Executors.newFixedThreadPool(configuration.getNumThreads());

        System.out.println("Obtendo Lista de Arquivos...");
        ArrayList<String> filesIn = ListFiles.listFilesStr(new File(configuration.getDirEntrada()));
        System.out.println("Carregando arquivos Arquivos...");

        try {
            for (int i = 0; i < filesIn.size(); i++) {

                if (!filesIn.get(i).endsWith(".arff")) {
                    continue;
                }
                System.out.println(filesIn.get(i));
                System.out.println("Loading ARFF file");

                DataSource trainSource = new DataSource(filesIn.get(i)); //Carregando arquivo de Dados
                Instances dataOriginal = trainSource.getDataSet();

                int numClasses = 0;
                Attribute classAtt = null;

                classAtt = dataOriginal.attribute(dataOriginal.numAttributes() - 1); //Setting the last feature as class
                dataOriginal.setClass(classAtt);
                numClasses = classAtt.numValues();

                for (int j = 0; j < numClasses; j++) {
                    System.out.println(j + ": " + classAtt.value(j));
                }

                StringBuilder outputFile = new StringBuilder();
                StringBuilder outputFilePar = new StringBuilder();
                outputFile.append(configuration.getDirSaida());
                outputFile.append("/");
                outputFile.append(dataOriginal.relationName());
                outputFile.append("_Transductive_CoTraining_");

                for (int numExInsTrain = 0; numExInsTrain < configuration.getNumInstPerClassAddTraining().size(); numExInsTrain++) {
                    for (int numEx = 0; numEx < configuration.getParametersNumLabeledInstancesPerClass().getNumLabeledInstancesPerClass().size(); numEx++) {
                        double numLabeledInstances = configuration.getParametersNumLabeledInstancesPerClass().getNumLabeledInstancesPerClass(numEx);
                        if (configuration.isMNB()) {
                            //System.out.println("Number of labeled intances per class: " + numLabeledInstances);
                            //System.out.println("Classification Algorithm: Multinomial Naive Bayes");
                            //System.out.println("Número de exemplos para incrementar o modelo de classificacao: " + (int)configuration.getParametersnumInstPerClassInseridosTreinamento().getnumInstPerClassInseridosTreinamento(numExInsTrain));
                            outputFilePar = new StringBuilder();
                            outputFilePar.append(outputFile.toString());
                            outputFilePar.append("MNB_");
                            outputFilePar.append(numLabeledInstances);
                            outputFilePar.append("_");
                            if (configuration.isPorcentage() == true) {
                                outputFilePar.append("percentage");
                                outputFilePar.append("_");
                            } else {
                                outputFilePar.append("real");
                                outputFilePar.append("_");
                            }
                            outputFilePar.append(configuration.getNumInstPerClassAddTraining().get(numExInsTrain));
                            Classifier[] classifiersView1 = new Classifier[configuration.getNumReps()];
                            Classifier[] classifiersView2 = new Classifier[configuration.getNumReps()];
                            for (int rep = 0; rep < configuration.getNumReps(); rep++) {
                                classifiersView1[rep] = new NaiveBayesMultinomial();
                                classifiersView2[rep] = new NaiveBayesMultinomial();
                            }
                            learning(configuration, threads, classifiersView1, classifiersView2, dataOriginal, outputFilePar.toString(), numLabeledInstances, numClasses, (int) configuration.getNumInstPerClassAddTraining().get(numExInsTrain));
                        }
                    }
                }

            }

            threads.shutdown();

            boolean exit = false;
            while (exit == false) {
                if (threads.isTerminated()) {
                    System.out.println("Process concluded successfully");
                    configuration.getEmail().getContent().append(configuration.toString());
                    configuration.getEmail().getContent().append(configuration.toString());
                    configuration.getEmail().send();
                    exit = true;
                } else {
                    Thread.sleep(1000);
                }
            }
        } catch (OutOfMemoryError e) {
            configuration.getEmail().getContent().append("OutOfMemory!!!!");
            configuration.getEmail().getContent().append(configuration.toString());
            configuration.getEmail().send();
            e.printStackTrace();
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Error when generating a classifier.");
            configuration.getEmail().getContent().append(e.getMessage());
            configuration.getEmail().getContent().append(configuration.toString());
            configuration.getEmail().send();
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    //Function to run and evaluate Co-Training
    public static void learning(final TransductiveConfiguration_CoTraining configuration, final ExecutorService threads, final Classifier[] classifiersView1, final Classifier[] classifiersView2, final Instances dataOriginal, String outputFile, final double numInstPerClass, final int numClasses, final int numExInstTreino){
        
        try{
            
            final Results results;
            
            final File output = new File(outputFile);
            final File outputResult = new File(output.getAbsolutePath() + ".txt");
            if(outputResult.exists()){
                return;
            }
            final File outputTemp = new File(output.getAbsolutePath() + ".tmp");
            
            if(outputTemp.exists()){
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(output.getAbsolutePath() + ".tmp"));
                results = (Results)objInput.readObject();
                objInput.close();
            }else{
                results = new Results(output, configuration.getNumReps(), 1, "Transductive-CoTraining");
            }
            
            //System.out.println("Output: " + output.getAbsolutePath());
            
            for(int rep=0;rep<configuration.getNumReps();rep++){
                
                if(results.getComplete(rep, 0) == true){
                    continue;
                }
                
                final int numRep = rep;
                
                Thread thread = new Thread(new Runnable() {
                @Override
                public void run(){
                
                    final Integer[][] confusionMatrix = new Integer[numClasses][numClasses]; 
                    for(int class1=0;class1<numClasses;class1++){
                        for(int class2=0;class2<numClasses;class2++){
                            confusionMatrix[class1][class2] = 0;
                        }
                    }

                    for(int randView=1;randView<=configuration.getNumRandomViews();randView++){
                           
                        final int rand = randView;  
                        final Instances dataTrainView1 = new Instances(dataOriginal,0);
                        final Instances dataTestView1 = new Instances(dataOriginal,0);
                        final Instances dataTrainView2 = new Instances(dataOriginal,0);
                        final Instances dataTestView2 = new Instances(dataOriginal,0);

                        Instances data = new Instances(dataOriginal);
                        data.randomize(new Random(numRep));

                        splitViews(configuration, data, dataTrainView1, dataTestView1, dataTrainView2, dataTestView2, numInstPerClass, ((numRep+1) * (rand+1)));

                        long begin = System.currentTimeMillis();

                        try{
                            while(dataTestView1.numInstances()>0){
                                classifiersView1[numRep].buildClassifier(dataTrainView1);
                                Evaluations.CoTrainingEvaluation1(classifiersView1[numRep], classifiersView2[numRep], dataTrainView1, dataTestView1, dataTrainView2, dataTestView2, confusionMatrix, numExInstTreino);
                                classifiersView2[numRep].buildClassifier(dataTrainView2);
                                Evaluations.CoTrainingEvaluation2(classifiersView1[numRep], classifiersView2[numRep], dataTrainView1, dataTestView1, dataTrainView2, dataTestView2, confusionMatrix, numExInstTreino);
                            }    
                        }catch(OutOfMemoryError e){
                            configuration.getEmail().getContent().append("OutOfMemory!!!!");
                            configuration.getEmail().getContent().append(configuration.toString());
                            configuration.getEmail().send();
                            e.printStackTrace();
                            System.exit(0);
                        }catch(Exception e){
                            System.err.println("Error when generating a classifier.");
                            configuration.getEmail().getContent().append(e.getMessage());
                            configuration.getEmail().getContent().append(configuration.toString());
                            configuration.getEmail().send();
                            e.printStackTrace();
                            System.exit(0);
                        }

                        long end  = System.currentTimeMillis();
                        //Incremental Averaging
                        long averageBuildingTime = results.getBuildingTime(numRep, 0);
                        averageBuildingTime = averageBuildingTime + ((end - begin) - averageBuildingTime)/randView;
                        results.setBuildingTime(numRep, 0, averageBuildingTime);
                        results.setClassificationTime(numRep, 0, averageBuildingTime);

                    }
                    results.computeEvaluationMeasures(confusionMatrix, numClasses, numRep, 0);
                    results.setComplete(numRep, 0, true);
                        
                }});
                
                threads.execute(thread);
                
            }   
            
        }catch(OutOfMemoryError e){
            configuration.getEmail().getContent().append("OutOfMemory!!!!");
            configuration.getEmail().getContent().append(configuration.toString());
            configuration.getEmail().send();
            e.printStackTrace();
            System.exit(0);
        }catch(Exception e){
            System.err.println("Error when generating a classifier.");
            configuration.getEmail().getContent().append(e.getMessage());
            configuration.getEmail().getContent().append(configuration.toString());
            configuration.getEmail().send();
            e.printStackTrace();
            System.exit(0);
        }
        
    }
    
    
    //Generate two views by splitting the feature set
    private static void splitViews(TransductiveConfiguration_CoTraining configuration, Instances data, Instances dataTrainView1, Instances dataTestView1, Instances dataTrainView2, Instances dataTestView2, double numInstPerClass, int indRep){
        
        int numTerms = data.numAttributes() - 1;
        int numTermsView1 = numTerms / 2;
        int numTermsView2 = numTerms - numTermsView1;
       
        int numClasses = data.numClasses();
        int[] totalInstClass = new int[numClasses];
        int[] instPerClass = new int[numClasses];
        int[] instChosenByClass = new int[numClasses];
        for(int classe=0;classe<numClasses;classe++){
            totalInstClass[classe] = 0;
            instPerClass[classe] = 0;
            instChosenByClass[classe] = 0;
        }
        
        if(configuration.isPorcentage() == true){
            for(int inst=0;inst<data.numInstances();inst++){
                Instance instance = data.instance(inst);
                int classe = (int)instance.classValue();
                int value = totalInstClass[classe];
                value++;
                totalInstClass[classe] = value;
            }    
        }
        
        if(configuration.isPorcentage() == false){
            for(int classe=0;classe<numClasses;classe++){
                instPerClass[classe] = (int)numInstPerClass;
            }    
        }else{
            for(int classe=0;classe<numClasses;classe++){
                double value = totalInstClass[classe] * ((double)numInstPerClass/(double)100);
                if(value < 1){
                    value = 1;
                }
                instPerClass[classe] = (int)value;
            }
        }
        
        for(int inst=0;inst<data.numInstances();inst++){
            Instance instance = data.instance(inst);
            int classe = (int)instance.classValue();
            int value = instChosenByClass[classe];
            value++;
            if(value > instPerClass[classe]){
                dataTestView1.add(instance);
                dataTestView2.add(instance);
            }else{
                dataTrainView1.add(instance);
                dataTrainView2.add(instance);
                instChosenByClass[classe] = value;
            }
        }
        
        Random random = new Random(indRep);
        
        for(int term=0;term<numTerms;term++){
            int randomAtrView = random.nextInt(2);
            if(dataTestView1.numAttributes() == numTermsView1){
                randomAtrView = 0;
            }
            if(dataTestView2.numAttributes() == numTermsView2){
                randomAtrView = 1;
            }
            String nameAtr = data.attribute(term).name();
            if(randomAtrView == 0){
                Attribute att = dataTrainView2.attribute(nameAtr);
                int index = att.index();
                dataTrainView2.deleteAttributeAt(index);
                dataTestView2.deleteAttributeAt(index);
            }else{
                Attribute att = dataTrainView1.attribute(nameAtr);
                int index = att.index();
                dataTrainView1.deleteAttributeAt(index);
                dataTestView1.deleteAttributeAt(index);
            }
        }
    }
    
   
}