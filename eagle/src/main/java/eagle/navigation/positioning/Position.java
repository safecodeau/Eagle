package eagle.navigation.positioning;

public abstract class Position{
	double longitude;
	double latitude;
	double altitude;
    double roll;
	double pitch;
	double yaw;

    public Position(double longitude, double latitude, double altitude,double roll, double pitch, double yaw){
        this.longitude=longitude;
        this.latitude=latitude;
        this.altitude=altitude;
        this.roll=roll;
        this.pitch=pitch;
        this.yaw=yaw;
    }

    public double getLongitude(){return this.longitude;};
    public double getLatitude(){return this.latitude;};
    public double getAltitude(){return this.altitude;};
    public double getRoll(){return this.roll;};
    public double getPitch(){return this.pitch;};
    public double getYaw(){return this.yaw;};

    public abstract String toString();
}
