package com.github.dcal12.web_cache.cache.data;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Douglas Callaway on 8/29/16.
 */
public class StringListAdapter extends XmlAdapter<String[], List<String>> {

    @Override
    public List<String> unmarshal(String[] strings) throws Exception {
        List<String> stringList = new ArrayList();
        for (String string : strings) {
            stringList.add(string);
        }
        return stringList;
    }

    @Override
    public String[] marshal(List<String> stringList) throws Exception {
        return stringList.toArray(new String[0]);
    }
}
