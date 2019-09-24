
package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import weka.core.Instances;


public class Evaluation {
    
    public static double clusteringCohesion(ArrayList<HashSet<Integer>>clustering, ArrayList<HashMap<Integer,Double>> centroids, HashMap<Integer,Double>[] instances){
        double cohesion=0;
        double result=0;
        Iterator<Integer> instance;
        for(int cluster=0;cluster<clustering.size();cluster++){
            result+=clusterCohesion(clustering.get(cluster), centroids.get(cluster), instances);
        }
        result=result/clustering.size();
        return result;
    }
    
    public static double clusterCohesion(HashSet<Integer>cluster, HashMap<Integer,Double> centroid, HashMap<Integer,Double>[] instances){
        double result=0;
        int inst;
        Iterator<Integer> instance;
        instance=cluster.iterator();
        
        //usa o iterator para passar pelas instancias do cluster e calcular
        //a distancia da instancia em relação à centroide
        
        while(instance.hasNext()){
            inst = instance.next();
            result += Clusterer.cosseno(instances[inst],centroid);
        }
        
        result=result/cluster.size();
        return result;
    }
    
  
    public static double entropy(Instances data, Clusterer clusterer){
        double result = 0;
        double clusterEntropy = 0;
        int classCount = 0;
        int clusterSize = 0;
        double classValues = -1;
        data.setClassIndex(data.numAttributes()-1);
        int classNum = data.numClasses();
        double probij;
        Iterator<Integer> iterator;
        
        for (HashSet<Integer> clustering : clusterer.getClustering()) {
            for (int j = 0; j<classNum; j++) {
                iterator = clustering.iterator();
                //passa pelo cluster e soma a existencia de j(classe) nele
                while(iterator.hasNext()){
                    if(j==data.instance(iterator.next()).classValue()){
                        classCount++;
                    }
                }
                probij = (double)classCount / clustering.size();
                if(probij!=0 && clustering.size()!=0){
                    clusterEntropy += probij*log2(probij)*-1; 
                }
                classCount=0;
            }
            result += clusterEntropy*clustering.size() / data.numInstances();
            clusterEntropy=0;
        }
        
        return result;
    }
    
   
    public static double log2(double number){
        double logNumerador;
        double logDenominador;
        
        logNumerador=Math.log10(number);
        logDenominador=Math.log10(2);
        return logNumerador/logDenominador;
    }
    
    public static double purity(Instances data, Clusterer clusterer){    
        int instances;
        double result = 0;
        data.setClassIndex(data.numAttributes()-1);
        int[] classes = new int [data.numClasses()];
        int ocorrente = -1;
        int[] clusterMajority = new int [clusterer.getClustering().size()];
        Iterator<Integer> iterator;
        
        for (int i = 0; i < classes.length; i++) {
            classes[i]=0;
        }
        
        
        for(int cluster=0; cluster<clusterer.getClustering().size(); cluster++){
            iterator=clusterer.getClustering().get(cluster).iterator();
            if(clusterer.getClustering().get(cluster).size() != 0){
                while(iterator.hasNext()){
                    //no vetor classes eu aumento o contador daquela classe pra aquele grupo k
                    instances = iterator.next();
                    classes[(int)data.instance(instances).classValue()]++;
                }            

                //para todas classes do cluster, seleciona a mais ocorrente
                for(int classe = 0; classe < classes.length; classe++){
                    if(classes[classe] > ocorrente){
                        ocorrente = classes[classe];
                    }
                }

                clusterMajority[cluster] = ocorrente;
                for (int j = 0; j < classes.length; j++) {
                    classes[j]=0;
                }
                ocorrente = 0;
                result += (1*(double)clusterMajority[cluster]/clusterer.getClustering().get(cluster).size())*clusterer.getClustering().get(cluster).size()/data.numInstances();
            }
        }
        
        return result;
        
    }
    
    //CC = class and matrix
    public static double correlationCC(Instances data, Clusterer clusterer, ArrayList<HashSet<Integer>>clustering){
        double result = 0;
        int [][] clusterMatriz = new int [data.numInstances()][data.numInstances()];
        int [][] classMatriz = new int [data.numInstances()][data.numInstances()];

        int f00=0;
        int f10=0;
        int f01=0;
        int f11=0;
        double jaccard=0;
        double randS=0;
        boolean sair=false;
        
        //preenche a matriz binaria do cluster relacionando similaridade dentro de cada cluster
        for(int docLine= 0; docLine<data.numInstances(); docLine++){
            for (int docColumn = 0; docColumn < docLine; docColumn++) {
                for(int cluster=0; cluster<clustering.size();cluster++){
                    if(clustering.get(cluster).contains((Integer)docLine) && clustering.get(cluster).contains((Integer)docColumn)){
                        clusterMatriz[docLine][docColumn] = 1;
                        break;
                    }
                }
            }
        }
        
        //preenche a matriz binaria da classe relacionando similaridade dentro de cada classe
        for(int i= 0; i<data.numInstances(); i++){
            for (int j = 0; j < data.numInstances(); j++) {
                if(data.instance(i).classValue() == data.instance(j).classValue()){
                    classMatriz[i][j] = 1;
                }else{
                    classMatriz[i][j] = 0;
                }
            }
        }
        
        for(int i=0; i<classMatriz.length;i++){
            for(int j=0; j<i;j++){
                if(clusterMatriz[i][j]==0 && classMatriz[i][j]==0){
                    f00++;
                }
                if(clusterMatriz[i][j]==1 && classMatriz[i][j]==0){
                    f10++;
                }
                if(clusterMatriz[i][j]==0 && classMatriz[i][j]==1){
                    f01++;
                }
                if(clusterMatriz[i][j]==1 && classMatriz[i][j]==1){
                    f11++;
                }
            }
        }
        
        jaccard = f11/f01+f10+f11;
        randS = f00+f11/f00+f11+f01+f10;
        
    
        return result;
    }
    
    //confusion matrix
    public static void fMeasure(Instances data, Clusterer clusterer){
        int ocorrente=0;
        int instances;
        int tP=0;
        int fP=0;
        int fN=0;
        int bkup_TP = 0;
        int bkup_FP = 0;
        int bkup_FN = 0;
        int f1=0;
        data.setClassIndex(data.numAttributes()-1);
        int[] classes = new int[data.numClasses()];
        double[] clusterClass = new double[clusterer.getClustering().size()];
        double precision=0;
        double recall=0;
        Iterator<Integer> iterator;
        
        //descobre qual classe cada cluster representa, armazenando em clusterClass
        for(int cluster=0; cluster<clusterer.getClustering().size(); cluster++){
            iterator=clusterer.getClustering().get(cluster).iterator();
            while(iterator.hasNext()){
                //no vetor classes eu aumento o contador daquela classe pra aquele grupo
                instances = iterator.next();
                classes[(int)data.instance(instances).classValue()]++;
            }
            
            //determina qual classe o cluster representa
            for(int classe = 0; classe < classes.length; classe++){
                if(classes[classe] > ocorrente){
                    ocorrente = classes[classe];
                    clusterClass[cluster] = classe;
                }
            }
            ocorrente=0;
            
            for (int j = 0; j < classes.length; j++) {
                classes[j]=0;
            }
        }
        
        int[][] confusionMatrix = new int[classes.length][classes.length];
        for(int line=0;line<classes.length;line++){
            for(int column=0;column<classes.length;column++){
                confusionMatrix[line][column]=0;
            }
        }
        
        
        for(int cluster=0; cluster<clusterer.getClustering().size();cluster++){
            iterator=clusterer.getClustering().get(cluster).iterator();
            while(iterator.hasNext()){
                instances=iterator.next();
                //linha = classificação real do doc; coluna = classe do grupo que ele está
                confusionMatrix[(int)data.instance(instances).classValue()][(int)clusterClass[cluster]]++;
            }
        }
        
        for(int line=0;line<classes.length;line++){
            System.out.print(line+" |");
            for(int column=0;column<classes.length;column++){
                System.out.print(confusionMatrix[line][column]+"\t");
            }
            System.out.println("");
        }
        
        clusterer.setMacroRecall(0);
        clusterer.setMicroRecall(0);
        clusterer.setMicroPrecision(0);
        clusterer.setMacroPrecision(0);
        //roda pro numero de classes existentes
        for(int classe=0; classe<classes.length; classe++){
            for(int realClass=0;realClass<classes.length;realClass++){
                for(int clusteringClass=0;clusteringClass<classes.length;clusteringClass++){
                    //se o clustering(coluna) é o mesmo da minha classe observada
                    if(classe==clusteringClass){
                        //sua classe real é igual à classe observada
                        if(realClass==classe){
                            tP+=confusionMatrix[realClass][clusteringClass];
                        }
                        //o clustering diz que o objeto é daquela classe observada, porém o sua classe real é outra
                        else{
                            fP+=confusionMatrix[realClass][clusteringClass];
                        }
                    }
                    //se o clustering(coluna) que estou observando é diferente da minha classe
                    else{ 
                        //se os objetos são da minha classe real, porém classificados como pertencentes a outros clustering
                        if(realClass==classe){
                            fN+=confusionMatrix[realClass][clusteringClass];
                        }
                    }
                }
            }
            
            
            
            //armazena os para aquela classe para poder medir micro f-measure
            bkup_TP+=tP;
            bkup_FP+=fP;
            bkup_FN+=fN;
            
            //faz a medida de macro f-measure caso exista truePositive ou falsePositive para aquela classe
            if(tP!=0 || fP!=0){
                clusterer.setMacroPrecision(clusterer.getMacroPrecision()+((double)tP/(tP+fP)));
                clusterer.setMacroRecall(clusterer.getMacroRecall()+((double)tP/(tP+fN)));
            }
            
            tP=0;
            fP=0;
            fN=0; 
        }
        
        clusterer.setMicroPrecision((double)bkup_TP/(bkup_TP+bkup_FP));
        clusterer.setMicroRecall((double)bkup_TP/(bkup_TP+bkup_FN));
        clusterer.setMacroPrecision(clusterer.getMacroPrecision()/data.numClasses());
        clusterer.setMacroRecall(clusterer.getMacroRecall()/data.numClasses());
        clusterer.setAccuracy((double)bkup_TP/data.numInstances());
        clusterer.setF1Macro(2*clusterer.getMacroPrecision()*clusterer.getMacroRecall()/(clusterer.getMacroPrecision()+clusterer.getMacroRecall()));
        clusterer.setF1Micro(2*clusterer.getMicroPrecision()*clusterer.getMicroRecall()/(clusterer.getMicroPrecision()+clusterer.getMicroRecall()));
    }
  
}

