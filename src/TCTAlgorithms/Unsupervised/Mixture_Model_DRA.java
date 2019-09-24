package TCTAlgorithms.Unsupervised;

import TCTAlgorithms.Unsupervised.Clusterer;
import java.util.ArrayList;
import java.util.HashSet;
import weka.core.Instances;

public class Mixture_Model_DRA extends Clusterer{
    
    int kClusters;
    double [] clustersProbabiilities;
    
    
    public Mixture_Model_DRA(int numInstances, int kClusters) {
        super(numInstances);
        this.kClusters = kClusters;
        clustersProbabiilities = new double[kClusters];
    }

    @Override
    public boolean buildCluster(Instances data, int rep) {
        //probabilidade da Instancia x pertencer ao Cluster y
        double probabilityIC = 0;
        double maior = 0;
        int cluster = 0;
        boolean ok =true;
        
        //adiciona o valor dos atributos(clusters) ao vetor
        for (int attribute = 0; attribute < kClusters; attribute++) {
            for (int instance = 0; instance < data.numInstances(); instance++) {
                clustersProbabiilities[attribute] += data.instance(instance).value(attribute);
            }
        }
        
        //faz a média probabilistica para cada cluster
        for (int attributes = 0; attributes < kClusters; attributes++) {
            clustersProbabiilities[attributes] =  clustersProbabiilities[attributes]/kClusters;
        }
        
        
        for (int instance = 0; instance < data.numInstances(); instance++) {
            for (int attribute = 0; attribute < kClusters; attribute++) {
                probabilityIC = clustersProbabiilities[attribute] * data.instance(instance).value(attribute);
                if (probabilityIC > maior) {
                    cluster = attribute;
                    maior = probabilityIC;
                }
            }
            maior = 0;
            //adiciona esse doc no cluster mais indicado
            this.getClustering().get(cluster).add(instance);
        }
        
        return ok;
    }
    
    public void initialInstances(Instances data){
        ArrayList<HashSet<Integer>> clusters = new ArrayList<>();
        for (int cluster = 0; cluster < kClusters; cluster++) {
            HashSet<Integer> newCluster = new HashSet<>();
            clusters.add(newCluster);
        }
        super.setClustering(clusters);
        
        for (int cluster = 0; cluster < kClusters; cluster++) {
            clustersProbabiilities [cluster] = 0;
        }
    }
    
}
