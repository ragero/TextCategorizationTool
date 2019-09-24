package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import weka.core.Instances;

public class LabelPropagation_DocDoc extends Clusterer{

    ArrayList<Neighbor> adjList;
    private boolean weighted;
    private boolean unitary;
    private boolean synchronous;
    private boolean asynchronous;
    private int[] labels;
    private int[] labelsUP; //updated
    
    public LabelPropagation_DocDoc(int numInstances, ArrayList<Neighbor> adjlist) {
        super(numInstances);
        this.adjList = adjlist;
    }

    public ArrayList<Neighbor> getAdjList() {
        return adjList;
    }

    public void setAdjList(ArrayList<Neighbor> adjList) {
        this.adjList = adjList;
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

    public boolean isSynchronous() {
        return synchronous;
    }

    public void setSynchronous(boolean synchronous) {
        this.synchronous = synchronous;
    }

    public boolean isAsynchronous() {
        return asynchronous;
    }

    public void setAsynchronous(boolean asynchronous) {
        this.asynchronous = asynchronous;
    }

    public int[] getLabels() {
        return labels;
    }

    public void setLabels(int[] labels) {
        this.labels = labels;
    }

    public int[] getLabelsUP() {
        return labelsUP;
    }

    public void setLabelsUP(int[] labelsUP) {
        this.labelsUP = labelsUP;
    }
    
    
    public void initialInstances(){
        labels = new int[adjList.size()];
        for (int label = 0; label < labels.length; label++) {
            labels[label] = label;
        }
    }
    
    //propagação de rótulo de forma síncrona e com peso nas ligações
    public void weightedSyn(int seed){
        labelsUP = new int[labels.length];
        Neighbor neighborhood = new Neighbor();
        ArrayList<Integer> indices = new ArrayList<>();
        Random random = new Random(seed);
        Set<Integer> indexValues;
        IndexValue indValue;
        boolean equilibriumC1 = false; //Após uma iteração, nada mudar
        boolean equilibriumC2 = false; //Para todos nós, que estejam rotulados com 
        //a maioria dos rótulos de seus vizinhos
        int iteracoes = super.getIteracoes();
        int instance; 
        double value;
        
        while(iteracoes > 0 && equilibriumC1 == false && equilibriumC2 == false){
            equilibriumC1 = true;
//            equilibriumC2 = true;
            //escreve no ArrayList os indices(intances) possíveis a serem escolhidos
            for (int ind = 0; ind < labels.length; ind++) {
                indices.add(ind);
            }
            
            while(indices.size() > 0){
                //hashmap anota o label e valor(ocorrência) do mesmo
                HashMap<Integer,Double> nextLabel = new HashMap<>();
                //escolhe randomicamente o indice (documento) a receber um possível novo rotulo
                int pos = random.nextInt(indices.size());
                instance = indices.get(pos);
                //escolhe um inteiro entre 0 e o tamanho do array list
                neighborhood = adjList.get(instance);
                //recebe os vizinho do documento
                for (int neighbor = 0; neighbor < neighborhood.getNeighbors().size(); neighbor++) {
                    //para todos seus vizinhos adiciona no hashmap seus rótulos, e soma suas quantidades
                    indValue = neighborhood.getNeighbor(neighbor);
                    //label recebe a instance que é seu vizinho e o valor dessa ligação
                    value = indValue.getValue();
                    if(nextLabel.get(labels[indValue.getIndex()]) != null){
                        //value recebe o valor da ligação e a soma com o que existia já no HashMap
                        value += nextLabel.get(labels[indValue.getIndex()]);
                    }
                    //acesso o meu vetor labels na posição da minha instancia vizinha label.index 
                    //e com o valor de seu label, encontro seu valor no HashMap
                    nextLabel.put(labels[indValue.getIndex()], value);
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
                if(nextLabel.get(labels[instance]) != null){
                    if(argmax.getValue() == nextLabel.get(labels[instance])){
                        argmax.setIndex(labels[instance]);
                    }
                }
                //adiciona o novo label da instancia no vetor updated
                labelsUP[instance] = argmax.getIndex();
                //remove o indice dentre os possíveis a serem escolhidos randomicamente
                //logo, garante que não escolha novamente o mesmo indice
                indices.remove(pos);
            }
            //atualiza as instancias de forma sincrona após fazer o cálculo para toda a rede
            for (int instances = 0; instances < labels.length; instances++) {
                //se houve alguma mudança entre as rotulações de uma iteração para outra
                //então não satisfez a primeira condição de equilibrio
                if(labels[instances] != labelsUP[instances])
                    equilibriumC1=false;
                labels[instances] = labelsUP[instances];
            }
            iteracoes --;
        }
        
    }
    
    //propagação de rótulo de forma assíncrona e  com peso nas ligações
    public void weightedAssyn(int seed){
        labelsUP = new int[labels.length];
        Neighbor neighborhood = new Neighbor();
        ArrayList<Integer> indices = new ArrayList<>();
        Random random = new Random(seed);
        Set<Integer> indexValues;
        IndexValue indValue;
        boolean equilibriumC1 = false; //Após uma iteração, nada mudar
        boolean equilibriumC2 = false; //Para todos nós, que estejam rotulados com 
        //a maioria dos rótulos de seus vizinhos
        int iteracoes = super.getIteracoes();
        int instance; 
        double value;
        
        while(iteracoes > 0 && equilibriumC1 == false && equilibriumC2 == false){
            equilibriumC1 = true;
//            equilibriumC2 = true;
            //escreve no ArrayList os indices(intances) possíveis a serem escolhidos
            for (int ind = 0; ind < labels.length; ind++) {
                indices.add(ind);
            }
            
            while(indices.size() > 0){
                //hashmap anota o label e valor(ocorrência) do mesmo
                HashMap<Integer,Double> nextLabel = new HashMap<>();
                //escolhe randomicamente o indice (documento) a receber um possível novo rotulo
                int pos = random.nextInt(indices.size());
                instance = indices.get(pos);
                //escolhe um inteiro entre 0 e o tamanho do array list
                neighborhood = adjList.get(instance);
                //recebe os vizinho do documento
                for (int neighbor = 0; neighbor < neighborhood.getNeighbors().size(); neighbor++) {
                    //para todos seus vizinhos adiciona no hashmap seus rótulos, e soma suas quantidades
                    indValue = neighborhood.getNeighbor(neighbor);
                    //label recebe a instance que é seu vizinho e o valor dessa ligação
                    value = indValue.getValue();
                    if(nextLabel.get(labels[indValue.getIndex()]) != null){
                        //value recebe o valor da ligação e a soma com o que existia já no HashMap
                        value += nextLabel.get(labels[indValue.getIndex()]);
                    }
                    //acesso o meu vetor labels na posição da minha instancia vizinha label.index 
                    //e com o valor de seu label, encontro seu valor no HashMap
                    nextLabel.put(labels[indValue.getIndex()], value);
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
                if(nextLabel.get(labels[instance]) != null){
                    if(argmax.getValue() == nextLabel.get(labels[instance])){
                        argmax.setIndex(labels[instance]);
                    }
                }
                //guarda o antigo label para comparação de mudanças
                labelsUP[instance] = labels[instance];
                //atualiza o label do documento
                labels[instance] = argmax.getIndex();
                if(labels[instance] != labelsUP[instance])
                    equilibriumC1=false;
                //remove o indice dentre os possíveis a serem escolhidos randomicamente
                //logo, garante que não escolha novamente o mesmo indice
                indices.remove(pos);
            }
            iteracoes --;
        }
    }
    
    //propagação de rótulo de forma síncrona e sem peso nas ligações
    public void unweightedSyn(int seed){
        labelsUP = new int[labels.length];
        Neighbor neighborhood = new Neighbor();
        ArrayList<Integer> indices = new ArrayList<>();
        Random random = new Random(seed);
        Set<Integer> indexValues;
        IndexValue indValue;
        boolean equilibriumC1 = false; //Após uma iteração, nada mudar
        boolean equilibriumC2 = false; //Para todos nós, que estejam rotulados com 
        //a maioria dos rótulos de seus vizinhos
        int iteracoes = super.getIteracoes();
        int instance; 
        int value;
        
        while(iteracoes > 0 && equilibriumC1 == false && equilibriumC2 == false){
            equilibriumC1=true;
//            equilibriumC2=true;
            //escreve no ArrayList os indices possíveis a serem escolhidos
            for (int ind = 0; ind < labels.length; ind++) {
                indices.add(ind);
            }
            
            while(indices.size() > 0){
                //hashmap anota o label e valor(ocorrência) do mesmo
                HashMap<Integer,Integer> nextLabel = new HashMap<>();
                //escolhe randomicamente o indice (documento) a receber um possível novo rotulo
                int pos = random.nextInt(indices.size());
                instance = indices.get(pos);
                //escolhe um inteiro entre 0 e o tamanho do array list
                neighborhood = adjList.get(instance);
                //recebe os vizinho do documento
                for (int neighbor = 0; neighbor < neighborhood.getNeighbors().size(); neighbor++) {
                    value = 0;
                    //para todos seus vizinhos adiciona no hashmap seus rótulos, e soma suas quantidades
                    indValue = neighborhood.getNeighbor(neighbor);
                    //label recebe a instance que é seu vizinho e o valor dessa ligação
                    if(nextLabel.get(labels[indValue.getIndex()]) != null)
                        value = nextLabel.get(labels[indValue.getIndex()]);
                    //acesso o meu vetor labels na posição da minha instancia vizinha label.index 
                    //e com o valor de seu label, encontro seu valor no HashMap
                    value ++;
                    nextLabel.put(labels[indValue.getIndex()], value);
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
                if(nextLabel.get(labels[instance]) != null){
                    if(argmax.getValue() == nextLabel.get(labels[instance])){
                        argmax.setIndex(labels[instance]);
                    }
                }
                //adiciona o novo label da instancia no vetor updated
                labelsUP[instance] = argmax.getIndex();
                //remove o indice dentre os possíveis a serem escolhidos randomicamente
                //logo, garante que não escolha novamente o mesmo indice
                indices.remove(pos);
            }
            //atualiza as instancias de forma sincrona após fazer o cálculo para toda a rede
            for (int instances = 0; instances < labels.length; instances++) {
                //se houve alguma mudança entre as rotulações de uma iteração para outra
                //então não satisfez a primeira condição de equilibrio
                if(labels[instances] != labelsUP[instances])
                    equilibriumC1=false;
                labels[instances] = labelsUP[instances];
            }
            iteracoes--;
        }
        
    }
    
    //propagação de rótulo de forma assíncrona e sem peso nas ligações
    public void unweightedAssyn(int seed){
        labelsUP = new int[labels.length];
        Neighbor neighborhood = new Neighbor();
        ArrayList<Integer> indices = new ArrayList<>();
        Random random = new Random(seed);
        Set<Integer> indexValues;
        IndexValue indValue;
        boolean equilibriumC1 = false; //Após uma iteração, nada mudar
        boolean equilibriumC2 = false; //Para todos nós, que estejam rotulados com 
        //a maioria dos rótulos de seus vizinhos
        int iteracoes = super.getIteracoes();
        int instance; 
        int value;
        
        while(iteracoes > 0 && equilibriumC1 == false && equilibriumC2 == false){
            equilibriumC1=true;
//            equilibriumC2=true;
            //escreve no ArrayList os indices possíveis a serem escolhidos
            for (int ind = 0; ind < labels.length; ind++) {
                indices.add(ind);
            }
            
            while(indices.size() > 0){
                //hashmap anota o label e valor(ocorrência) do mesmo
                HashMap<Integer,Integer> nextLabel = new HashMap<>();
                //escolhe randomicamente o indice (documento) a receber um possível novo rotulo
                int pos = random.nextInt(indices.size());
                instance = indices.get(pos);
                //escolhe um inteiro entre 0 e o tamanho do array list
                neighborhood = adjList.get(instance);
                //recebe os vizinho do documento
                for (int neighbor = 0; neighbor < neighborhood.getNeighbors().size(); neighbor++) {
                    value = 0;
                    //para todos seus vizinhos adiciona no hashmap seus rótulos, e soma suas quantidades
                    indValue = neighborhood.getNeighbor(neighbor);
                    //label recebe a instance que é seu vizinho e o valor dessa ligação
                    if(nextLabel.get(labels[indValue.getIndex()]) != null)
                        value = nextLabel.get(labels[indValue.getIndex()]);
                    //acesso o meu vetor labels na posição da minha instancia vizinha label.index 
                    //e com o valor de seu label, encontro seu valor no HashMap
                    value ++;
                    nextLabel.put(labels[indValue.getIndex()], value);
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
                if(nextLabel.get(labels[instance]) != null){
                    if(argmax.getValue() == nextLabel.get(labels[instance])){
                        argmax.setIndex(labels[instance]);
                    }
                }
                //guarda o antigo label para comparação de mudanças
                labelsUP[instance] = labels[instance];
                //atualiza o label do documento
                labels[instance] = argmax.getIndex();
                if(labels[instance] != labelsUP[instance])
                    equilibriumC1=false;
                //remove o indice dentre os possíveis a serem escolhidos randomicamente
                //logo, garante que não escolha novamente o mesmo indice
                indices.remove(pos);
            }
            iteracoes--;
        }
        
    }
    
    @Override
    public boolean buildCluster(Instances data, int rep) {
        boolean ok =true;
        int numberAcceptableClusters = (int)Math.sqrt(data.numInstances());
        this.setInstances(mappingInstances(data));
        initialInstances();
        if(this.asynchronous){
            if(this.isWeighted())
                weightedAssyn(rep);
            else
                unweightedAssyn(rep);
        }else{
            if(this.isWeighted())
                weightedSyn(rep);
            else
                unweightedSyn(rep);
        }
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
        
        for (int instances = 0; instances < labels.length; instances++) {
            //se já existe um cluster para aquele label
            //eu apenas adiciono a instancia como pertencente àquele cluster
            if (communities.get(labels[instances]) != null){
                HashSet<Integer> group = communities.get(labels[instances]);
                group.add(instances);
            }else{
                //instancio um cluster que será mapeado para aquele label
                //ou seja, crio um cluster para cada label
                HashSet<Integer> group = new HashSet();
                group.add(instances);
                communities.put(labels[instances], group);
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
