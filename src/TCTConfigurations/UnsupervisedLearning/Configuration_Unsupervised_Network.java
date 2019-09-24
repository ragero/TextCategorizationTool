package TCTConfigurations.UnsupervisedLearning;

import TCTParameters.UnsupervisedLearning.Parameters_LabelPropagation;
import TCTParameters.UnsupervisedLearning.Parameters_DocumentNetwork_Knn;
import TCTParameters.UnsupervisedLearning.Parameters_LabelPropagation_DocTerm;
import TCTParameters.UnsupervisedLearning.Parameters_MixtureModel;
import TCTParameters.UnsupervisedLearning.Parameters_SimpleRanking;
import TCTParameters.UnsupervisedLearning.Parameters_SimpleRanking_Plus_Clustering;

public class Configuration_Unsupervised_Network extends Configuration_Unsupervised_Base{
    
    Parameters_DocumentNetwork_Knn knn_Parameters;
    Parameters_LabelPropagation lp_Parameters;
    Parameters_LabelPropagation_DocTerm lpDocTerm_Parameters;
    Parameters_SimpleRanking simpleRanking_Parameters;
    Parameters_SimpleRanking_Plus_Clustering simpleRankClus_Parameters;
    Parameters_MixtureModel mixture_Model;
    private boolean mixtureModel;
    private boolean simpleRankClus;
    private boolean simpleRanking;
    private boolean lpDocTerm;
    private boolean lp;
    private boolean knn;
    private boolean cosine;

    public Configuration_Unsupervised_Network() {
        super();
        setLp_Parameters(new Parameters_LabelPropagation());
        setKnn_Parameters(new Parameters_DocumentNetwork_Knn());
        setLpDocTerm_Parameters(new Parameters_LabelPropagation_DocTerm());
        setSimpleRanking_Parameters(new Parameters_SimpleRanking());
        setSimpleRankClus_Parameters(new Parameters_SimpleRanking_Plus_Clustering());
        setMixture_Model(new Parameters_MixtureModel());
    }
    
    public boolean isKnn() {
        return knn;
    }

    public void setKnn(boolean knn) {
        this.knn = knn;
    }

    public Parameters_DocumentNetwork_Knn getKnn_Parameters() {
        return knn_Parameters;
    }

    public void setKnn_Parameters(Parameters_DocumentNetwork_Knn knn_Parameters) {
        this.knn_Parameters = knn_Parameters;
    }

    public Parameters_LabelPropagation getLp_Parameters() {
        return lp_Parameters;
    }

    public void setLp_Parameters(Parameters_LabelPropagation lp_Parameters) {
        this.lp_Parameters = lp_Parameters;
    }

    public Parameters_LabelPropagation_DocTerm getLpDocTerm_Parameters() {
        return lpDocTerm_Parameters;
    }

    public void setLpDocTerm_Parameters(Parameters_LabelPropagation_DocTerm lpDocTerm_Parameters) {
        this.lpDocTerm_Parameters = lpDocTerm_Parameters;
    }
    
    public boolean isCosine() {
        return cosine;
    }

    public void setCosine(boolean cosine) {
        this.cosine = cosine;
    }

    public boolean isLp() {
        return lp;
    }

    public void setLp(boolean lp) {
        this.lp = lp;
    }

    public boolean isLpDocTerm() {
        return lpDocTerm;
    }

    public void setLpDocTerm(boolean lpDocTerm) {
        this.lpDocTerm = lpDocTerm;
    }

    public Parameters_SimpleRanking getSimpleRanking_Parameters() {
        return simpleRanking_Parameters;
    }

    public void setSimpleRanking_Parameters(Parameters_SimpleRanking simpleRanking_Parameters) {
        this.simpleRanking_Parameters = simpleRanking_Parameters;
    }

    public boolean isSimpleRanking() {
        return simpleRanking;
    }

    public void setSimpleRanking(boolean simpleRanking) {
        this.simpleRanking = simpleRanking;
    }

    public Parameters_SimpleRanking_Plus_Clustering getSimpleRankClus_Parameters() {
        return simpleRankClus_Parameters;
    }

    public void setSimpleRankClus_Parameters(Parameters_SimpleRanking_Plus_Clustering simpleRankClus_Parameters) {
        this.simpleRankClus_Parameters = simpleRankClus_Parameters;
    }

    public boolean isSimpleRankClus() {
        return simpleRankClus;
    }

    public void setSimpleRankClus(boolean simpleRankClus) {
        this.simpleRankClus = simpleRankClus;
    }

    public Parameters_MixtureModel getMixture_Model() {
        return mixture_Model;
    }

    public void setMixture_Model(Parameters_MixtureModel mixture_Model) {
        this.mixture_Model = mixture_Model;
    }

    public boolean isMixtureModel() {
        return mixtureModel;
    }

    public void setMixtureModel(boolean mixtureModel) {
        this.mixtureModel = mixtureModel;
    }
    
    
    
}
