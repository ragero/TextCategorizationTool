package TCTAlgorithms.Unsupervised;

import TCTConfigurations.UnsupervisedLearning.Configuration_Dimensionality_Reduction_Analysis;
import TCTAlgorithms.Unsupervised.Clusterer;
import java.io.File;
import java.util.ArrayList;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Dimensionality_Reduction_Analysis{

    public static void learning(Configuration_Dimensionality_Reduction_Analysis cDRA){
        ArrayList<File> entradas = new ArrayList<>();
        Arquivos.lista(new File (cDRA.getDirEntrada()), entradas);
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
                outputFile.append(cDRA.getDirSaida());
                outputFile.append("/");
                outputFile.append(objetos.relationName());
                outputFilePar = new StringBuilder();
                outputFilePar.append(outputFile.toString());
                
                if(cDRA.isSimpleDRA()){
                    outputFilePar.append("_Simple_Dimensionality_Reduction_Analysis_");
                    outputFilePar.append(cDRA.getText());
                    //aqui atributo equivale ao numero de grupo
                    Simple_DRA simpleDRA = new Simple_DRA(objetos.numInstances(), objetos.numAttributes()-1);
                    Learning(simpleDRA,outputFilePar.toString(),objetos);
                }
                if(cDRA.isMixtureModelDRA()){
                    outputFilePar.append("_Mixture_Model_Dimensionality_Reduction_Analysis_");
                    outputFilePar.append(cDRA.getText());
                    //aqui atributo equivale ao numero de grupo
                    Mixture_Model_DRA mixtureModelDRA = new Mixture_Model_DRA(objetos.numInstances(), objetos.numAttributes()-1);
                    Learning(mixtureModelDRA,outputFilePar.toString(),objetos);
                }                
                
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error when generating file");
                System.exit(0);
            }
        } 
    }
    
    
    public static void Learning(Clusterer cluster, String outputFile, Instances data){
        
        Results result;
        try{
            final File output = new File(outputFile);
            final File outputResult = new File(output.getAbsolutePath() + ".txt");
            if(outputResult.exists()){
                return;
            }
            
            result = new Results(output,1);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int rep=0; rep<1;rep++){
                        long begin = System.currentTimeMillis();
                        cluster.buildCluster(data, rep);
                        long end = System.currentTimeMillis();
                        result.computeEvaluationMeasures(cluster, data, rep, end-begin);  
                    }
                    result.saveResults();
                }
            });
            thread.run();
            
        }catch(Exception e){
            System.out.println("Error when generating file");
            e.printStackTrace();
        }
    }
    
}
