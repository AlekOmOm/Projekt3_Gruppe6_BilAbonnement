package dk.kea.projekt3_gruppe6_bilabonnement.Model;

public class PackageDeals {
    private String packageName;
    private int packagePrice;
    private String packageDescription;

    // ------------------- Constructor -------------------
    public PackageDeals(String packageName, int packagePrice, String packageDescription) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageDescription = packageDescription;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(int packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }


}
