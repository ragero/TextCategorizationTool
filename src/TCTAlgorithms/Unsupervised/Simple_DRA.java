package TCTAlgorithms.Unsupervised;

import TCTAlgorithms.Unsupervised.Clusterer;
import java.util.ArrayList;
import java.util.HashSet;
import weka.core.Instances;


public class Simple_DRA extends Clusterer{

    int kClusters;
    
    public Simple_DRA(int numInstances, int kClusters) {
        super(numInstances);
        this.kClusters = kClusters;
    }


    @Override
    public boolean buildCluster(Instances data, int rep) {
        initialInstances(data);
        double maior = 0;
        int cluster = 0;
        boolean ok =true;
        
        for (int instance = 0; instance < data.numInstances(); instance++) {
            for (int attribute = 0; attribute < data.numAttributes()-1; attribute++) {
                //pega o valor do atributo mais alto e define ele como sendo o topico/cluster mais propenso
                if(data.instance(instance).value(attribute) > maior){
                    //conforme seus attributor(indicações) nomeia o mais indicado e guarda o grupo indicado
                    cluster = attribute;
                    maior = data.instance(instance).value(attribute);
                }
            }
            maior=0;
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
    }
}
