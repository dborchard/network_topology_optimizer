package edu.utdallas.atn.p2.domain;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {

  private final Point southWest;
  private final Point northEast;

  private final List<Point> points;

  public Rectangle(Point southWest, Point northEast) {
    this.southWest = southWest;
    this.northEast = northEast;
    this.points = new ArrayList<>();
  }

  public boolean contains(Point point) {

    double swLatitude = southWest.getLat();
    double swLongitude = southWest.getLng();
    double neLatitude = northEast.getLat();
    double neLongitude = northEast.getLng();
    double latitude = point.getLat();
    double longitude = point.getLng();

    boolean longitudeContained = false;
    boolean latitudeContained = false;

    // Check if the bbox contains the prime meridian (longitude 0.0).
    if (swLongitude < neLongitude) {
      if (swLongitude < longitude && longitude < neLongitude) {
        longitudeContained = true;
      }
    } else {
      // Contains prime meridian.
      if ((0 < longitude && longitude < neLongitude)
          || (swLongitude < longitude && longitude < 0)) {
        longitudeContained = true;
      }
    }

    if (swLatitude < neLatitude) {
      if (swLatitude < latitude && latitude < neLatitude) {
        latitudeContained = true;
      }
    } else {
      // The poles. Don't care.
    }

    return (longitudeContained && latitudeContained);
  }

  public void add(Point point) {
    this.points.add(point);
  }

  public Graph generateGraph() {
    return new Graph(this.points);
  }
}
