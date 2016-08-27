package com.github.dcal12.web_cache.cache.data;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Douglas Callaway on 8/27/16.
 */
public class HashtableAdapter extends XmlAdapter<BlockElement[], Map<String, byte[]>> {
    /*
      Adapted from the example by Grégory at
      https://stackoverflow.com/questions/3941479/jaxb-how-to-marshall-map-into-keyvalue-key
      Retrieved 27/08/2016
    */

    @Override
    public Map<String, byte[]> unmarshal(BlockElement[] blockElements) throws Exception {
        Map<String, byte[]> blocks = new Hashtable<>();
        for (BlockElement element : blockElements) {
            blocks.put(element.hash, element.block);
        }
        return blocks;
    }

    @Override
    public BlockElement[] marshal(Map<String, byte[]> stringMap) throws Exception {
        BlockElement[] blockElements = new BlockElement[stringMap.size()];
        int i = 0;
        for (Map.Entry<String, byte[]> entry : stringMap.entrySet()) {
            blockElements[i++] = new BlockElement(entry.getKey(), entry.getValue());
        }
        return blockElements;
    }
}
