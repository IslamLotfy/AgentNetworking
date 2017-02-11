package com.example.islam.agentnetworking.CallBackPackage;

import java.util.List;

/**
 * Created by islam on 09/02/17.
 */

public interface DataLoadedListener <T> {
    void onDataLoaded(List<T> result);
}