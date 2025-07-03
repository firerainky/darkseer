package com.zky.fragments.sortingJson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class SortingJsonRecursivelyTest {

    @Test
    void test_sortingJsonRecursively() throws IOException {
        Map<String, Object> unsortedMap = createUnsortedMap();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(unsortedMap);
        System.out.println("Unsorted JSON: " + json);
        json = JsonSortUtil.sortJsonRecursively(json);
        System.out.println("Sorted JSON:   " + json);
    }

    private Map<String, Object> createUnsortedMap() {
        Map<String, Object> unsortedMap = new HashMap<>();
        unsortedMap.put("apple", 1);
        unsortedMap.put("banana", 2);
        unsortedMap.put("cherry", 3);

        Map<String, Object> childUnsortedMap = new HashMap<>();
        childUnsortedMap.put("apple", 1);
        childUnsortedMap.put("banana", 2);
        childUnsortedMap.put("cherry", 3);

        unsortedMap.put("child", childUnsortedMap);

        return unsortedMap;
    }

    class JsonSortUtil {
        private static final ObjectMapper mapper = new ObjectMapper();
    
        static {
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        }
    
        public static String sortJsonRecursively(String json) throws IOException {
            JsonNode tree = mapper.readTree(json);
            JsonNode sorted = sortNode(tree);
            return mapper.writeValueAsString(sorted);
        }
    
        private static JsonNode sortNode(JsonNode node) {
            if (node.isObject()) {
                ObjectNode sortedNode = mapper.createObjectNode();
                TreeMap<String, JsonNode> sortedMap = new TreeMap<>();
                node.fieldNames().forEachRemaining(field -> sortedMap.put(field, sortNode(node.get(field))));
                sortedMap.forEach(sortedNode::set);
                return sortedNode;
            } else if (node.isArray()) {
                ArrayNode arrayNode = mapper.createArrayNode();
                for (JsonNode item : node) {
                    arrayNode.add(sortNode(item));
                }
                return arrayNode;
            } else {
                return node;
            }
        }
    }
}
