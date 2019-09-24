
package TCTAlgorithms.Unsupervised;

import TCTParameters.UnsupervisedLearning.Parameters_K_Means;


public class Base {

    private String dirEntrada;
    private String dirSaida;
    private Parameters_K_Means kmeans;
    private int numReps;
    private int numFolds;
    private boolean K_Means;
    
    //se k-means estiver selecionado eu crio ele aqui?

    public Base() {
        this.dirEntrada = "";
        this.dirSaida = "";
        setKmeans(new Parameters_K_Means());
        setNumFolds(1);
        setNumReps(10);
    }

    public String getDirEntrada() {
        return dirEntrada;
    }

    public void setDirEntrada(String dirEntrada) {
        this.dirEntrada = dirEntrada;
    }

    public String getDirSaida() {
        return dirSaida;
    }

    public void setDirSaida(String dirSaida) {
        this.dirSaida = dirSaida;
    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }

    public int getNumFolds() {
        return numFolds;
    }

    public void setNumFolds(int numFolds) {
        this.numFolds = numFolds;
    }

    public boolean isK_Means() {
        return K_Means;
    }

    public void setK_Means(boolean K_Means) {
        this.K_Means = K_Means;
    }

    public Parameters_K_Means getKmeans() {
        return kmeans;
    }

    public void setKmeans(Parameters_K_Means kmeans) {
        this.kmeans = kmeans;
    }
    
    
    
    
    
}
