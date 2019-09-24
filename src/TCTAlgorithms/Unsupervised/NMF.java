package TCTAlgorithms.Unsupervised;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import weka.core.Instances;


public class NMF extends Prototype_Based{

    double[][] w;//term-topic MxK
    double[][] h;//doc-topic NxK
    double minimumError;
    boolean lda;//produz um arff(lda)
    String ldaDir;//diretorio para o lda, pode passar a mesma pasta de saida para as clustering ou n
    
    public NMF(int numInstances, int numKclusters) {
        super(numInstances, numKclusters);
    }
    
    //V^t(M[Term]xN[Doc]) * H(N[Doc]xK[Clusters])
    //seguindo NMF do Mining Text Data e Lee(artigo) e suas representações de documento
    public double[][] matrixMultiplication(double[][]matrixH, Instances data){
        int aRows =matrixH.length;//N docs
        int aColumns=matrixH[0].length;//K clusters
        
        if(aRows != data.numInstances())
            return null;
        
        double [][] matrixResult = new double[data.numAttributes()-1][aColumns];
        for(int line=0;line<data.numAttributes()-1;line++){
            for(int column=0;column<aColumns;column++){
                matrixResult[line][column] = 0;
            }
        }
        
        for(int line=0;line<data.numAttributes()-1;line++){
            for(int column=0;column<aColumns;column++){
                //n são os termos
                for(int n=0;n<data.numInstances();n++){
                    matrixResult[line][column] += matrixH[n][column] * data.instance(n).value(line);                    
                }
            }
        }
        return matrixResult;
    }
    
    //V(NxM) * W(MxK)
    //seguindo NMF do Mining Text Data e Lee(artigo) e suas representações de documento
    public double[][] matrixMultiplication(Instances data, double[][]matrixW){
        int bRows =matrixW.length;//m attributes
        int bColumns=matrixW[0].length;//k clusters
        
        if(data.numAttributes()-1!=bRows)
            return null;
        
        //V(NxM) onde M==numTerms e N==numDocs, logo a matriz final fica
        //numDocs x K(bColumn)
        double [][] matrixResult = new double[data.numInstances()][bColumns];
        for(int line=0;line<data.numInstances();line++){
            for(int column=0;column<bColumns;column++){
                matrixResult[line][column] = 0;
            }
        }
        
        // ~~~ignorar~~~Imagine que o arff é o inverso, ficando M(term)xN(doc)
        //basta apenas mudar a forma de acessa-lo, o importante é manter a visão
        //de termos e documentos na hora de acessar as celulas(valores)
        for(int line=0;line<data.numInstances();line++){
            for(int column=0;column<bColumns;column++){
                //m são os atributos, coluna de V
                for(int m=0;m<data.numAttributes()-1;m++)
                    matrixResult[line][column] += data.instance(line).value(m) * matrixW[m][column];
            }
        }
        return matrixResult;
    }
    
    public double[][] matrixMultiplication(double[][]matrixA, double[][]matrixB){
        
        int aRows =matrixA.length;
        int aColumns=matrixA[0].length;
        int bRows =matrixB.length;
        int bColumns=matrixB[0].length;

        if(aColumns!=bRows)
            return null;
        
        double [][] matrixResult = new double[aRows][bColumns];
        for(int line=0;line<aRows;line++){
            for(int column=0;column<bColumns;column++){
                matrixResult[line][column] = 0;
            }
        }
        
        for(int line=0;line<aRows;line++){
            for(int column=0;column<bColumns;column++){
                for(int c=0;c<aColumns;c++)
                matrixResult[line][column] += matrixA[line][c] * matrixB[c][column];
            }
        }
        
        return matrixResult;
    }
    
    public double[][] transposedMultiplication(double[][]matrix){
        double[][] t;
        t=transpose(matrix);
        return matrixMultiplication(t,matrix);
    }
    
    public double[][] transpose(double[][]matrix){
        double[][] transposed = new double[matrix[0].length][matrix.length];
        
        for(int line=0; line<matrix.length;line++){
            for (int column = 0; column < matrix[0].length; column++) {
                transposed[column][line]=matrix[line][column];
            }
        }
        
        return transposed;
    }

    public void initialInstances(Instances data, int seedrep){
        
        w = new double[data.numAttributes()-1][this.getK()];
        h = new double[data.numInstances()][this.getK()];
        
        //instancia r com uma semente
        Random r = new Random(seedrep);
        
        //distribui valores entre 0,1 para a matriz doc-topic N x K
        for (int line = 0; line < w.length; line++) {
            for (int column = 0; column < w[0].length; column++){
                w[line][column] = r.nextDouble();
            }
        }
        
        //distruibui valores entre 0,1 para a matriz term-topic(transposta)K x M
        for (int line = 0; line < h.length; line++) {
            for (int column = 0; column < h[0].length; column++){
                h[line][column] = r.nextDouble();
            }
        }
        
        //instancia as centroids e as adiciona ao array, e adiciona clusters ao clustering
        ArrayList<HashMap<Integer,Double>> centroids = new ArrayList<>();
        ArrayList<HashSet<Integer>> clusters = new ArrayList<>();
        for (int cluster = 0; cluster < this.getK(); cluster++) {
            HashMap<Integer,Double> centroid = new HashMap();
            HashSet<Integer> group = new HashSet<>();
            centroids.add(centroid);
            clusters.add(group);
        }
        super.setCentroides(centroids);
        super.setClustering(clusters);
    }
    
    public double computingError(Instances data, double[][] matrixHW){
        double value;
        double loss=0;
        
        for (int doc = 0;doc < matrixHW.length; doc++) {
            for (int term = 0; term < matrixHW[0].length; term++) {
                //||V-HW|| subitraio o valor[term][doc] do original pelo fatorado
                value = data.instance(doc).value(term) - matrixHW[doc][term];
                loss+=value*value;
            }
        }
        return loss/=data.numInstances()*2;
    }
    
    //Dita o valor das centroids e o clustering(qual grupo cada doc pertence)
    public void clustering(double[][]matrixW, double[][]matrixH){
        double maior;
        int cluster=0;
        for (int topic = 0; topic < matrixW[0].length; topic++) {
            //para cada cluster, k, pega seus valores(atributos) na matriz
            for (int term = 0; term < matrixW.length; term++) {
                this.getCentroides().get(topic).put(term,matrixW[term][topic]);
            }         
        }
        
        //define qual cluster o doc pertence com base nos seus valores de H[NxK]
        //onde seu maior valor do doc-topico define o topico daquele documento
        for (int doc = 0; doc < matrixH.length; doc++) {
            maior=0;
            for (int topic = 0; topic < matrixH[0].length; topic++) {
                if(matrixH[doc][topic]>maior){
                    cluster=topic;
                    maior=matrixH[doc][topic];
                }
            }
            //adiciona o documento(column da matrix) no cluster de maior peso
            this.getClustering().get(cluster).add(doc);
        }
    }
    
    @Override
    public boolean buildCluster(Instances data, int rep) {
        boolean ok = true;
        double numerador;
        double denominador;
        double dif = 1;
        double newValue;
        int iteration=super.getIteracoes();
        this.setInstances(mappingInstances(data));
        initialInstances(data, rep);
        double [][]hw = matrixMultiplication(h,transpose(w));
        double error = computingError(data,hw);
        //
        while(iteration > 0 && error > minimumError && dif > this.getMinimumDif()){
            //atualiza H e fixa W
            double[][] termTopic2 = transposedMultiplication(w);//gera uma matriz KxK (W^t * W)
            dif=0;
            for (int doc = 0; doc < data.numInstances(); doc++) {
                for (int cluster = 0; cluster < this.getK(); cluster++) {
                    numerador = 0; denominador = 0;
                    for (int term = 0; term < data.numAttributes()-1; term++) {
                        //V[NxM] * W[MxK]
                        numerador += data.instance(doc).value(term) * w[term][cluster];
                    }
                    for (int cluster2 = 0; cluster2 < this.getK(); cluster2++) {
                        //H[NxK] * (W^t * W)KxK 
                        denominador += h[doc][cluster2] * termTopic2[cluster2][cluster];
                    }
                    if(denominador!=0){
                        numerador = numerador * h[doc][cluster];//Multiplica numerador por valor antigo
                        newValue = numerador/denominador;
                        dif += Math.abs(newValue-h[doc][cluster]);
                        h[doc][cluster] = newValue;
                    }else{
                        h[doc][cluster]=0;
                    }
                }
            }
            dif=dif/(data.numAttributes()-1);
            if(dif < this.getMinimumDif())
                break;
            
            //atualiza W e fixa H
            double[][] docTopic2 = transposedMultiplication(h);//gera uma matriz KxK (H^t * H)
            dif = 0;
            for (int term = 0; term < data.numAttributes()-1; term++) {
                for (int cluster = 0; cluster < this.getK(); cluster++) {
                    numerador = 0; denominador = 0;
                    for (int doc = 0; doc < data.numInstances(); doc++) {
                        //V^t[MxN] * H[N*K] trata o doc-topic como se estivesse transposto
                        numerador += data.instance(doc).value(term) * h[doc][cluster];
                    }
                    for (int cluster2 = 0; cluster2 < this.getK();cluster2++){
                        //W[MxK] * (H^t * H)[KxK]
                        denominador += w[term][cluster2] * docTopic2[cluster2][cluster];
                    }
                    if(denominador!=0){
                        numerador = numerador*w[term][cluster];
                        newValue = numerador/denominador;
                        dif += Math.abs(newValue-w[term][cluster]);
                        w[term][cluster] = newValue;
                    }else{
                        w[term][cluster]=0;
                    }
                }
            }
            dif = dif/(data.numAttributes()-1);
            if(dif < this.getMinimumDif())
                break;
            hw = matrixMultiplication(h,transpose(w));
            error = computingError(data,hw);
            iteration--;
        }
        if(lda==true){
            LDAwriting.writingLDA(h, data, ldaDir);
        }
        clustering(w,h);
        return ok;
    }
    
    public double[][] getW() {
        return w;
    }

    public void setW(double[][] w) {
        this.w = w;
    }

    public double[][] getH() {
        return h;
    }

    public void setH(double[][] h) {
        this.h = h;
    }

    public boolean isLda() {
        return lda;
    }

    public void setLda(boolean lda) {
        this.lda = lda;
    }

    public String getLdaDir() {
        return ldaDir;
    }

    public void setLdaDir(String ldaDir) {
        this.ldaDir = ldaDir;
    }
    
    
}
