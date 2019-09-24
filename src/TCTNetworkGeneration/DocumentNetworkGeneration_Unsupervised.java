package TCTNetworkGeneration;

import TCTAlgorithms.Unsupervised.Clusterer;
import TCTAlgorithms.Unsupervised.IndexValue;
import TCTAlgorithms.Unsupervised.Neighbor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import weka.core.Instances;


public class DocumentNetworkGeneration_Unsupervised {
    
    public static ArrayList<Neighbor> generateKnnNetworkCosineAdjList(HashMap<Integer,Double>[] instances, int valueK){
        ArrayList<Neighbor> adjList = new ArrayList<>();
        PriorityQueue priority = new PriorityQueue();
        double value;
        IndexValue minimum = null;
        double similarity;
        for (int doc1 = 0; doc1 < instances.length; doc1++) {
            //um a um, compara a similaridade com todos documentos
            for (int doc2 = 0; doc2 < instances.length; doc2++) {
                if (doc1 == doc2) 
                    continue;
                similarity = Clusterer.cosseno(instances[doc1], instances[doc2]);
                if(priority.size() == valueK){
                    //usa a cabeça da lista(menor valor) como comparativo, ou seja, valor mínimo
                    if(similarity > minimum.getValue()){
                        IndexValue neighbor = new IndexValue();
                        neighbor.setIndex(doc2);
                        neighbor.setValue(similarity);
                        priority.add(neighbor);
                        //apos a inserção do novo valor, ele remove a cabeça(menor valor)
                        priority.poll();
                        //determina um novo valor minimo após a atualização de vizinhos
                        minimum = (IndexValue)priority.peek();
                    }
                }else{
                    IndexValue neighbor = new IndexValue();
                    neighbor.setIndex(doc2);
                    neighbor.setValue(similarity);
                    priority.add(neighbor);
                    //dá um valor mínimo sempre que adiciona um novo vizinho, desde que
                    //o número de vizinhos seja, ainda, menor que k(valueK)
                    minimum = (IndexValue)priority.peek();
                }
            }
            
            Neighbor neighbors = new Neighbor();
            for (int neighbor = 0; neighbor < valueK; neighbor++) {
                //remove os elementos da fila de prioridade, limpando-a, e pegando todos seus elementos
                //uma vez que priority queue só funciona com elementos da cabeça
                IndexValue indValue = (IndexValue)priority.poll();
                neighbors.AddNeighbor(indValue);
            }
            adjList.add(doc1, neighbors);
        }  
        return adjList;
    }
}
