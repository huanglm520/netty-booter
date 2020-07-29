package com.nettybooter.framework.codec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * date: 2020-07-29
 * time: 23:26
 * author: huanglm520
 * see: https://github.com/huanglm520
 */
public class MultiValueMap extends HashMap<String, List<String>> implements Map<String, List<String>> {

    public void add(String key, String value) {
        computeIfAbsent(key, k -> new ArrayList<>()).add(value);
    }

    public String getFirst(String key) {
        List<String> values;
        return (values = get(key)) == null || values.isEmpty() ? null : values.get(0);
    }
}
