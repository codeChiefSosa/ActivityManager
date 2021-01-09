package com.example.activitymanager.Entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithActivities {
    @Embedded public User user;
    @Relation(
            parentColumn = "id",
            entityColumn = "userCreatorId"
    )
    public List<Activity> activities;
}
