package com.example.islam.agentnetworking;

import java.util.List;

/**
 * Created by islam on 09/02/17.
 */

public interface Databaselistener {
    public void onDatabaseSuccess(List<stringHolder> list);
    public void onDatabaseFailure();
}
