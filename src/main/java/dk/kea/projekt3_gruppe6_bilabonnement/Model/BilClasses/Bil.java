package dk.kea.projekt3_gruppe6_bilabonnement.Model.Bil;

public class Bil {

    // ------------------- Fields -------------------

    private String model;

    //1: Identifikation
    private int id;

    // 2: Bil model data - 'configuration' data
        // for hver bil model vil disse blive initialiseret fra en konfigurationsklasse, hvor de er sat som konstanter

    private String gearType;
    private String fuelType;
    private double co2Emission;
    private double fuelConsumption;
    private String equipmentHighlightText;
    private int udbetaling;
    private int mdlYdelse;
    private int bindingMdr;
    private int samletYdelse36Maaneder;
    private String leveringTid;
    private String leveringsstedStandard;
    private int koerselPrMd;
    private String specifikkeEgenskaber;
    private int bagagerumsStoerrelse;
    private int selvrisiko;

    // 3: Bilspecifikationer - 'identitets' data
    private String vognNummer;
    private String stelNummer;
    private String udstyrsNiveau;
    private int kilometerKoert;

    // 4: Tilstand - 'status' data
    private String status;


    // ------------------- Constructors -------------------

    // constructor
    public Bil(int id, String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {
        this.id = id;
        this.vognNummer = vognNummer;
        this.stelNummer = stelNummer;
        this.udstyrsNiveau = udstyrsNiveau;
        this.kilometerKoert = kilometerKoert;
        this.status = status;
    }

    public Bil(String vognNummer, String stelNummer, String udstyrsNiveau, int kilometerKoert, String status) {

        this.vognNummer = vognNummer;
        this.stelNummer = stelNummer;
        this.udstyrsNiveau = udstyrsNiveau;
        this.kilometerKoert = kilometerKoert;
        this.status = status;
    }

    //empty constructor
    public Bil() {

    }

    // ------------------- interface implementation methods -------------------
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void setSomTilgaengelig() {
        this.status = "Tilgaengelig";
    }

    public void setSomUdlejet() {
        this.status = "Udlejet";
    }

    public void setSomTilService() {
        this.status = "Til service";
    }





    // ------------------- Get & Set -------------------


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVognNummer() {
        return vognNummer;
    }

    public void setVognNummer(String vognNummer) {
        this.vognNummer = vognNummer;
    }

    public String getStelNummer() {
        return stelNummer;
    }

    public void setStelNummer(String stelNummer) {
        this.stelNummer = stelNummer;
    }

    public String getUdstyrsNiveau() {
        return udstyrsNiveau;
    }

    public void setUdstyrsNiveau(String udstyrsNiveau) {
        this.udstyrsNiveau = udstyrsNiveau;
    }

    public int getKilometerKoert() {
        return kilometerKoert;
    }

    public void setKilometerKoert(int kilometerKoert) {
        this.kilometerKoert = kilometerKoert;
    }


    // ------------------- Get & Set for 'configuration' data -------------------
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getCo2Emission() {
        return co2Emission;
    }

    public void setCo2Emission(double co2Emission) {
        this.co2Emission = co2Emission;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getEquipmentHighlightText() {
        return equipmentHighlightText;
    }

    public void setEquipmentHighlightText(String equipmentHighlightText) {
        this.equipmentHighlightText = equipmentHighlightText;
    }

    public int getUdbetaling() {
        return udbetaling;
    }

    public void setUdbetaling(int udbetaling) {
        this.udbetaling = udbetaling;
    }

    public int getMdlYdelse() {
        return mdlYdelse;
    }

    public void setMdlYdelse(int mdlYdelse) {
        this.mdlYdelse = mdlYdelse;
    }

    public int getBindingMdr() {
        return bindingMdr;
    }

    public void setBindingMdr(int bindingMdr) {
        this.bindingMdr = bindingMdr;
    }

    public int getSamletYdelse36Maaneder() {
        return samletYdelse36Maaneder;
    }

    public void setSamletYdelse36Maaneder(int samletYdelse36Måneder) {
        this.samletYdelse36Maaneder = samletYdelse36Måneder;
    }

    public String getLeveringTid() {
        return leveringTid;
    }

    public void setLeveringTid(String leveringTid) {
        this.leveringTid = leveringTid;
    }

    public String getLeveringsstedStandard() {
        return leveringsstedStandard;
    }

    public void setLeveringsstedStandard(String leveringsstedStandard) {
        this.leveringsstedStandard = leveringsstedStandard;
    }

    public int getKoerselPrMd() {
        return koerselPrMd;
    }

    public void setKoerselPrMd(int koerselPrMd) {
        this.koerselPrMd = koerselPrMd;
    }

    public String getSpecifikkeEgenskaber() {
        return specifikkeEgenskaber;
    }

    public void setSpecifikkeEgenskaber(String specifikkeEgenskaber) {
        this.specifikkeEgenskaber = specifikkeEgenskaber;
    }

    public int getBagagerumsStoerrelse() {
        return bagagerumsStoerrelse;
    }

    public void setBagagerumsStoerrelse(int bagagerumsStoerrelse) {
        this.bagagerumsStoerrelse = bagagerumsStoerrelse;
    }

    public int getSelvrisiko() {
        return selvrisiko;
    }

    public void setSelvrisiko(int selvrisiko) {
        this.selvrisiko = selvrisiko;
    }


    // ------------------- toString -------------------
    @Override
    public String toString() {
        return "Bil{" +
                "id=" + id +
                ", vognNummer='" + vognNummer + '\'' +
                ", stelNummer='" + stelNummer + '\'' +
                ", udstyrsNiveau='" + udstyrsNiveau + '\'' +
                ", kilometerKoert=" + kilometerKoert +
                ", status='" + status + '\'' +
                '}';

    }

}