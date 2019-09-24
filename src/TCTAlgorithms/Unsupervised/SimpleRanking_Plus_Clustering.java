
package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import weka.core.Instances;


public class SimpleRanking_Plus_Clustering extends Prototype_Based{
    
    Neighbor[] adjListTerms; //vizinhos dos termos
    Neighbor[] adjListDocs;//vizinhos dos documentos
    private double[][] termsRank; //rank dos termos para os clusters
    private double[][] docsRank;//rank dos documentos para os clusters
    private double[][] docsRankTemp;//temporario que guarda valores antigos dos ranks dos docs
    private double[][] clustersCenters;//centroides
    private double alfa;

    public SimpleRanking_Plus_Clustering(int numInstances, int numkClusters) {
        super(numInstances, numkClusters);
    }
    
    
    public void initialInstances(Instances data, int rep){
        Random random = new Random(rep);
        double[] sumRanks = new double[data.numInstances()];
        adjListDocs = new Neighbor[data.numInstances()];
        adjListTerms = new Neighbor[data.numAttributes()-1];
        docsRank = new double[data.numInstances()][this.getK()];
        docsRankTemp = new double[data.numInstances()][this.getK()];
        termsRank = new double[data.numAttributes()-1][this.getK()];
        clustersCenters = new double[this.getK()][this.getK()];
        
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        ArrayList<HashMap<Integer,Double>> clustersCenters = new ArrayList<>();
        
        
        //inicializa os clusters
        for (int k = 0; k < this.getK(); k++) {
            HashSet<Integer> cluster = new HashSet();
            clustering.add(cluster);
        }
        this.setClustering(clustering);
        
        //inicializa as centroids dos clusters
        for (int cluster = 0; cluster < this.getK(); cluster++) {
            for (int k = 0; k < this.getK(); k ++) {
                
            }
        }
        
        //inicializa todos vizinhos dos termos, ou seja, seus links com documentos
        for (int term = 0; term < data.numAttributes()-1; term++) {
            Neighbor docsNeighbors = new Neighbor();
            adjListTerms[term] = docsNeighbors;
        }
        
        //iniciaiza valores do vetor de rank condicional para todos docs
        for (int inst = 0; inst < data.numInstances(); inst++) {
            //inicializa todos vizinhos dos documentos, ou seja, seus links com termos
            Neighbor termsNeighbors = new Neighbor();
            adjListDocs[inst] = termsNeighbors;
            //inicializa vetor de somas
            sumRanks[inst] = 0;
            //k = numero de clusters atual
            for (int k = 0; k < this.getK(); k++) {
                docsRank[inst][k] = random.nextDouble();
                //soma o rank total (inicializado) daquele doc
                sumRanks[inst] += docsRank[inst][k];
            }
        }
        
        //normaliza as inicializações aleatórias 
        for (int inst = 0; inst < data.numInstances(); inst++) {
            //k = numero de clusters atual
            for (int k = 0; k < this.getK(); k++) {
                docsRank[inst][k] = docsRank[inst][k]/sumRanks[inst];
                //copia todos valores para o temporário
                docsRankTemp[inst][k] = docsRank[inst][k];
            }
        }
        
        //após inicialização e normalização atribui os documentos aos clusters
        double maior=0;
        int ind=0;
        
        for (int inst = 0; inst < docsRank.length; inst++) {
            maior =0;
            for (int k = 0; k < this.getK(); k++) {
                if (docsRank[inst][k] > maior) {
                    maior = docsRank[inst][k];
                    ind = k;
                }
            }
            
            this.getClustering().get(ind).add(inst);
        }
        
        //para todas instancias adiciona os termos ocorrentes na sua lista de adjacencia
        for (int inst = 0; inst < data.numInstances(); inst++) {
            //para todos os termos, adiciona os documentos em que ocorre na sua lista de adjacencia
            for (int term = 0; term < data.numAttributes()-1; term++) {
                if (data.instance(inst).value(term) > 0) {
                    //instancia o documento vizinho do termo
                    IndexValue neighborDoc = new IndexValue();
                    //guarda o documento vizinho
                    neighborDoc.setIndex(inst);
                    //guarda o valor da ligação com o documento
                    neighborDoc.setValue(data.instance(inst).value(term));
                    //adiciona o documento na sua vizinhança
                    adjListTerms[term].getNeighbors().add(neighborDoc);

                    //instancia o termo vizinho do documento
                    IndexValue neighborTerm = new IndexValue();
                    //guarda o termo que ocorre, logo, seu vizinho
                    neighborTerm.setIndex(term);
                    //adiciona o valor da ligação com o termo
                    neighborTerm.setValue(data.instance(inst).value(term));
                    //adiciona o termo na sua lista de adjacencia
                    adjListDocs[inst].getNeighbors().add(neighborTerm);
                }
            }
        }
        
    }
    
    
    public void initialInstances2(Instances data, int rep){
        Random random = new Random(rep);
        double[] sumRanks = new double[data.numInstances()];
        adjListDocs = new Neighbor[data.numInstances()];
        adjListTerms = new Neighbor[data.numAttributes()-1];
        docsRank = new double[data.numInstances()][this.getK()];
        docsRankTemp = new double[data.numInstances()][this.getK()];
        termsRank = new double[data.numAttributes()-1][this.getK()];
        clustersCenters = new double[this.getK()][this.getK()];
        
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        ArrayList<HashMap<Integer,Double>> clustersCenters = new ArrayList<>();
        
        //inicializa os clusters
        for (int k = 0; k < this.getK(); k++) {
            HashSet<Integer> cluster = new HashSet();
            clustering.add(cluster);
        }
        this.setClustering(clustering);
        
        //inicializa as centroids dos clusters
        for (int cluster = 0; cluster < this.getK(); cluster++) {
            for (int k = 0; k < this.getK(); k ++) {
                
            }
        }
        
        //inicializa todos vizinhos dos termos, ou seja, seus links com documentos
        for (int term = 0; term < data.numAttributes()-1; term++) {
            Neighbor docsNeighbors = new Neighbor();
            adjListTerms[term] = docsNeighbors;
        }
        
        for (int inst = 0; inst < data.numInstances(); inst++) {
            Neighbor termsNeighbors = new Neighbor();
            adjListDocs[inst] = termsNeighbors;
        }

        //inicializa valores do vetor de rank condicional para todos docs
        for (int k = 0; k < this.getK(); k++) {
            //inicializa vetor de somas
            sumRanks[k] = 0;
            for (int inst = 0; inst < data.numInstances(); inst++) {
                docsRank[inst][k] = random.nextDouble();
                //soma o rank total (inicializado) dos docs
                sumRanks[k] += docsRank[inst][k];
            }
        }
        
        //normaliza as inicializações aleatórias 
        //soma do rank condicional de todos inst naquele k tem que ser = 1
        for (int k = 0; k < this.getK(); k++) {
            for (int inst = 0; inst < data.numInstances(); inst++) {
                docsRank[inst][k] = docsRank[inst][k]/sumRanks[k];
            }
        }
        
        //após inicialização e normalização atribui os documentos aos clusters
        double maior=0;
        int ind=0;
        
        for (int inst = 0; inst < docsRank.length; inst++) {
            maior =0;
            for (int k = 0; k < this.getK(); k++) {
                if (docsRank[inst][k] > maior) {
                    maior = docsRank[inst][k];
                    ind = k;
                }
            }
            
            this.getClustering().get(ind).add(inst);
        }
        
        //para todas instancias adiciona os termos ocorrentes na sua lista de adjacencia
        for (int inst = 0; inst < data.numInstances(); inst++) {
            //para todos os termos, adiciona os documentos em que ocorre na sua lista de adjacencia
            for (int term = 0; term < data.numAttributes()-1; term++) {
                if (data.instance(inst).value(term) > 0) {
                    //instancia o documento vizinho do termo
                    IndexValue neighborDoc = new IndexValue();
                    //guarda o documento vizinho
                    neighborDoc.setIndex(inst);
                    //guarda o valor da ligação com o documento
                    neighborDoc.setValue(data.instance(inst).value(term));
                    //adiciona o documento na sua vizinhança
                    adjListTerms[term].getNeighbors().add(neighborDoc);

                    //instancia o termo vizinho do documento
                    IndexValue neighborTerm = new IndexValue();
                    //guarda o termo que ocorre, logo, seu vizinho
                    neighborTerm.setIndex(term);
                    //adiciona o valor da ligação com o termo
                    neighborTerm.setValue(data.instance(inst).value(term));
                    //adiciona o termo na sua lista de adjacencia
                    adjListDocs[inst].getNeighbors().add(neighborTerm);
                }
            }
        }
        
    }
    
    //calcula os rankings dos documentos baseados nos clusters em que estão contidos
    public void conditionalRankingCalculation(){
        double result = 0;
        double dif = 1;
        double linkValue = 0;
        double[] sum = new double[this.getK()];
        int linkInd = 0;
        Iterator<Integer> instance;
        
        //para todos os clusters
        for (int cluster = 0; cluster < this.getK(); cluster++) {
            //para todos ranks condicionais
            for(int k = 0; k < this.getK(); k++){
                //zera todos termos em relação àquele cluster
                for (int term = 0; term < adjListTerms.length; term++) {
                    termsRank[term][k]=0;
                }
                //inicializa/zera o vetor de somatorio de ranks
                sum[k] = 0;
                //propaga os ranks scores para os termos
                for (int term = 0; term < adjListTerms.length; term++) {
                    result = 0;
                    //para todos os documentos linkados àquele termo
                    for (int link = 0; link < adjListTerms[term].getNeighbors().size(); link++) {
                        //salva o valor da ligação entre o termo e o documento
                        linkValue = adjListTerms[term].getNeighbor(link).value;
                        //salva o indice do documento sendo observado (linkado)
                        linkInd = adjListTerms[term].getNeighbor(link).index;
                        //se o documento (linkado) está contido no cluster sendo observado
                        //então ele leva em conta essa ligação pra estimar o valor do seu rank para esse cluster
                        if(this.getClustering().get(cluster).contains(linkInd)){
                            //o valor do rank condicional para aquele termo e para aquele cluster(não normalizado)
                            result += linkValue * docsRank[linkInd][k];
                        }
                    }
                    termsRank[term][k] = result;
                    //soma o valor daquele rank no vetor para aquele cluster
                    sum[k] += result;
                }
            }
            
            
            //é possível fazer a normalização dos termos de outras formas, como a somatoria dos ranks condicionais somando 1
            //não usando assim, como a somatoria para todas instancias de um rank somano 1
            //normaliza os valores de ranks condicionais dos termos
            for (int k = 0; k < this.getK(); k++) {
                //normaliza conforme a formula
                for (int term = 0; term < termsRank.length; term++) {
                    termsRank[term][k] = termsRank[term][k]/sum[k];
                }
            }
            
            //para todos ranks condicionais
            for (int k = 0; k < this.getK(); k++) {
                //inicializa/zera o vetor de somatorio de ranks de documentos 
                sum[k] = 0;
                //propaga os ranks scores para os documentos
                //para todos os documentos
                for (int inst = 0; inst < adjListDocs.length; inst++) {
                    result = 0;
                    //salva valor do rank condicional dos documentos para os clusters no temp
                    docsRankTemp[inst][k] = docsRank[inst][k];
                    //para todos os termos linkados àquele documento
                    for (int link = 0; link < adjListDocs[inst].getNeighbors().size(); link++) {
                        //salva o valor da ligação entre o documento e o termo
                        linkValue = adjListDocs[inst].getNeighbor(link).value;
                        //salva o indice do termo sendo observado (linkado)
                        linkInd = adjListDocs[inst].getNeighbor(link).index;
                        //o valor do rank condicional para aquele documento e para aquele cluster(não normalizado)
                        result += linkValue * termsRank[linkInd][k];
                    }
                    docsRank[inst][k] = result;
                    //soma o valor daquele rank no vetor daquele cluster
                    sum[k] += result;
                }
            }

            //normaliza os valores de ranks condicionais dos documentos
            for (int k = 0; k < this.getK(); k++) {
                for (int inst = 0; inst < docsRank.length; inst++) {
                    //normaliza conforme a formula
                    docsRank[inst][k] = docsRank[inst][k]/sum[k];
                }
            }

            //atualiza valor dos rankings condicionais dos documentos
            for (int inst = 0; inst < docsRank.length; inst++) {
                for (int k = 0; k < this.getK(); k++) {
                    docsRank[inst][k] = docsRank[inst][k] * alfa + ((1-alfa) * docsRankTemp[inst][k]);
                }
            }
        }
    }
    
    //calcula a posição das centroides com base nos ranks condicionais dos documentos contidos no cluster
    public void clusterCenterCalculation(){
        Iterator<Integer> instance;
        int bkup;
        
        for(int cluster = 0; cluster < clustersCenters.length; cluster++){
            if(this.getClustering().get(cluster)!= null && (!this.getClustering().get(cluster).isEmpty())){
                instance = this.getClustering().get(cluster).iterator();
                //o vetor armazena o valor de todos rankings dos documentos componentes ao cluster
                double[] rankings = new double[this.getK()];
                //armazena uma somatoria da ocorrencia dos rankings de cada documento para um cluster
                while(instance.hasNext()){
                    //bkup é a instancia daquele cluster
                    bkup=instance.next();
                    //recebe instancia por instancia os valores dos seus rankings
                    for (int rank = 0; rank < this.getK(); rank++) {
                        rankings[rank] += docsRank[bkup][rank];
                    }
                }
                for(int rank = 0; rank < this.getK();rank++){
                    //atualiza os valores das centroides (as médias dos ranks)
                    clustersCenters[cluster][rank] = rankings[rank]/this.getClustering().get(cluster).size();
                }
            }
        }
    }
    
    //determina os documentos pertencentes a cada centroide de acordo com a centroid mais proxima
    public void closestCentroid(){
        double similar = -1; 
        double maior=similar;
        int newCluster=0;
        int oldCluster=0;
        int doc;
        ArrayList<HashSet<Integer>> localClustering = new ArrayList<>();
        Iterator<Integer> instance;
        
        for(HashSet<Integer> clusters : this.getClustering()){
            HashSet<Integer> cluster = new HashSet();
            localClustering.add(cluster);
        }
         
        for(int cluster = 0; cluster < localClustering.size(); cluster ++){
            instance = this.getClustering().get(cluster).iterator();
            while(instance.hasNext()){
                doc = instance.next();
                //o cluster antigo sempre sera de onde ela esta saindo
                oldCluster = cluster;
                for(int clusters = 0;clusters < localClustering.size(); clusters++){
                    //o mesmo ocorre aqui na instancia sendo medida
                    similar = cosseno(docsRank[doc], clustersCenters[clusters]);
                    //se a similaridade desse documento for a maior até o momento, ele atualiza esse cluster como o ideal
                    if(similar > maior){
                        maior = similar;
                        newCluster = clusters;
                    }
                }
                maior=-1;
                //se os clusters "novo" e "velho" forem diferentes, realiza as seguintes operações
                if(oldCluster != newCluster){
                    instance.remove();
                    //instance.remove é uma função que ativa ConcurrentModificationException
                    //que e ativada quando modifica a colection ligada ao iterator
                    localClustering.get(newCluster).add(doc);
                }
            }
        }
        
        //adiciona no clustering as instancias guardadas localmente
        for(int cluster=0;cluster < this.getClustering().size();cluster++){
             instance = localClustering.get(cluster).iterator();
             while(instance.hasNext()){
                 this.getClustering().get(cluster).add(instance.next());
             }
        }
    }
    
    
    @Override
    public boolean buildCluster(Instances data, int rep) {
        this.setInstances(mappingInstances(data));
        initialInstances2(data, rep);
        int iteration = this.getIteracoes();
        double dif = this.getMinimumDif();
        
        while(iteration > 0){
            
            clusterCenterCalculation();
            //atualiza o clustering com base na centroid mais proxima para cada documento
            closestCentroid();
            conditionalRankingCalculation();
            
            //calcula o valor da diferença dos ranks da iteração anterior para essa
            dif = 0;
            for (int inst = 0; inst < docsRank.length; inst++) {
                for (int k = 0; k < this.getK(); k++) {
                    dif += Math.abs(docsRank[inst][k] - docsRankTemp[inst][k]);
                }
            }
            dif = dif/docsRank.length;
            if (dif < this.getMinimumDif()) {
                break;
            }
            
            iteration --;
        }
        
        return true;
    }

    public Neighbor[] getAdjListTerms() {
        return adjListTerms;
    }

    public void setAdjListTerms(Neighbor[] adjListTerms) {
        this.adjListTerms = adjListTerms;
    }

    public Neighbor[] getAdjListDocs() {
        return adjListDocs;
    }

    public void setAdjListDocs(Neighbor[] adjListDocs) {
        this.adjListDocs = adjListDocs;
    }

    public double[][] getTermsRank() {
        return termsRank;
    }

    public void setTermsRank(double[][] termsRank) {
        this.termsRank = termsRank;
    }

    public double[][] getDocsRank() {
        return docsRank;
    }

    public void setDocsRank(double[][] docsRank) {
        this.docsRank = docsRank;
    }

    public double[][] getDocsRankTemp() {
        return docsRankTemp;
    }

    public void setDocsRankTemp(double[][] docsRankTemp) {
        this.docsRankTemp = docsRankTemp;
    }

    public double[][] getClustersCenters() {
        return clustersCenters;
    }

    public void setClustersCenters(double[][] clustersCenters) {
        this.clustersCenters = clustersCenters;
    }

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }
 
    
    
}
