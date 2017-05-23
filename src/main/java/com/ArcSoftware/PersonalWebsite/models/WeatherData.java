package com.ArcSoftware.PersonalWebsite.models;

import java.util.List;

/**
 * Created by Jake on 5/22/17.
 */
public class WeatherData {
    private double latitude;
    private double longitude;
    private String timezone;
    private int offset;
    private List<Currently> currently;
    private List<Alert> alerts;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Currently> getCurrent() {
        return currently;
    }

    public void setCurrent(List<Currently> currently) {
        this.currently = currently;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<Currently> getCurrently() {
        return currently;
    }

    public void setCurrently(List<Currently> currently) {
        this.currently = currently;
    }

    public WeatherData() {
    }

    public static class Currently {
        Long time;
        String summary;
        String icon;
        Integer nearestStorm;
        Integer temperature;
        double humidity;
        double windspeed;

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public Integer getNearestStorm() {
            return nearestStorm;
        }

        public void setNearestStorm(Integer nearestStorm) {
            this.nearestStorm = nearestStorm;
        }

        public Integer getTemprature() {
            return temperature;
        }

        public void setTemprature(Integer temprature) {
            this.temperature = temperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getWindspeed() {
            return windspeed;
        }

        public void setWindspeed(double windspeed) {
            this.windspeed = windspeed;
        }

        public Currently() {
        }
    }

    public static class Alert {
        String title;
        long time;
        long expires;
        String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public long getExpires() {
            return expires;
        }

        public void setExpires(long expires) {
            this.expires = expires;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Alert() {
        }
    }
}
