package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import weka.core.Instances;

public class Bisecting_Kmeans extends Prototype_Based{
    
    private int numReps;

    public Bisecting_Kmeans(int numInstances, int numkClusters, int numRep) {
        super(numInstances, numkClusters);
        numReps=numRep;
    }
    
    
    //chamada de media precisa usar data original
    //chamada de closest usa data do cluster sendo dividido
    @Override
    public boolean buildCluster(Instances data, int actualRep){
        boolean ok = true;
        double lowest=1;
        double highest=0;
        double bestCohesion=0;
        double actualCohesion=0;
        int numberClusters;
        int cluster=0;
        int iteration;
        Iterator<Integer> instances;
        ArrayList<HashSet<Integer>> clustering=null;
        ArrayList<HashSet<Integer>> bestClustering=null;
        HashSet<Integer> worstCluster;
        ArrayList<HashMap<Integer,Double>> centroids=null;
        ArrayList<HashMap<Integer,Double>> bestCentroids=null;

        this.setInstances(mappingInstances(data));
        //inicializei um grupo no array contendo todos documentos
        initialInstances(data);
        //fiz a média da centroide relacionada a esse grupo
        media(data, super.getCentroides(), super.getClustering());
        //adiciona ao array de coesão a coesão do grupo que acabou de ser instanciado
        super.getCohesions().add(Evaluation.clusteringCohesion(super.getClustering(), super.getCentroides(), this.getInstances()));
        
        while(super.getClustering().size() < super.getK()){
            for(int clusters=0;clusters<super.getCohesions().size();clusters++){
                    if(super.getCohesions().get(clusters)<lowest){
                        lowest=super.getCohesions().get(clusters);
                        //marca qual cluster tem a menor cohesion
                        cluster=clusters;
                    }
            }
            //remove o cluster com a menor coesão
            worstCluster=super.getClustering().remove(cluster);
            //remove a coesão do array
            super.getCohesions().remove(cluster);
            //remove a centroide ligada ao cluster
            super.getCentroides().remove(cluster);
            for(int repetition=0; repetition<numReps; repetition++){
                iteration=super.getIteracoes();
                //armazena as metades do cluster com menor cohesão
                clustering = clusterDivision(worstCluster, actualRep);
                ArrayList<HashSet<Integer>> proximidadesAntiga=null;

                //inicializa a centroids dos novo clusters após a divisao
                centroids = new ArrayList<>();
                //adiciona 2 hashmap ao vetor (um para cada cluster, após sua divisão)
                for (int clusters = 0; clusters < clustering.size(); clusters++) {
                    HashMap<Integer,Double> terms = new HashMap<>();
                    centroids.add(terms);
                }

                //media dá valores às centroides apos a divisao do cluster
                media(data, centroids, clustering);
                double cohesion=Evaluation.clusteringCohesion(clustering, centroids, this.getInstances());
                double dif=cohesion;
                //entra nos testes com o cluster dividido em 2
                while(classInequality(clustering, proximidadesAntiga) && iteration>0 && super.getMinimumDif()<dif){
                    proximidadesAntiga=clustering;
                    closest(centroids, clustering);
                    media(data, centroids, clustering);
                    //dif recebe coesão da iteração antiga
                    dif=cohesion;
                    //atualiza nova cohesão depois de mais uma iteração de agrupamento
                    cohesion=Evaluation.clusteringCohesion(clustering, centroids, this.getInstances());
                    //calcula a diferença com coesão antiga e nova
                    dif=cohesion-dif;
                    //Evaluation.fMeasure(data, this);
                    iteration--;
                }
                actualCohesion = Evaluation.clusteringCohesion(clustering, centroids, this.getInstances());
                if(actualCohesion>bestCohesion){
                    bestClustering=clustering;
                    bestCentroids=centroids;
                }
                
                //coesao volta ao valor maximo
                lowest=1;
            }
            //apenas 0 e 1 por que foi dividido em dois o cluster, isso na função clusterDivision
            
            super.getClustering().add(bestClustering.get(0));
            super.getClustering().add(bestClustering.get(1));

            //adiciona a centroid dos dois novos clusters
            super.getCentroides().add(bestCentroids.get(0));
            super.getCentroides().add(bestCentroids.get(1));

            //adiciona as coesoes dos novos clusters
            super.getCohesions().add(Evaluation.clusterCohesion(bestClustering.get(0), centroids.get(0), this.getInstances()));
            super.getCohesions().add(Evaluation.clusterCohesion(bestClustering.get(1), centroids.get(1), this.getInstances()));
        }
        return ok;
    }
    

    public void initialInstances(Instances data){
        //inicia o cluster
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        //instancia o cluster unico inicial
        HashSet<Integer> cluster = new HashSet<>();
        //adiciona ao cluster os documentos que possuem atributos não nulos
        for(int doc=0;doc<data.numInstances();doc++){
            if(this.getInstances()[doc] != null)
                cluster.add((Integer)doc);
        }
        clustering.add(cluster);
        super.setClustering(clustering);
        
        ArrayList<Double> cohesion = new ArrayList<>();
        super.setCohesions(cohesion);
        
        //inicia a lista de centroides (apenas uma é adicionada)
        ArrayList<HashMap<Integer,Double>> centroids = new ArrayList<>();
        HashMap<Integer,Double> terms = new HashMap();
        centroids.add(terms);
        super.setCentroides(centroids);
        
//        for(int term =0;term<terms.length;term++){
//            terms[term]=0.0;
//        }
    }
    
   
    public ArrayList<HashSet<Integer>> clusterDivision(HashSet<Integer>cluster, int seed){
        Iterator<Integer> instance;
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        
        for(int group=0;group<2;group++){
            HashSet<Integer> novo = new HashSet();
            clustering.add(novo);
        }
        
        //inicializa os dois clusters randomicamente
        Random groups = new Random(seed);
        instance = cluster.iterator();
        while(instance.hasNext()){
            clustering.get(groups.nextInt(2)).add(instance.next());
        }
        
        return clustering;
    }
}
