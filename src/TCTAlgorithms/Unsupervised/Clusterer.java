
package TCTAlgorithms.Unsupervised;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import weka.core.Instance;
import weka.core.Instances;


public abstract class Clusterer {
    
    private int iteracoes;
    private ArrayList<HashSet<Integer>> clustering;//separa os clusters em hashset, facilita acesso, funções nessa classe
    private HashMap<Integer,Double>[] instances;//para fazer media da centroid mapeia os atributos das instancias
    private double microPrecision;
    private double microRecall;
    private double macroRecall;
    private double macroPrecision;
    private double accuracy;
    private double f1Micro;
    private double f1Macro;
    
    public Clusterer(int numInstances){
        instances= new HashMap[numInstances];
        clustering = new ArrayList<>();
        setIteracoes(100);
        setMacroRecall(0);
        setMacroPrecision(0);
        setF1Micro(0);
        setF1Macro(0);
    }
    
    
    //get-set
    public double getF1Micro() {    
        return f1Micro;
    }

    public void setF1Micro(double f1Micro) {
        this.f1Micro = f1Micro;
    }

    public double getF1Macro() {
        return f1Macro;
    }

    public void setF1Macro(double f1Macro) {
        this.f1Macro = f1Macro;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {    
        this.accuracy = accuracy;
    }

    public double getMicroPrecision() {
        return microPrecision;
    }

    public void setMicroPrecision(double microPrecision) {
        this.microPrecision = microPrecision;
    }

    public double getMicroRecall() {
        return microRecall;
    }

    public void setMicroRecall(double microRecall) {
        this.microRecall = microRecall;
    }

    public double getMacroRecall() {
        return macroRecall;
    }

    public void setMacroRecall(double macroRecall) {
        this.macroRecall = macroRecall;
    }

    public double getMacroPrecision() {
        return macroPrecision;
    }
    
    public void setMacroPrecision(double macroPrecision) {    
        this.macroPrecision = macroPrecision;
    }

    public int getIteracoes() {
        return iteracoes;
    }

    public void setIteracoes(int iteracoes) {
        this.iteracoes = iteracoes;
    }

    public ArrayList<HashSet<Integer>> getClustering() {
        return clustering;
    }

    public void setClustering(ArrayList<HashSet<Integer>> clustering) {
        this.clustering = clustering;
    }

    public HashMap<Integer, Double>[] getInstances() {
        return instances;
    }

    public void setInstances(HashMap<Integer, Double>[] instance) {
        instances = instance;
    }
    
    
    
    //armazena os valores não nulos para dos atributos do documento em hash, e os armazena em um array
    public abstract boolean buildCluster(Instances data, int rep);
    
    //mapeia as instancias em um hashmap para conseguir acessar os valores dos atributos
    //com mais facilidade e poder fazer a media das centroids
    public static HashMap<Integer,Double>[] mappingInstances(Instances data){
        HashMap<Integer,Double>[] mappedInstances = new HashMap[data.numInstances()];
        for(int doc=0; doc<data.numInstances();doc++){
            HashMap<Integer,Double> attributes= new HashMap<>();
            for(int att=0; att<data.numAttributes()-1; att++){
                if(data.instance(doc).value(att)!=0){
                    attributes.put((Integer)att,(Double)data.instance(doc).value(att));
                }  
            }

            mappedInstances[doc]=attributes;

        }
        return mappedInstances;
    }
    
    //recebe as centroides e altera seus valores, ou seja, sua posição com base no vetor de proximidade
    //da pra fazer sem instances
    public void media(Instances objetos, ArrayList<HashMap<Integer,Double>> centroides, ArrayList<HashSet<Integer>>groups){
        Iterator<Integer> instance;
        int bkup;
        double numerador = 0;
        
        for(int centroide = 0; centroide < centroides.size(); centroide++){
            if(groups.get(centroide)!= null && (!groups.get(centroide).isEmpty())){
                instance = groups.get(centroide).iterator();
                //o vetor armazena o valor de todo atributo diferente de 0, em sua respectiva posição
                double[] terms = new double[objetos.numAttributes()-1];
                //armazena uma somatoria da ocorrencia dos termos de cada documento pertencente ao cluster
                while(instance.hasNext()){
                    bkup=instance.next();
                    //recebe instancia por instancia os valores dos atributos não nulos
                    Set<Integer> keys = instances[bkup].keySet();
                    for(Integer attList : keys){
                        terms[attList] += instances[bkup].get(attList);
                    }
                }
                for(int term = 0; term<objetos.numAttributes()-1;term++){
                    //insere em todas as chaves da centroid (hashmap) um valor da média dos termos
                    centroides.get(centroide).put(term, terms[term]/groups.get(centroide).size());
                }
            }
        }
        
    }
    
    public boolean classInequality(ArrayList<HashSet<Integer>>newOne, ArrayList<HashSet<Integer>>oldOne){
        
        if(oldOne!=null){
            for(int cluster=0; cluster < newOne.size(); cluster++){
                if(!(newOne.get(cluster).equals(oldOne)))
                    return true;
            }
            return false;
        }
        return true;
    }
    
    //feito para fazer a rede, já que é estabelecida antes de mapear os documentos
    //logo, não estão no formato de HashMap
    public static double cosseno(double[] inst, double[] inst2){
        double normaDoc = 0.0;
        double normaDoc2 = 0.0; 
        double produto = 0.0;
        double result=0.0;
        
        for (int i = 0; i < inst.length; i++) {
            normaDoc += Math.pow(inst[i], 2);
        }
        if(normaDoc==0){
            return 0;
        }
        for (int i = 0; i < inst2.length; i++) {
            normaDoc2 += Math.pow(inst2[i], 2);
        }
        if(normaDoc2==0){
            return 0;
        }
        for (int i = 0; i < inst.length; i++) {
            produto += inst[i]*inst2[i];
        }
        result=(produto / ((Math.sqrt(normaDoc) * Math.sqrt(normaDoc2))));
        return result;
    }
    
    public static double cosseno(HashMap<Integer,Double> doc, HashMap<Integer,Double> doc2){
        double normaDoc = 0.0;
        double normaDoc2 = 0.0; //no caso pode também ser a centroid
        double produto = 0.0;
        double result =0.0;
        Set<Integer> terms = doc.keySet();
        Set<Integer> terms2 = doc2.keySet();
        
        for(Integer term : terms){
            normaDoc += Math.pow(doc.get(term), 2);
        }
        if(normaDoc == 0)
            return 0;
        for(Integer term : terms2){
            normaDoc2 += Math.pow(doc2.get(term), 2);
        }
        if(normaDoc2 == 0)
            return 0;
        
         for(Integer term : terms){
             if(doc2.containsKey(term))
                 produto += doc.get(term) * doc2.get(term);
         }
         result=(produto / ((Math.sqrt(normaDoc) * Math.sqrt(normaDoc2))));
         return result;      
    }
    
    
    //determina os documentos pertencentes a cada centroide
    public void closest(ArrayList<HashMap<Integer,Double>> centroides, ArrayList<HashSet<Integer>> clustering){
        double similar = -1; 
        double maior=similar;
        int newCluster=0;
        int oldCluster=0;
        int doc;
        ArrayList<HashSet<Integer>> localClustering = new ArrayList<>();
        Iterator<Integer> instance;
        
        for(HashSet<Integer> clusters : clustering){
            HashSet<Integer> cluster = new HashSet();
            localClustering.add(cluster);
        }
         
        for(int cluster = 0; cluster < localClustering.size(); cluster ++){
            instance = clustering.get(cluster).iterator();
            while(instance.hasNext()){
                doc = instance.next();
                //o cluster antigo sempre sera de onde ela esta saindo
                oldCluster = cluster;
                for(int clusters = 0;clusters < localClustering.size(); clusters++){
                    //o mesmo ocorre aqui na instancia sendo medida
                    similar = cosseno(this.instances[doc], centroides.get(clusters));
                    //se a similaridade desse documento for a maior até o momento, ele atualiza esse cluster como o ideal
                    if(similar > maior){
                        maior=similar;
                        newCluster=clusters;
                    }
                }
                maior=-1;
                //se os clusters "novo" e "velho" forem diferentes, realiza as seguintes operações
                if(oldCluster!=newCluster){
                    instance.remove();
                    //instance.remove é uma função que previne ConcurrentModificationException
                    //que e ativada quando modifica a colection ligada ao iterator
                    localClustering.get(newCluster).add(doc);
                }
            }
        }
            
        //adiciona no clustering as instancias guardadas localmente
        for(int cluster=0;cluster<clustering.size();cluster++){
             instance=localClustering.get(cluster).iterator();
             while(instance.hasNext()){
                 clustering.get(cluster).add(instance.next());
             }
        }
    }
       
}
