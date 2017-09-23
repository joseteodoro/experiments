package br.jteodoro.lambdas.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;


public class BFS_DFS {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    // source weight target
    strings.add("1 2 2");
    strings.add("1 1 3");
    strings.add("2 4 3");
    strings.add("2 10 4");
    strings.add("4 1 5");
    strings.add("3 1 5");

    Graph g = Graph.fromCollection(strings);
    g.BFS("1", (str) -> System.out.println(str) );
    System.out.println("\n");
    g.DFS("1", (str) -> System.out.println(str) );
    
    //System.out.println("\n");
    //g.bySource.entrySet().
    //    stream()
    //    .forEach( (entry) -> System.out.println(String.format("%s - %s", entry.getKey(), entry.getValue())));
      
  }
    
}


class Graph {
    
    static Graph fromCollection (Collection<String> strs) {
        Graph g =  new Graph();
        
        Collection<Edge> edges = strs.stream().map( (str) -> {
            return new Edge(str.split(" ") );
        }).collect( Collectors.toList() );
        
        g.bySource = edges.stream()
            .collect(Collectors.groupingBy( (edge) -> edge.source ));
        g.byTarget = edges.stream()
            .collect(Collectors.groupingBy( (edge) -> edge.target ));

        return g;
    }
    
    Map<String, List<Edge>> bySource;
    Map<String, List<Edge>> byTarget;
    
    void BFS(String source, Consumer<String> consumer ) {
        List<Edge> childs = bySource.get(source);
        BFSVisit(source, childs, new HashSet<String>(), consumer);
    }
    
    void DFS(String source, Consumer<String> consumer ) {
        List<Edge> childs = bySource.get(source);
        DFSVisit(source, childs, new HashSet<String>() , consumer);
    }
    
    private void BFSVisit(String source, List<Edge> childs, Set<String> visited,  Consumer<String> consumer ) {
        if (visited.contains(source)) {
            return;
        }
        consumer.accept(source);
        visited.add(source);
        if (childs != null) {
            childs.stream().forEach( (edge) -> {
                BFSVisit(edge.target, bySource.get(edge.target), visited, consumer);
            });
        }
    }
    
    private void DFSVisit(String source, List<Edge> childs, Set<String> visited,  Consumer<String> consumer ) {
        if (visited.contains(source)) {
            return;
        }
        if (childs != null) {
            childs.stream().forEach( (edge) -> {
                DFSVisit(edge.target, bySource.get(edge.target), visited, consumer);
            });
        }
        consumer.accept(source);
        visited.add(source);
    }
    
}

class Edge {
    
    String source;
    Integer weight;
    String target;
    
    Edge (String _source, Integer _weight, String _target) {
        this.source = _source;
        this.target = _target;
        this.weight = _weight;
    }
    
    Edge (String [] strs) {
        this(strs[0], Integer.valueOf(strs[1]), strs[2]);
    }
    
    public boolean equals(Object obj) {
        return this.source.equals( ((Edge) obj).source) &&
            this.target.equals( ((Edge) obj).target);
    }
    
    public int hashCode() {
        return 7 * this.source.hashCode() * this.target.hashCode();
    }
    
    public String toString() {
        return String.format("%s - %s -> %s", this.source, this.weight, this.target);
    };
}
