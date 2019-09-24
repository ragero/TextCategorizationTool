package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import weka.core.Instances;


public class LabelPropagation_DocTerm extends Clusterer{
    
    Neighbor[] adjListTerms; //vizinhos dos termos
    Neighbor[] adjListDocs;//vizinhos dos documentos
    private boolean weighted;
    private boolean unitary;
    private int[] termsLabels;
    private int[] docsLabels;
    private int[] docsLabelsUP; //updated
    
    public LabelPropagation_DocTerm(int numInstances) {
        super(numInstances);
    }

    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public boolean isUnitary() {
        return unitary;
    }

    public void setUnitary(boolean unitary) {
        this.unitary = unitary;
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

    public int[] getTermsLabels() {
        return termsLabels;
    }

    public void setTermsLabels(int[] termsLabels) {
        this.termsLabels = termsLabels;
    }

    public int[] getDocsLabels() {
        return docsLabels;
    }

    public void setDocsLabels(int[] docsLabels) {
        this.docsLabels = docsLabels;
    }

    public int[] getDocsLabelsUP() {
        return docsLabelsUP;
    }

    public void setDocsLabelsUP(int[] docsLabelsUP) {
        this.docsLabelsUP = docsLabelsUP;
    }
    
    public void initialInstances(Instances data){
        docsLabels = new int[data.numInstances()];
        docsLabelsUP = new int[data.numInstances()];
        termsLabels = new int[data.numAttributes()-1];
        adjListTerms = new Neighbor[data.numAttributes()-1];
        adjListDocs = new Neighbor[data.numInstances()];
        
        for (int inst = 0; inst < data.numInstances(); inst++) {
            docsLabels[inst] = inst;
            docsLabelsUP[inst] = inst;
            Neighbor termsNeighbors = new Neighbor();
            adjListDocs[inst] = termsNeighbors;
        }
        
        for (int term = 0; term < data.numAttributes()-1; term++) {
            termsLabels[term] = 0;
            Neighbor docsNeighbors = new Neighbor();
            adjListTerms[term] = docsNeighbors;
        }
        
        if(this.isWeighted()){
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
        }else{
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
                        neighborDoc.setValue(1);
                        //adiciona o documento na sua vizinhança
                        adjListTerms[term].getNeighbors().add(neighborDoc);

                        //instancia o termo vizinho do documento
                        IndexValue neighborTerm = new IndexValue();
                        //guarda o termo que ocorre, logo, seu vizinho
                        neighborTerm.setIndex(term);
                        //adiciona o valor da ligação com o termo
                        neighborTerm.setValue(1);
                        //adiciona o termo na sua lista de adjacencia
                        adjListDocs[inst].getNeighbors().add(neighborTerm);
                    }
                }
            }
            
        }
    }
    
    //propagação de rótulo de forma síncrona e com peso nas ligações
    public void weightedSyn(int seed){
        Random random = new Random(seed);
        Neighbor neighborhood = new Neighbor();
        Set<Integer> indexValues; //navega no hashmap
        boolean equilibrium = false;
        double value;
        int iteration = super.getIteracoes();
        
        while(iteration > 0 && equilibrium == false){
            equilibrium =true;
            
            //para todo termo, atualiza seu label
            for (int terms = 0; terms < termsLabels.length; terms++) {
                //hashmap anota na posição label e valor(ocorrência) do mesmo
                HashMap<Integer,Double> nextLabel = new HashMap<>();
                //para cada termo percorre sua vizinhança de documentos
                neighborhood = adjListTerms[terms];
                for (int neighbor = 0; neighbor < neighborhood.getNeighbors().size(); neighbor++) {
                    //aponta para um documentos vizinho
                    IndexValue specifiedNeighbor = neighborhood.getNeighbor(neighbor);
                    //recebe o valor da ligação com esse documento específico
                    value = specifiedNeighbor.getValue();
                    //olha para a posição no HashMap do label do documento apontado, se tiver algum valor, soma.
                    if(nextLabel.get(docsLabels[specifiedNeighbor.getIndex()]) != null){
                        value += nextLabel.get(docsLabels[specifiedNeighbor.getIndex()]);
                    }
                    nextLabel.put(docsLabels[specifiedNeighbor.getIndex()], value);
                }
                //uso a classe index valor para anotar o rótulo/valor majoritário
                IndexValue argmax = new IndexValue();
                argmax.setValue(0);
                indexValues = nextLabel.keySet();
                //olha para os labels que apareceram entre seus vizinhos
                for (Integer label : indexValues){
                    if(nextLabel.get(label) >= argmax.getValue()){
                        if(nextLabel.get(label) > argmax.getValue()){
                            argmax.setValue(nextLabel.get(label));
                            argmax.setIndex(label);
                        }else{
                           double coin = random.nextDouble();
                           if(coin > 0.5){
                               argmax.setValue(nextLabel.get(label));
                               argmax.setIndex(label);
                           }
                        }
                    }
                }
                //após determinar o argmax ele compara com o valor do seu próprio label
                //em caso de igualdade, ele mantem seu label
                if(nextLabel.get(termsLabels[terms]) != null){
                    if(argmax.getValue() == nextLabel.get(termsLabels[terms])){
                        argmax.setIndex(termsLabels[terms]);
                    }
                }
                //atualiza o label do termo                
                termsLabels[terms] = argmax.getIndex();
            }
            
            //para todo documento, atualiza seu label
            for (int inst = 0; inst < docsLabels.length; inst++) {
                //hashmap anota na posição label e valor(ocorrência) do mesmo
                HashMap<Integer,Double> nextLabel = new HashMap<>();
                //para cada documento percorre sua vizinhança de termos
                neighborhood = adjListDocs[inst];
                for (int neighbor = 0; neighbor < neighborhood.getNeighbors().size(); neighbor++) {
                    //aponta para um termos vizinho
                    IndexValue specifiedNeighbor = neighborhood.getNeighbor(neighbor);
                    //recebe o valor da ligação com esse termo específico
                    value = specifiedNeighbor.getValue();
                    //olha para a posição no HashMap do label do termo apontado, se tiver algum valor, soma.
                    if(nextLabel.get(termsLabels[specifiedNeighbor.getIndex()]) != null){
                        value += nextLabel.get(termsLabels[specifiedNeighbor.getIndex()]);
                    }
                    nextLabel.put(termsLabels[specifiedNeighbor.getIndex()], value);
                }
                //uso a classe index valor para anotar o rótulo/valor majoritário
                IndexValue argmax = new IndexValue();
                argmax.setValue(0);
                indexValues = nextLabel.keySet();
                //olha para os labels que apareceram entre seus vizinhos
                for (Integer label : indexValues){
                    if(nextLabel.get(label) >= argmax.getValue()){
                        if(nextLabel.get(label) > argmax.getValue()){
                            argmax.setValue(nextLabel.get(label));
                            argmax.setIndex(label);
                        }else{
                           double coin = random.nextDouble();
                           if(coin > 0.5){
                               argmax.setValue(nextLabel.get(label));
                               argmax.setIndex(label);
                           }
                        }
                    }
                }
                //após determinar o argmax ele compara com o valor do seu próprio label
                //em caso de igualdade, ele mantem seu label
                if(nextLabel.get(docsLabels[inst]) != null){
                    if(argmax.getValue() == nextLabel.get(docsLabels[inst])){
                        argmax.setIndex(docsLabels[inst]);
                    }
                }
                if(docsLabels[inst] != argmax.getIndex())
                    equilibrium = false;
                //atualiza o label do termo                
                docsLabels[inst] = argmax.getIndex();
            }
            iteration --;
        }
    }
    
    //propagação de rótulo de forma assíncrona e  com peso nas ligações
    public void weightedAssyn(int seed){

    }
    
    //propagação de rótulo de forma síncrona e sem peso nas ligações
    public void unweightedSyn(int seed){
        
    }
    
    //propagação de rótulo de forma assíncrona e sem peso nas ligações
    public void unweightedAssyn(int seed){
        
    }
    
    @Override
    public boolean buildCluster(Instances data, int rep) {
        boolean ok = true;
        int numberAcceptableClusters = (int)Math.sqrt(data.numInstances());
        this.setInstances(mappingInstances(data));
        initialInstances(data);
        weightedSyn(rep);
        clustering();
        if(this.getClustering().size() > numberAcceptableClusters){
            ok = false;
        }
        return ok;
    }
    
    public void clustering(){
        //mapeio o label, e um respectivo cluster
        HashMap<Integer, HashSet<Integer>> communities = new HashMap();
        ArrayList<HashSet<Integer>> clustering = new ArrayList<>();
        Set<Integer> clusters;
        int label;
        
        for (int instances = 0; instances < docsLabels.length; instances++) {
            //se já existe um cluster para aquele label
            //eu apenas adiciono a instancia como pertencente àquele cluster
            if (communities.get(docsLabels[instances]) != null){
                HashSet<Integer> group = communities.get(docsLabels[instances]);
                group.add(instances);
            }else{
                //instancio um cluster que será mapeado para aquele label
                //ou seja, crio um cluster para cada label
                HashSet<Integer> group = new HashSet();
                group.add(instances);
                communities.put(docsLabels[instances], group);
            }
        }
        
        //para todo cluster criado, o adiciono ao meus clustering final
        clusters = communities.keySet();
        for (Integer cluster : clusters) {
            clustering.add(communities.get(cluster));
        }
        
        this.setClustering(clustering);
    }
}
