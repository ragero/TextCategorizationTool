package TCTAlgorithms.Unsupervised;

import TCTConfigurations.UnsupervisedLearning.Configuration_Unsupervised_VSM;
import TCTConfigurations.UnsupervisedLearning.Configuration_Unsupervised_Base;
import TCTParameters.UnsupervisedLearning.Parameters_B_K_M;
import TCTParameters.UnsupervisedLearning.Parameters_K_Means;
import TCTParameters.UnsupervisedLearning.Parameters_NMF;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.util.ArrayList;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class UnsupervisedVectorSpacedClustering {
    
    public static void learning(Configuration_Unsupervised_VSM configuration){
        ArrayList<File> entradas = new ArrayList<>();
        Arquivos.lista(new File (configuration.getDirEntrada()), entradas);
        for(int i=0; i<entradas.size(); i++){
            if(!entradas.get(i).getAbsolutePath().endsWith(".arff"))
                continue;
            try{ 
                //carrego o conjunto de dados da entrada
                DataSource conjunto = new DataSource(entradas.get(i).getAbsolutePath());

                //carrega os objetos contidos no conjunto
                Instances objetos = conjunto.getDataSet();

                StringBuilder outputFile = new StringBuilder();
                StringBuilder outputFilePar = new StringBuilder();
                outputFile.append(configuration.getDirSaida());
                outputFile.append("/");
                outputFile.append(objetos.relationName());
                outputFile.append("_Unsupervised_");
                
                ExecutorService threads = Executors.newFixedThreadPool(configuration.getNumThreads());

                if(configuration.isK_means()){
                    outputFilePar = new StringBuilder();
                    outputFilePar.append(outputFile.toString());
                    outputFilePar.append("KMeans_");
                    //num k atual, max iteration, minimum cohesion, num repetitions
                    Parameters_K_Means k_Parameter = configuration.getKmeans_Parameter();
                    ArrayList<Integer> ks;
                    if(k_Parameter.isAutoIncrementK()){
                        ks = k_Parameter.automaticallyGenerate(objetos);
                    }else{
                        ks = k_Parameter.getKs();
                    }
                    for(int experimento = 0; experimento < ks.size(); experimento++){
                        
                        StringBuilder outputFilePar2= new StringBuilder();
                        outputFilePar2.append(outputFilePar.toString());
                        outputFilePar2.append(ks.get(experimento)+"_");
                        outputFilePar2.append(k_Parameter.getMaxIteration()+"_");
                        outputFilePar2.append(k_Parameter.getMinimumCohesion()+"_");
                        outputFilePar2.append(configuration.getNumReps());
                        
                        //inicia as centroides daquele determinado ciclo com o valor especifico de k's
                        K_Means kmeans = new K_Means(objetos.numInstances(), ks.get(experimento));
                        kmeans.setMinimumDif(k_Parameter.getMinimumCohesion());
                        kmeans.setIteracoes(k_Parameter.getMaxIteration());
                        Learning(kmeans, configuration, outputFilePar2.toString(), objetos,threads);
                    }
                }
                if(configuration.isB_k_means()){
                    outputFilePar = new StringBuilder();
                    outputFilePar.append(outputFile.toString());
                    outputFilePar.append("Bisecting-KMeans_");
                    
                    Parameters_B_K_M bKmeans_Parameter = configuration.getbKmeans_Parameter();
                    ArrayList<Integer> ks;
                    if(bKmeans_Parameter.isAutoIncrementK()){
                        ks = bKmeans_Parameter.automaticallyGenerate(objetos);
                    }else{
                        ks = bKmeans_Parameter.getKs();
                    }
                    for(int experimento = 0; experimento < ks.size(); experimento++){
                        StringBuilder outputFilePar2= new StringBuilder();
                        outputFilePar2.append(outputFilePar.toString());
                        outputFilePar2.append(ks.get(experimento)+"_");
                        outputFilePar2.append(bKmeans_Parameter.getMaxIteration()+"_");
                        outputFilePar2.append(bKmeans_Parameter.getMinimumCohesion()+"_");
                        outputFilePar2.append(configuration.getNumReps());
                        
                        Bisecting_Kmeans bKmeans = new Bisecting_Kmeans(objetos.numInstances(), ks.get(experimento), configuration.getNumReps());
                        bKmeans.setMinimumDif(bKmeans_Parameter.getMinimumCohesion());
                        bKmeans.setIteracoes(bKmeans_Parameter.getMaxIteration());
                        Learning(bKmeans, configuration, outputFilePar2.toString(), objetos,threads);
                    }
                }
                if(configuration.isNmf()){
                    outputFilePar = new StringBuilder();
                    outputFilePar.append(outputFile.toString());
                    outputFilePar.append("NMF_");
                    Parameters_NMF nmf_Parameter = configuration.getNmf_Parameter();
                    ArrayList<Integer> ks;
                    if(nmf_Parameter.isAutoIncrementK()){
                        ks = nmf_Parameter.automaticallyGenerate(objetos);
                    }else{
                        ks = nmf_Parameter.getKs();
                    }
                    for (int experiment = 0; experiment < ks.size(); experiment++) {
                        StringBuilder outputFilePar2= new StringBuilder();
                        outputFilePar2.append(outputFilePar.toString());
                        outputFilePar2.append(ks.get(experiment)+"_");
                        outputFilePar2.append(nmf_Parameter.getMaxIteration()+"_");
                        outputFilePar2.append(nmf_Parameter.getMinimumDiference()+"_");
                        outputFilePar2.append(configuration.getNumReps());
                        
                        NMF nmf = new NMF(objetos.numInstances(), ks.get(experiment));
                        nmf.setIteracoes(nmf_Parameter.getMaxIteration());
                        nmf.setLda(nmf_Parameter.isLda());
                        nmf.setLdaDir(configuration.getDirSaida());
                        nmf.setMinimumDif(nmf_Parameter.getMinimumDiference());
                        Learning(nmf, configuration, outputFilePar2.toString(), objetos, threads);
                    }
                }
                
                threads.shutdown();
                
                boolean exit = false;
                while(exit == false){
                    if(threads.isTerminated()){
                        System.out.println("Process concluded successfully");
                        configuration.getEmail().getContent().append(configuration.toString());
                        configuration.getEmail().send();
                        exit = true;
                    }else{
                        Thread.sleep(1000);
                    }
                }
                
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error when generating file");
                System.exit(0);
            }
            
        } 
  
    }
    
    
    public static void Learning(Clusterer cluster, Configuration_Unsupervised_Base configurationuration, String outputFile, Instances data, final ExecutorService threads){
        
        Results result;
        try{
            final File output = new File(outputFile);
            final File outputResult = new File(output.getAbsolutePath() + ".txt");
            if(outputResult.exists()){
                return;
            }
            
            result = new Results(output,configurationuration.getNumReps());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int rep=0; rep<configurationuration.getNumReps();rep++){
                        long begin = System.currentTimeMillis();
                        cluster.buildCluster(data, rep);
                        long end = System.currentTimeMillis();
                        result.computeEvaluationMeasures(cluster, data, rep, end-begin);  
                    }
                    result.saveResults();
                }
            });
            threads.execute(thread);
            
        }catch(Exception e){
             System.err.println("Error when generating a classifier.");
             configurationuration.getEmail().getContent().append(e.getMessage());
             configurationuration.getEmail().getContent().append(configurationuration.toString());
             configurationuration.getEmail().send();
             e.printStackTrace();
             System.exit(0);
        }
    }
    
    
}
