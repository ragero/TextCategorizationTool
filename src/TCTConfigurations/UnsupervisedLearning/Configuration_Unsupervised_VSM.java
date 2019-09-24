package TCTConfigurations.UnsupervisedLearning;

import TCTParameters.UnsupervisedLearning.Parameters_B_K_M;
import TCTParameters.UnsupervisedLearning.Parameters_K_Means;
import TCTParameters.UnsupervisedLearning.Parameters_NMF;

public class Configuration_Unsupervised_VSM extends Configuration_Unsupervised_Base{
    
    private Parameters_K_Means kmeans_Parameter;
    private Parameters_B_K_M bKmeans_Parameter;
    private Parameters_NMF nmf_Parameter;
    private boolean nmf;
    private boolean k_means;
    private boolean b_k_means;

    public Configuration_Unsupervised_VSM() {
        super();
        setKmeans_Parameter(new Parameters_K_Means());
        setbKmeans_Parameter(new Parameters_B_K_M());
        setNmf_Parameter(new Parameters_NMF());
    }

    public Parameters_K_Means getKmeans_Parameter() {
        return kmeans_Parameter;
    }

    public void setKmeans_Parameter(Parameters_K_Means kmeans_Parameter) {
        this.kmeans_Parameter = kmeans_Parameter;
    }

    public Parameters_B_K_M getbKmeans_Parameter() {
        return bKmeans_Parameter;
    }

    public void setbKmeans_Parameter(Parameters_B_K_M bKmeans_Parameter) {
        this.bKmeans_Parameter = bKmeans_Parameter;
    }

    public Parameters_NMF getNmf_Parameter() {
        return nmf_Parameter;
    }

    public void setNmf_Parameter(Parameters_NMF nmf_Parameter) {
        this.nmf_Parameter = nmf_Parameter;
    }

    public boolean isNmf() {
        return nmf;
    }

    public void setNmf(boolean nmf) {
        this.nmf = nmf;
    }

    public boolean isK_means() {
        return k_means;
    }

    public void setK_means(boolean k_means) {
        this.k_means = k_means;
    }

    public boolean isB_k_means() {
        return b_k_means;
    }

    public void setB_k_means(boolean b_k_means) {
        this.b_k_means = b_k_means;
    }
       
}
