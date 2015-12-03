package com.astral.back9;

import java.util.List;

/**
 * Created by connormyers on 12/2/15.
 */
public class Course {

    private String courseName, courseLocation, coursePar, courseAverage, holeCount;

    private List<Double> holeLocations;

    Course() {

    }

    Course(String _courseName, String _courseLocation, String _coursePar, String _courseAverage, String _holeCount, List<Double> _holeLocations) {
        courseName = _courseName;
        courseLocation = _courseLocation;
        coursePar = _coursePar;
        courseAverage = _courseAverage;
        holeCount = _holeCount;
        holeLocations = _holeLocations;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public String getCourseLocation() {
        return this.courseLocation;
    }

    public String getCoursePar() {
        return this.coursePar;
    }

    public String getCourseAverage() {
        return this.courseAverage;
    }

    public String getHoleCount() {
        return this.holeCount;
    }

}
