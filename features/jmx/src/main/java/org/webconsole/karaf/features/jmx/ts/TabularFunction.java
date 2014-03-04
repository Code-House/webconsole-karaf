package org.webconsole.karaf.features.jmx.ts;

import java.util.List;

import javax.management.openmbean.CompositeData;

/**
 * 
 * 
 * @author dl02
 *
 * @param <K>
 * @param <R>
 */
public interface TabularFunction<K, R> {

    R apply(List<K> key, CompositeData value);

}
