package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import weka.core.Instances;


public class SimpleRanking extends Prototype_Based{
    
    Neighbor[] adjListTerms; //vizinhos dos termos
    Neighbor[] adjListDocs;//vizinhos dos documentos
    private double[][] termsRank; 
    private double[][] docsRank;
    private double[][] docsRankTemp;//temporario que guarda valores antigos dos ranks dos docs
    private double alfa;
    
    public SimpleRanking(int numInstances, int numKclusters) {
        super(numInstances,numKclusters);
    }
    
    public void initialInstances(Instances data, int rep){
        Random random = new Random(rep);
        double[] sumRanks = new double[data.numInstances()];
        adjListDocs = new Neighbor[data.numInstances()];
        docsRank = new double[data.numInstances()][this.getK()];
        docsRankTemp = new double[data.numInstances()][this.getK()];
        termsRank = new double[data.numAttributes()-1][this.getK()];
        adjListTerms = new Neighbor[data.numAttributes()-1];
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        
        //inicializa os clusters
        for (int k = 0; k < this.getK(); k++) {
            HashSet<Integer> cluster = new HashSet();
            clustering.add(cluster);
        }
        this.setClustering(clustering);
        
        
        //inicializa todos vuzinhos dos termos, ou seja, seus links com documentos
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
        //soma do rank condicional de todos k tem que ser = 1
        for (int inst = 0; inst < data.numInstances(); inst++) {
            //k = numero de clusters atual
            for (int k = 0; k < this.getK(); k++) {
                docsRank[inst][k] = docsRank[inst][k]/sumRanks[inst];
                //copia todos valores para o temporário
                docsRankTemp[inst][k] = docsRank[inst][k];
            }
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
        double[] sumRanks = new double[this.getK()];
        adjListDocs = new Neighbor[data.numInstances()];
        docsRank = new double[data.numInstances()][this.getK()];
        docsRankTemp = new double[data.numInstances()][this.getK()];
        termsRank = new double[data.numAttributes()-1][this.getK()];
        adjListTerms = new Neighbor[data.numAttributes()-1];
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        
        //inicializa os clusters
        for (int k = 0; k < this.getK(); k++) {
            HashSet<Integer> cluster = new HashSet();
            clustering.add(cluster);
        }
        this.setClustering(clustering);
        
        
        //inicializa todos vizinhos dos termos, ou seja, seus links com documentos
        for (int term = 0; term < data.numAttributes()-1; term++) {
            Neighbor docsNeighbors = new Neighbor();
            adjListTerms[term] = docsNeighbors;
        }
        
        //inicializa todos vizinhos dos documentos, ou seja, seus links com termos
        for (int inst = 0; inst < data.numInstances(); inst++) {
            Neighbor termsNeighbors = new Neighbor();
            adjListDocs[inst] = termsNeighbors;
        }

        //iniciaiza valores do vetor de rank condicional para todos docs
        for (int k = 0; k < this.getK(); k++) {
            //inicializa vetor de somas
            sumRanks[k] = 0;
            for (int inst = 0; inst < data.numInstances(); inst++) {
                docsRank[inst][k] = random.nextDouble();
                //soma o rank total (inicializado) daquele doc
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
    
    public void initialInstances3(Instances data, int rep){
        Random random = new Random(rep);
        double[] sumRanks = new double[this.getK()];
        adjListDocs = new Neighbor[data.numInstances()];
        docsRank = new double[data.numInstances()][this.getK()];
        docsRankTemp = new double[data.numInstances()][this.getK()];
        termsRank = new double[data.numAttributes()-1][this.getK()];
        adjListTerms = new Neighbor[data.numAttributes()-1];
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        Neighbor[] adjacencyListPTermDoc = new Neighbor[data.numAttributes()-1];
        Neighbor[] adjacencyListPDocTerm = new Neighbor[data.numInstances()];
        

        //inicializa os clusters
        for (int k = 0; k < this.getK(); k++) {
            HashSet<Integer> cluster = new HashSet();
            clustering.add(cluster);
        }
        this.setClustering(clustering);
        
        //inicializa todos vuzinhos dos termos, ou seja, seus links com documentos
        for (int term = 0; term < data.numAttributes()-1; term++) {
            Neighbor docsNeighbors = new Neighbor();
            adjListTerms[term] = docsNeighbors;
            adjacencyListPTermDoc[term] = new Neighbor();
        }
        
        //inicializa todos vizinhos dos documentos, ou seja, seus links com termos
        for (int inst = 0; inst < data.numInstances(); inst++) {
            Neighbor termsNeighbors = new Neighbor();
            adjListDocs[inst] = termsNeighbors;
            adjacencyListPDocTerm[inst] = new Neighbor();
        }

        //iniciaiza valores do vetor de rank condicional para todos docs
        for (int k = 0; k < this.getK(); k++) {
            //inicializa vetor de somas
            sumRanks[k] = 0;
            for (int inst = 0; inst < data.numInstances(); inst++) {
                docsRank[inst][k] = random.nextDouble();
                //soma o rank total (inicializado) daquele doc
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
        
        double[] dDoc = new double[data.numInstances()]; // degree of documents
        for(int inst=0;inst<(data.numInstances());inst++){
            double grau = 0;
            ArrayList<IndexValue> neighbors = adjListDocs[inst].getNeighbors();
            for(int term=0;term<neighbors.size();term++){
                grau += neighbors.get(term).value;
            }
            dDoc[inst] = grau;
        }
        
        double[] dTerm = new double[data.numAttributes()-1]; // degree of terms
        for(int term=0;term<data.numAttributes()-1;term++){
            double grau = 0;
            ArrayList<IndexValue> neighbors = adjListTerms[term].getNeighbors();
            for(int inst=0;inst<neighbors.size();inst++){
                grau += neighbors.get(inst).value;
            }
            dTerm[term] = grau;
        }
        
        // Normalizing relation weights
        for(int inst=0;inst<(data.numInstances());inst++){
            ArrayList<IndexValue> neighbors = adjListDocs[inst].getNeighbors();
            for(int term=0;term<neighbors.size();term++){
                if(dDoc[inst] != 0){
                    IndexValue indVal = new IndexValue();
                    indVal.index = neighbors.get(term).index;
                    indVal.value = (double)neighbors.get(term).value / (double)dDoc[inst];
                    adjacencyListPDocTerm[inst].AddNeighbor(indVal);
                }
            }
        }
        
        adjListDocs = adjacencyListPDocTerm;
        
        for(int term=0;term<data.numAttributes()-1;term++){
            ArrayList<IndexValue> neighbors = adjListTerms[term].getNeighbors();
            for(int inst=0;inst<neighbors.size(); inst++){
                if((double)dTerm[term] != 0){
                    IndexValue indVal = new IndexValue();
                    indVal.index = neighbors.get(inst).index;
                    indVal.value = (double)neighbors.get(inst).value / (double)dTerm[term];
                    adjacencyListPTermDoc[term].AddNeighbor(indVal);
                }
            }
        }
        
        adjListTerms = adjacencyListPTermDoc;
    
    }
    
    public void rankScorePropagation(){
        double result = 0;
        double dif = 1;
        double linkValue = 0;
        double[] sumRanksTerms = new double [termsRank.length];//guarda somatória do rank do termo para todos clusters
        double[] sumRanksDocs = new double [docsRank.length];//guarda somatória do rank do documento para todos ter
        int linkInd = 0;
        int iter = this.getIteracoes();
        
        while (iter > 0 && dif > this.getMinimumDif()){        
            //propaga os ranks scores para os termos
            //para todos os termos
            for (int term = 0; term < adjListTerms.length; term++) {
                //inicializa/zera o vetor de somatorio de ranks de termos
                sumRanksTerms[term] = 0;
                //para todos ranks condicionais
                for (int k = 0; k < this.getK(); k++) {
                    result = 0;
                    //para todos os documentos linkados àquele termo
                    for (int link = 0; link < adjListTerms[term].getNeighbors().size(); link++) {
                        //salva o valor da ligação entre o termo e o documento
                        linkValue = adjListTerms[term].getNeighbor(link).value;
                        //salva o indice do documento sendo observado (linkado)
                        linkInd = adjListTerms[term].getNeighbor(link).index;
                        //o valor do rank condicional para aquele termo e para aquele cluster(não normalizado)
                        result += linkValue * docsRank[linkInd][k];
                    }
                    termsRank[term][k] = result;
                    //soma o valor daquele rank no vetor para aquele cluster
                    sumRanksTerms[term] += result;
                }
            }

            //normaliza os valores de ranks condicionais dos termos
            for (int term = 0; term < termsRank.length; term++) {
                for (int k = 0; k < this.getK(); k++) {
                    //normaliza conforme a formula
                    termsRank[term][k] = termsRank[term][k]/sumRanksTerms[term];
                }
            }

            //propaga os ranks scores para os documentos
            //para todos os documentos
            for (int inst = 0; inst < adjListDocs.length; inst++) {
                //inicializa/zera o vetor de somatorio de ranks de documentos 
                sumRanksDocs[inst] = 0;
                //para todos ranks condicionais
                for (int k = 0; k < this.getK(); k++) {
                    result = 0;
                    //salva valor do rank condicional dos documentos para os clusters em um temp
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
                    sumRanksDocs[inst] += result;
                }
            }

            //normaliza os valores de ranks condicionais dos documentos
            for (int inst = 0; inst < docsRank.length; inst++) {
                for (int k = 0; k < this.getK(); k++) {
                    //normaliza conforme a formula
                    docsRank[inst][k] = docsRank[inst][k]/sumRanksDocs[inst];
                }
            }
            
            //atualiza valor dos rankings condicionais dos documentos
            for (int inst = 0; inst < docsRank.length; inst++) {
                for (int k = 0; k < this.getK(); k++) {
                    docsRank[inst][k] = docsRank[inst][k] * alfa + ((1-alfa) * docsRankTemp[inst][k]);
                }
            }
            
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
            
            iter --;
        }
    }
    
    public void rankScorePropagation2(){
        double result = 0;
        double dif = 1;
        double linkValue = 0;
        double[] sum = new double[this.getK()];
        int linkInd = 0;
        int iter = this.getIteracoes();
        
        while (iter > 0 && dif > this.getMinimumDif()){        
            //para todos ranks condicionais
            for (int k = 0; k < this.getK(); k++) {            
                //inicializa/zera o vetor de somatorio de ranks
                sum[k] = 0;                
                //propaga os ranks scores para os termos
                //para todos os termos
                for (int term = 0; term < adjListTerms.length; term++) {
                    result = 0;
                    //para todos os documentos linkados àquele termo
                    for (int link = 0; link < adjListTerms[term].getNeighbors().size(); link++) {
                        //salva o valor da ligação entre o termo e o documento
                        linkValue = adjListTerms[term].getNeighbor(link).value;
                        //salva o indice do documento sendo observado (linkado)
                        linkInd = adjListTerms[term].getNeighbor(link).index;
                        //o valor do rank condicional para aquele termo e para aquele cluster(não normalizado)
                        result += linkValue * docsRank[linkInd][k];
                    }
                    termsRank[term][k] = result;
                    //soma o valor daquele rank no vetor para aquele cluster
                    sum[k] += result;
                }
            }

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
                    //salva valor do rank condicional dos documentos para os clusters em um temp
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
            
            iter --;
        }
    }
    
    public void clusteringAllocation(){
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
    }
    
    @Override
    public boolean buildCluster(Instances data, int rep) {
        this.setInstances(mappingInstances(data));
        initialInstances3(data, rep);
        rankScorePropagation2();
        clusteringAllocation();
        
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

    public double getAlfa() {
        return alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    
    
}
