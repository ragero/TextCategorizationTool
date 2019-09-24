
package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import weka.core.Instances;


public class K_Means extends Prototype_Based {

    
    public K_Means(int numInstances, int numkClusters){
        super(numInstances, numkClusters);
    }

    @Override
    public boolean buildCluster(Instances data, int rep){
        this.setInstances(mappingInstances(data));
        initialInstances(data, rep); 
        media(data, super.getCentroides(), super.getClustering());
        ArrayList<HashSet<Integer>> proximidadesAntiga=null;
        double cohesion=Evaluation.clusteringCohesion(super.getClustering(), super.getCentroides(), this.getInstances());
        double dif=cohesion;
        int iteration=super.getIteracoes();
        boolean ok = true;
        //essa função realiza o agrupamento até seu fim, com um numero especificado de iteraçoes
        //chama a similaridade cosseno e anota o mais próximo, logo depois de anotar todos
        //chama a função de média, e se repete até q o grupo não mude de uma iteração pra outra, ou chegue ao seu limite de iterações
        while(classInequality(super.getClustering(), proximidadesAntiga) && iteration > 0 && super.getMinimumDif() < dif){
            proximidadesAntiga=super.getClustering();
            closest(super.getCentroides(),super.getClustering());
            media(data, super.getCentroides(), super.getClustering());
            //dif recebe coesão da iteração antiga
            dif = cohesion;
            //atualiza nova cohesão depois de mais uma iteração de agrupamento
            cohesion = Evaluation.clusteringCohesion(super.getClustering(), super.getCentroides(), this.getInstances());
            //calcula a diferença com coesão antiga e nova
            dif = cohesion-dif;
            iteration--;
            
        }
        return ok;
    }
    
    //adiciona arrays representantes dos clusters e randomicamente
    //adiciona o indice dos documentos aos clusters criados
    public void initialInstances(Instances data, int seed){
        //instancia um clustering
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        super.setClustering(clustering);
        //adiciona clusters ao clustering
        for(int group=0;group<super.getK();group++){
            HashSet<Integer> novo = new HashSet();
            super.getClustering().add(novo);
        }
        
        //instancia as centroides
        ArrayList<HashMap<Integer,Double>> centroids = new ArrayList<>();
        for(int groups=0;groups<super.getK();groups++){
            HashMap<Integer,Double> terms = new HashMap();
            centroids.add(terms);
        }
        super.setCentroides(centroids);
        
        //randomicamente adiciona documentos aos clusters
        //desconsiderando documentos mapeados como nulos==sem atributos nao nulo
        Random groups = new Random(seed);
        for (int doc = 0; doc < data.numInstances(); doc++) {
            if(this.getInstances()[doc]!=null)
                super.getClustering().get(groups.nextInt(super.getK())).add((Integer)doc);
        }
    }

}
