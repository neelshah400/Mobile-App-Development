package com.example.dallasmavericks;

public class Player {

    private int image;
    private String name;
    private String position;
    private int age;
    private String height;
    private double points;
    private double rebounds;
    private double assists;
    private double steals;
    private double blocks;
    private String video;

    public Player(int image, String name, String position, int age, String height, double points, double rebounds, double assists, double steals, double blocks, String video) {

        this.image = image;
        this.name = name;
        this.position = position;
        this.age = age;
        this.height = height;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.video = video;

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public double getPoints() {
        return points;
    }

    public double getRebounds() {
        return rebounds;
    }

    public double getAssists() {
        return assists;
    }

    public double getSteals() {
        return steals;
    }

    public double getBlocks() {
        return blocks;
    }

    public String getVideo() {
        return video;
    }
}
