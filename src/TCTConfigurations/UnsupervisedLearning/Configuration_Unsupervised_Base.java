
package TCTConfigurations.UnsupervisedLearning;

import TCTConfigurations.ConfigurationBase;
import java.io.Serializable;


public class Configuration_Unsupervised_Base extends ConfigurationBase implements Serializable{

    private String dirEntrada;
    private String dirSaida;
    private int numThreads;
    private int numReps;

    public Configuration_Unsupervised_Base() {
        this.dirEntrada = "";
        this.dirSaida = "";
        setNumReps(10);
        setNumThreads(10);
    }
    
    public int getNumThreads() {
        return numThreads;
    }

    public void setNumThreads(int numThread) {
        this.numThreads = numThread;
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

    
}
