package org.webconsole.karaf.features.jmx.ts;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.openmbean.TabularData;

public class TabularStructures {

    @SuppressWarnings("unchecked")
    public static <K, R> List<R> apply(TabularData data, TabularFunction<K, R> function) {
        List<R> result = new ArrayList<>();
        for (List<K> key : (Set<List<K>>) data.keySet()) {
            result.add(function.apply(key, data.get(key.toArray())));
        }
        return result;
    }


}
